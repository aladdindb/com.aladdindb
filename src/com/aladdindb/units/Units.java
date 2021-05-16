package com.aladdindb.units;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.aladdindb.finder.Finder;
import com.aladdindb.sorter.Sorter;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.units.models.Unit;
import com.aladdindb.units.models.UnitTransformer;
import com.aladdindb.util.Var;


/**
 * 
 * @author Macit Kandemir
 */
public class Units < UDM extends DataModel < UDM > > {

	
	public final 	Path path;
	
    private final  	UnitTransformer < UDM > unitTransformer;

    
    //**********************************************************
    //					 Constructors
    //**********************************************************
    
    public Units( Path path ) {
    	this( path, null );
    }
    
    public Units( Path path, Transformer< UDM > unitDataTransformer ) {
    	this.path 				= path;
        this.unitTransformer 	= new UnitTransformer< UDM >(unitDataTransformer);
        if( !Files.exists( path ) ) {
        	try {
				Files.createDirectories( path );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
    }

    //**********************************************************
    //					 Unit Search
    //**********************************************************
    
    public void search( Finder		< UDM, ? extends DataModel < ? > > 	finder, 
    					Sorter		< UDM, ? extends DataModel < ? > > 	sorter, 
    					Consumer 	< Unit < UDM > > 				unitConsumer ) {
    	this.search( finder, sorter ).forEach( unitId -> {
    		this.get( unitId, unitConsumer );
    	});
    }
    
    public List < String > search( Finder< UDM, ? extends DataModel < ? > > finder, Sorter< UDM, ? extends DataModel < ? > > sorter ) {
    	var rv = new ArrayList< String >();

    	sorter.setUnits( this );
    	
    	this.forEachUnit( unit -> {
       		if( finder.prove( unit ) ) rv.add( unit.id.get() ); 
    	});
    	
    	return sorter.sort(rv);
    }
    
    public void search( Finder< UDM, ? extends DataModel<?> > finder, Consumer < Unit < UDM > > unitConsumer ) {
//    	Counter c = new Counter();
    	this.forEachUnit( unit -> {
       		if( finder.prove( unit ) ) unitConsumer.accept( unit ); 
    		
//    		c.inc();
//    		if( c.getIndex() > 1000 ) {
//    			System.out.println( "  1000 Erreicht :-) ");
//    			c.reset();
//    		}
    		
    	});
    }
    
    //**********************************************************
    //					For Each Unit
    //**********************************************************

    public void forEachUnit( Consumer < Unit < UDM > > unitConsumer ) {
        forEachUnitNode( unitNode -> {
            unitTransformer.toModel( unitNode,  unitConsumer :: accept );
        });
    }

    //**********************************************************
    //					Units Operations
    //**********************************************************
    
    public void add( String... lables ) {
    	for( var label : lables ) {
    		this.add( label );
    	}
    }

    public void add( UDM... unitDatas ) {
    	for( var unitData : unitDatas ) {
    		this.add( unitData );
    	}
    }
    
    /**
     * return  OK ? newID : NULL 
     * 
     * @param dbUnit
     * @return
     */
    public String add( String label ) {
    	return this.add( null, label );
    }
    
    public String add( UDM unitData ) {
    	return this.add(unitData, null);
    }
    
    public String add( UDM unitData, String label ) {
    	
    	Var<String> newIdVar = new Var<>();
    	
    	ZonedDateTime timeStamp = ZonedDateTime.now();
    	
    	var unit = new Unit< UDM >();
    	
    	unit.meta.label.set(label);
    	
    	unit.meta.timeStamp.generatedOn	.set( timeStamp );
    	unit.meta.timeStamp.modifiedOn	.set( timeStamp );
    	
    	unit.data.set( unitData );
    	
    	unitTransformer.toNode( unit, unitNode -> {
    		newIdVar.set( addUnitNode( unitNode ) );
    	});
    	
    	return newIdVar.get();
    }
    
    public void get( String unitID, Consumer < Unit < UDM > > unitConsumer ) {
		getUnitNode( unitID, unitNode -> {
            unitTransformer.toModel( unitNode, unitConsumer :: accept );
		});
    } 

    public boolean update( Unit < UDM > unit ) {
		Var<Boolean> rv = new Var<Boolean>(false);
    	unit.meta.timeStamp.modifiedOn.set( ZonedDateTime.now() );
    	unit.incVersion();
    	unitTransformer.toNode( unit, unitNode -> {
    		if( updateUnitNode( unitNode ) ) {
           		rv.set( true );
    		}
    	});
		return rv.get();
    }

    public boolean remove( String unitID  ) {
    	var rv = new Var < Boolean > ( false );
		try {
			removeUnitFile( unitID );
			rv.set(true);
		} catch ( IOException e ) {
//            Logger.getLogger( Units.class.getName() ).log( Level.SEVERE, null, e );
		} 
		return rv.get();
    }

    //**********************************************************
    //					UnitNode Operations
    //**********************************************************
    
    /**
     * if( OK ) { return newID } else { NULL }
     * 
     * @param unitModelNode
     * @return
     */
    public String addUnitNode( SnPoint unitModelNode ) {
        var unitFile = UnitFile.createNew ( this.path, unitModelNode );
        return unitFile != null ? unitFile.unitID : null;
    }
    
    public boolean updateUnitNode( SnPoint unitModelNode ) {
        var unitFile = UnitFile.get ( this.path, unitModelNode );
        return unitFile != null ?  unitFile.saveUnitNode ( unitModelNode ) : false;
    }

    public void getUnitNode( String unitID, Consumer< SnPoint > consumer ) {
    	UnitFile.get ( this.path, unitID, unitFile -> {
    		unitFile.getUnitNode ( consumer );
    	} );
    }

    public void forEachUnitNode( Consumer < SnPoint > consumer ) {
        try {
			Files.newDirectoryStream( this.path ).forEach( unitPath -> {
				UnitFile.getUnitNode ( unitPath, consumer );
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    public void forEachUnitNodeStructureChange( Consumer < SnPoint >  consumer ) {
		forEachUnitNode( unitNode -> {
			consumer.accept(unitNode );
			updateUnitNode( unitNode );
		});
    }
    
    //**********************************************************
    //					UnitFile Operations
    //**********************************************************
    
    public void removeUnitFile( String unitID ) throws IOException {
        var unitFile = UnitFile.remove ( this.path, unitID );
    }

    
    //**********************************************************
    //					Label Map Operations
    //**********************************************************

    public void createIdLabelMap( Consumer < Map < String, String > > consumer ) {
    	consumer.accept( createIdLabelMap() );
    }
    
    public Map< String, String > createIdLabelMap() {
        Map< String, String > rv = new HashMap<>();
        forEachUnitNode( unitNode -> {
            var unitID = unitNode.attributes.getValue("id") ;
        	unitNode.children.get( "meta", meta -> {
        		meta.children.get( "label", label -> {
                    var unitLabel = label.value.get();
                    if( unitLabel != null ) rv.put( unitID, unitLabel );
        		});
        	});
        });
        return rv;
    }
    
    //**********************************************************
    //						
    //**********************************************************
}
