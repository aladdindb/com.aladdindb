package com.xelara.aladdin.unit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.xelara.aladdin.index.model.IndexModel;
import com.xelara.aladdin.unit.model.DbUnitModel;
import com.xelara.aladdin.unit.model.DbUnitModelParser;
import com.xelara.core.io.Filess;
import com.xelara.structure.snode.SNode;
import com.xelara.core.DateUtil;
import com.xelara.core.Var;

/**
 *
 * @author Macit
 * 
 * @param <DUM>
 * @param <DUMP>
 */
public class DbUnits < 

	DUM 	extends DbUnitModel			< DUM >,
	DUMP 	extends DbUnitModelParser 	< DUM > > {

	public static final String UNITS_FOLDER_NAME = "units";
	public static final String TREES_FOLDER_NAME = "trees";
	
	public final Path path;
    
    private final  DUMP unitModelParser;

    public DbUnits( Path path,  DUMP unitModelParser ) {
    	this.path 				= path;
        this.unitModelParser 	= unitModelParser;
        if( !Files.exists( path ) ) Filess.createDirectories( path );
        
    }

    public void getUnitsFolder( Consumer < DbUnitsFolder > consumer ) {
   		consumer.accept( new DbUnitsFolder ( path.resolve ( UNITS_FOLDER_NAME ) ) );
    }
    
    public void forEach( Consumer < DUM > consumer ) {
    	getUnitsFolder( unitsFolder -> {
            unitsFolder.forEach( unitNode -> {
                unitModelParser.parse( unitNode,  consumer :: accept );
            });
    	});
    }

    public void createIdLabelMap( Consumer < Map < String, String > > consumer ) {
    	getUnitsFolder( unitsFolder -> {
    		consumer.accept( unitsFolder.createIdLabelMap() );
        });
    }
    
    /**
     * if( OK ) { return newID } else { NULL }
     * 
     * @param dbUnit
     * @return
     */
    public String addUnit( DUM dbUnit ) {
    	Var<String> newIdVar = new Var<>();
    	 var time = DateUtil.nowAsTimeStamp();
    	dbUnit.tsNew.setValue( time );
    	dbUnit.tsUpdate.setValue( time );
    	unitModelParser.parse( dbUnit, unitNode -> {
    		getUnitsFolder( unitsFolder -> { 
    			newIdVar.setValue( unitsFolder.add( unitNode ) );
    		});
    	});
    	return newIdVar.getValue();
    }
    
    public boolean updateUnit( DUM dbUnit ) {
		Var<Boolean> rv = new Var<Boolean>(false);
    	dbUnit.tsUpdate.setValue( DateUtil.nowAsTimeStamp() );
    	unitModelParser.parse( dbUnit, unitNode -> {
    		getUnitsFolder( unitsFolder ->  {
        		rv.setValue( unitsFolder.update( unitNode ) );
    		});
    	});
		return rv.getValue();
    }

    public void structureChangeForEachUnitNode( Consumer < SNode >  unitConsumer ) {
		getUnitsFolder( unitsFolder ->  {
			unitsFolder.forEach( unitNode -> {
				unitConsumer.accept(unitNode );
				unitsFolder.update( unitNode );
			});
		});
    }

    public boolean removeUnit( String unitID  ) {
    	Var<Boolean> rv = new Var<>( false );
    		getUnitsFolder( unitsFolder -> { 
    			try {
					unitsFolder.remove( unitID );
					rv.setValue(true);
				} catch ( IOException e ) {
		            Logger.getLogger( DbUnits.class.getName() ).log( Level.SEVERE, null, e );
				} 
    		});
    		return rv.getValue();
    }

    public void getUnitModel( IndexModel indexModel, Consumer < DUM > consumer ) {
    	indexModel.refUnitID.getValue( refUnitID -> {
    		this.getUnitModel( refUnitID, consumer );
    	});
    }

    public void getUnitModel( String unitID, Consumer < DUM > consumer ) {
    	getUnitsFolder( unitsFolder -> {
    		unitsFolder.get( unitID, unitNode -> {
                unitModelParser.parse( unitNode, consumer :: accept );
    		});
    	});
    } 

    
}
