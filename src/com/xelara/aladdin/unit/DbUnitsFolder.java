package com.xelara.aladdin.unit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import com.xelara.core.io.Filess;
import com.xelara.structure.snode.SNode;

/**
 *
 * @author Macit Kandemir
 */
public class DbUnitsFolder  {
    
    private final Path  unitsPath;
    
	public static final String UNITS_FOLDER_NAME = "units";
	public static final String TREES_FOLDER_NAME = "trees";

	protected DbUnitsFolder( Path unitsPath ) {
        this.unitsPath	= unitsPath;
        if( !Files.exists( unitsPath ) ) Filess.createDirectories( unitsPath );
    }

    /**
     * if( OK ) { return newID } else { NULL }
     * 
     * @param unitNode
     * @return
     */
    public String add( SNode unitNode ) {
        var uc = DbUnitContainer.createNew ( unitsPath, unitNode );
        return uc != null ? uc.unitID : null;
    }
    
    public boolean update( SNode unitNode ) {
        var uc = DbUnitContainer.get ( unitsPath, unitNode );
        return uc != null ?  uc.save ( unitNode ) : false;
    }

    public void remove( String unitID ) throws IOException {
        var uc = DbUnitContainer.remove ( unitsPath, unitID );
    }

    public void get( String unitID, Consumer< SNode > consumer ) {
    	DbUnitContainer.get ( unitsPath, unitID, uc -> {
    		uc.getUnitNode ( consumer );
    	} );
    }
    
    public void forEach( Consumer < SNode > consumer ) {
        Filess.forEachDirStream( unitsPath, unitPath -> {
        	DbUnitContainer.getUnitNode ( unitPath, consumer );
        });
    }
 
    public Map< String, String > createIdLabelMap() {
        
        Map< String, String > rv = new HashMap<>();
        
        forEach( unode -> {
            var unitID       = unode.getAttribute( "id"       );
            var unitLabel    = unode.getAttribute( "label"    );
            
            rv.put( unitID, unitLabel );
        });
        
        return rv;
        
    }
    
}
