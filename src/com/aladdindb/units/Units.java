package com.aladdindb.units;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.aladdindb.finder.Finder;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataTransformer;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.units.models.Unit;
import com.aladdindb.units.models.UnitParser;
import com.aladdindb.util.Counter;
import com.aladdindb.util.Var;


/**
 * 
 * @author Macit Kandemir
 */
public class Units < UDM extends DataModel < UDM > > {

	public final Path path;
    
    private final  UnitParser < UDM > unitParser;

    public Units( Path path, DataTransformer< UDM > unitDataParser ) {
    	this.path 			= path;
        this.unitParser 	= new UnitParser< UDM >(unitDataParser);
        if( !Files.exists( path ) ) {
        	try {
				Files.createDirectories( path );
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
    }

    public void forEachUnit( Consumer < Unit < UDM > > consumer ) {
        forEachUnitNode( unitNode -> {
            unitParser.toModel( unitNode,  consumer :: accept );
        });
    }

    public void forEachUnit( Finder< UDM, ? extends DataModel<?> > filter, Consumer < Unit < UDM > > consumer ) {
//    	Counter c = new Counter();
    	this.forEachUnit( unit -> {
       		if( filter.prove( unit ) ) consumer.accept( unit );
    		
//    		c.inc();
//    		if( c.getIndex() > 1000 ) {
//    			System.out.println( "  1000 Erreicht :-) ");
//    			c.reset();
//    		}
    		
    	});
    }
    
    
    public void addUnits( UDM... unitDatas ) {
    	for( UDM unitData : unitDatas ) {
    		this.addUnit( unitData );
    	}
    }
    
    /**
     * return  OK ? newID : NULL 
     * 
     * @param dbUnit
     * @return
     */
    public String addUnit( UDM unitData ) {
    	
    	Var<String> newIdVar = new Var<>();
    	
    	ZonedDateTime timeStamp = ZonedDateTime.now();
    	
    	var unit = new Unit< UDM >();
    	
    	unit.meta.timeStamp.create.set( timeStamp );
    	unit.meta.timeStamp.update.set( timeStamp );
    	
    	unit.data.set( unitData );
    	
    	unitParser.toNode( unit, unitNode -> {
    		newIdVar.set( addUnitNode( unitNode ) );
    	});
    	
    	return newIdVar.get();
    }
    
    public boolean updateUnit( Unit < UDM > unit ) {
		Var<Boolean> rv = new Var<Boolean>(false);
    	unit.meta.timeStamp.update.set( ZonedDateTime.now() );
    	unit.incVersion();
    	unitParser.toNode( unit, unitNode -> {
    		if( updateUnitNode( unitNode ) ) {
           		rv.set( true );
    		}
    	});
		return rv.get();
    }

    public void structureChangeForEachUnitNode( Consumer < SnPoint >  consumer ) {
		forEachUnitNode( unitNode -> {
			consumer.accept(unitNode );
			updateUnitNode( unitNode );
		});
    }

    public boolean removeUnit( String unitID  ) {
    	Var<Boolean> rv = new Var<>( false );
		try {
			removeUnitFile( unitID );
			rv.set(true);
		} catch ( IOException e ) {
//            Logger.getLogger( Units.class.getName() ).log( Level.SEVERE, null, e );
		} 
		return rv.get();
    }

    public void getUnit( String unitID, Consumer < Unit < UDM > > consumer ) {
		getUnitNode( unitID, unitNode -> {
            unitParser.toModel( unitNode, consumer :: accept );
		});
    } 

    
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

    public void removeUnitFile( String unitID ) throws IOException {
        var unitFile = UnitFile.remove ( this.path, unitID );
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
    
    
}
