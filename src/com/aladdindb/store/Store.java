package com.aladdindb.store;

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
import com.aladdindb.store.models.Unit;
import com.aladdindb.store.models.UnitTransformer;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.util.Var;


/**
 * 
 * @author Macit Kandemir
 */
public class Store < UDM extends DataModel < UDM > > {

	
	public final 	Path storePath;
	
    private final  	UnitTransformer < UDM > unitTransformer;

    
    //**********************************************************
    //					 Constructors
    //**********************************************************
    
    public Store( Path storePath ) {
    	this( storePath, null );
    }
    
    public Store( Path storePath, Transformer< UDM > dataTransformer ) {
    	this.storePath 			= storePath;
        this.unitTransformer 	= new UnitTransformer< UDM >(dataTransformer);
        if( !Files.exists( storePath ) ) {
        	try {
				Files.createDirectories( storePath );
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
    }

    //**********************************************************
    //					 Unit Search
    //**********************************************************
    
    public void search( Finder		< UDM, ? extends DataModel < ? > > 	finder, 
    					Sorter		< UDM, ? extends DataModel < ? > > 	sorter, 
    					Consumer 	< Unit < UDM > > 					unitConsumer ) {
    	
    	this.search( finder, sorter ).forEach( unitId -> {
    		this.getUnitById( unitId, unitConsumer );
    	});
    	
    }
    
    public List < String > search( 	Finder< UDM, ? extends DataModel < ? > > finder, 
    								Sorter< UDM, ? extends DataModel < ? > > sorter ) {
    	
    	var rv = new ArrayList< String >();

    	sorter.setStore( this );
    	
    	this.forEachUnit( unit -> {
       		if( finder.prove( unit ) ) rv.add( unit.id.get() ); 
    	});
    	
    	return sorter.sort(rv);
    }
    
    public void search( Finder		< UDM, ? extends DataModel < ? > > 	finder, 
    					Consumer 	< Unit < UDM > > 					unitConsumer ) {
    	this.forEachUnit( unit -> {
       		if( finder.prove( unit ) ) unitConsumer.accept( unit ); 
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
    
    public void addUnits( String... lables ) {
    	for( var label : lables ) {
    		this.addUnit( label );
    	}
    }

    public void addUnits( UDM... datas ) {
    	for( var unitData : datas ) {
    		this.addUnit( unitData );
    	}
    }
    
    /**
     * return  OK ? newID : NULL 
     * 
     * @param dbUnit
     * @return
     */
    public String addUnit( String label ) {
    	return this.addUnit( null, label );
    }
    
    public String addUnit( UDM data ) {
    	return this.addUnit(data, null);
    }
    
    public String addUnit( UDM data, String label ) {
    	
    	Var<String> newUnitIdVar = new Var<>();
    	
    	ZonedDateTime timeStamp = ZonedDateTime.now();
    	
    	var unit = new Unit< UDM >();
    	
    	unit.meta.label.set(label);
    	
    	unit.meta.timeStamp.generatedOn	.set( timeStamp );
    	unit.meta.timeStamp.modifiedOn	.set( timeStamp );
    	
    	unit.data.set( data );
    	
    	unitTransformer.toNode( unit, unitNode -> {
    		newUnitIdVar.set( addUnitNode( unitNode ) );
    	});
    	
    	return newUnitIdVar.get();
    }
    
    public void getUnitById( String unitId, Consumer < Unit < UDM > > unitConsumer ) {
		getUnitNodeById( unitId, unitNode -> {
            unitTransformer.toModel( unitNode, unitConsumer :: accept );
		});
    } 

    public boolean updateUnit( Unit < UDM > unit ) {
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

    public boolean removeUnit( String unitId  ) {
    	var rv = new Var < Boolean > ( false );
		try {
			removeUnitFileById( unitId );
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
        var unitFile = UnitFile.createNew ( this.storePath, unitModelNode );
        return unitFile != null ? unitFile.unitID : null;
    }
    
    public boolean updateUnitNode( SnPoint unitModelNode ) {
        var unitFile = UnitFile.get ( this.storePath, unitModelNode );
        return unitFile != null ?  unitFile.saveUnitNode ( unitModelNode ) : false;
    }

    public void getUnitNodeById( String unitId, Consumer< SnPoint > consumer ) {
    	UnitFile.get ( this.storePath, unitId, unitFile -> {
    		unitFile.getUnitNode ( consumer );
    	} );
    }

    public void forEachUnitNode( Consumer < SnPoint > consumer ) {
        try {
			Files.newDirectoryStream( this.storePath ).forEach( unitPath -> {
				UnitFile.getUnitNode ( unitPath, consumer );
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
 
    public void changeStructureForEachUnitNode( Consumer < SnPoint >  unitNodeConsumer ) {
		forEachUnitNode( unitNode -> {
			unitNodeConsumer.accept(unitNode );
			updateUnitNode( unitNode );
		});
    }
    
    //**********************************************************
    //					UnitFile Operations
    //**********************************************************
    
    public void removeUnitFileById( String unitId ) throws IOException {
        var unitFile = UnitFile.remove ( this.storePath, unitId );
    }

    
    //**********************************************************
    //					Label Map Operations
    //**********************************************************

    public void createIdLabelMap( Consumer < Map < String, String > > idLabelMapConsumer ) {
    	idLabelMapConsumer.accept( createIdLabelMap() );
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
