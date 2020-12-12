package com.xelara.aladdin.unit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

import com.xelara.core.io.Filess;
import com.xelara.structure.snode.SNode;
import com.xelara.structure.xml.XML;
import com.xelara.core.IdHandler;


public class DbUnitContainer {
	
	private static final String UNIT_FILE_NAME = "unit.xml";
	
	public final Path path;
	public final Path unitPath;
	
	public final String  unitID ;

    private DbUnitContainer( Path unitsPath, String unitID ) {
		this.unitID		= unitID;
		this.path 		= unitsPath.resolve ( unitID );
		this.unitPath	= this.path.resolve ( UNIT_FILE_NAME );
    }
    
    public boolean save( SNode unitNode ) {
        unitNode.setAttribute( "id", unitID );
        return XML.save ( unitPath, unitNode ) ;
    }
    
    public void getUnitNode ( Consumer < SNode > consumer ) {
    	var rv = getUnitNode ();
    	if( rv != null )consumer.accept ( rv );
    }
    
    public SNode getUnitNode() {
    	return XML.load ( unitPath );
    }
    
    //************************************************************
    //                       STATIC METHODS
    //************************************************************
    
	public static final DbUnitContainer createNew( Path unitsPath, SNode unitNode ) {
		DbUnitContainer rv = createNew ( unitsPath );
		return rv != null && rv.save ( unitNode ) ? rv : null;
	}
	
	public static final DbUnitContainer createNew( Path unitsPath ) {
		var id =  new IdHandler().createHexID();
		DbUnitContainer rv = new DbUnitContainer ( unitsPath, id );
        if( !Files.exists( rv.path ) ) {
        	Filess.createDirectories( rv.path );
        	return rv;
        }
        return null;
	}

	public static final DbUnitContainer get( Path unitsPath, SNode unitNode ) {
		var id = unitNode.getAttribute ( "id" );
		return id != null && !id.isEmpty () ? get( unitsPath, id ) : null; 
	}
    
	public static final void get( Path unitsPath, String id, Consumer < DbUnitContainer > consumer ) {
		var rv = get( unitsPath, id);
		if( rv != null )consumer.accept ( rv );
	}
	
	public static final DbUnitContainer get( Path unitsPath, String id ) {
		DbUnitContainer rv = new DbUnitContainer ( unitsPath, id );
        return Files.exists( rv.path ) ? rv : null; 
	}
	
	public static final void getUnitNode( Path path, Consumer < SNode > consumer ) {
		var rv = getUnitNode ( path );
		if( rv != null ) consumer.accept ( rv );
	}
	
	public static final SNode getUnitNode( Path path ) {
		if( Files.exists ( path ) && Files.isDirectory ( path ) )  {
			return XML.load ( path.resolve ( UNIT_FILE_NAME ) );
		}
        return null; 
	}
	
	public static final DbUnitContainer remove( Path unitsPath, String id ) throws IOException {
		var rv = get ( unitsPath, id );
		if( rv != null ) {
			if( Files.exists ( rv.path ) ) {
				Filess.deleteFileTree ( rv.path ); 
			}
		}
		return rv;
	}
	
}
