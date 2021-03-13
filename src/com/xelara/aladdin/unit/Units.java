package com.xelara.aladdin.unit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.xelara.aladdin.index.model.IndexModel;
import com.xelara.aladdin.unit.model.DataModel;
import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.aladdin.unit.model.UnitModel;
import com.xelara.aladdin.unit.model.UnitModelParser;
import com.xelara.aladdin.verifier.Verifier;
import com.xelara.core.Xlr;
import com.xelara.core.io.Filess;
import com.xelara.core.util.Var;
import com.xelara.structure.sn.SnPoint;

/**
 *
 * @author Macit
 * 
 * @param <DUM>
 * @param <DUMP>
 */
public class Units < DATA_MODEL extends DataModel < DATA_MODEL > > {

	public final Path path;
    
    private final  UnitModelParser < DATA_MODEL > unitModelParser;

    public Units( Path path, DataModelParser< DATA_MODEL > dataModelParser ) {
    	this.path 				= path;
        this.unitModelParser 	= new UnitModelParser< DATA_MODEL >(dataModelParser);
        if( !Files.exists( path ) ) Filess.createDirectories( path );
        
    }

    public void forEach( Consumer < UnitModel < DATA_MODEL > > consumer ) {
        forEachUnitModelNode( unitModelNode -> {
            unitModelParser.toModel( unitModelNode,  consumer :: accept );
        });
    }

    public void forEach( Verifier< DATA_MODEL > verifier, Consumer < UnitModel < DATA_MODEL > > consumer ) {
    	this.forEach( model -> {
    		model.data.get( dataModel -> {
        		if( verifier.prove( dataModel ) ) consumer.accept(model);
    		});
    	});
    }
    
    
    public void addUnits( DATA_MODEL... dataModels ) {
    	for( DATA_MODEL unit : dataModels ) {
    		this.addUnit(unit);
    	}
    }
    
    /**
     * return  OK ? newID : NULL 
     * 
     * @param dbUnit
     * @return
     */
    public String addUnit( DATA_MODEL dataModel ) {
    	
    	Var<String> newIdVar = new Var<>();
    	
    	String timeStamp = Xlr.zonedDateTime.timeStamp();
    	
    	var unitModel = new UnitModel< DATA_MODEL >();
    	
    	unitModel.meta.timeStamp.create.set( timeStamp );
    	unitModel.meta.timeStamp.update.set( timeStamp );
    	
    	unitModel.data.set( dataModel );
    	
    	unitModelParser.toNode( unitModel, unitModelNode -> {
    		newIdVar.set( add( unitModelNode ) );
    	});
    	
    	return newIdVar.get();
    }
    
    public boolean updateUnit( UnitModel < DATA_MODEL > unitModel ) {
		Var<Boolean> rv = new Var<Boolean>(false);
    	unitModel.meta.timeStamp.update.set( Xlr.zonedDateTime.timeStamp() );
    	unitModelParser.toNode( unitModel, unitModelNode -> {
       		rv.set( update( unitModelNode ) );
    	});
		return rv.get();
    }

    public void structureChangeForEachUnitModelNode( Consumer < SnPoint >  consumer ) {
		forEachUnitModelNode( unitModelNode -> {
			consumer.accept(unitModelNode );
			update( unitModelNode );
		});
    }

    public boolean removeUnit( String unitID  ) {
    	Var<Boolean> rv = new Var<>( false );
		try {
			remove( unitID );
			rv.set(true);
		} catch ( IOException e ) {
            Logger.getLogger( Units.class.getName() ).log( Level.SEVERE, null, e );
		} 
		return rv.get();
    }

    public void getUnitModel( IndexModel indexModel, Consumer < UnitModel < DATA_MODEL > > consumer ) {
    	indexModel.refUnitID.get( refUnitID -> {
    		this.getUnitModel( refUnitID, consumer );
    	});
    }

    public void getUnitModel( String unitID, Consumer < UnitModel < DATA_MODEL > > consumer ) {
		get( unitID, unitNode -> {
            unitModelParser.toModel( unitNode, consumer :: accept );
		});
    } 

    
    /**
     * if( OK ) { return newID } else { NULL }
     * 
     * @param unitModelNode
     * @return
     */
    public String add( SnPoint unitModelNode ) {
        var uc = UnitFile.createNew ( this.path, unitModelNode );
        return uc != null ? uc.unitID : null;
    }
    
    public boolean update( SnPoint unitModelNode ) {
        var uc = UnitFile.get ( this.path, unitModelNode );
        return uc != null ?  uc.save ( unitModelNode ) : false;
    }

    public void remove( String unitID ) throws IOException {
        var uc = UnitFile.remove ( this.path, unitID );
    }

    public void get( String unitID, Consumer< SnPoint > consumer ) {
    	UnitFile.get ( this.path, unitID, uc -> {
    		uc.getUnitNode ( consumer );
    	} );
    }
    
    public void forEachUnitModelNode( Consumer < SnPoint > consumer ) {
        Filess.forEachDirStream( this.path, unitPath -> {
        	UnitFile.getUnitNode ( unitPath, consumer );
        });
    }
 
    public void createIdLabelMap( Consumer < Map < String, String > > consumer ) {
    	consumer.accept( createIdLabelMap() );
    }
    
    public Map< String, String > createIdLabelMap() {
        Map< String, String > rv = new HashMap<>();
        forEachUnitModelNode( unode -> {
            var unitID = unode.attributes.getValue("id") ;
        	unode.children.get( "meta", meta -> {
        		meta.children.get( "label", label -> {
                    var unitLabel = label.value.get();
                    if( unitLabel != null ) rv.put( unitID, unitLabel );
        		});
        	});
        });
        return rv;
    }
    
    
}