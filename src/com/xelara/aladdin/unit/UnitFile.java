package com.xelara.aladdin.unit;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

import com.xelara.core.io.Filess;
import com.xelara.core.util.IdHandler;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.xml.XML;


public class UnitFile {
	
	public final Path path;
	
	public final String  unitID ;

    private UnitFile( Path unitsPath, String unitID ) {
		this.unitID		= unitID;
		this.path 		= unitsPath.resolve ( unitID+".xml");
    }
    
    public boolean save( SnPoint unitNode ) {
        unitNode.attributes.set( "id", unitID );
        return XML.save ( this.path, unitNode ) ;
    }
    
    public void getUnitNode ( Consumer < SnPoint > consumer ) {
    	var rv = getUnitNode ();
    	if( rv != null )consumer.accept ( rv );
    }
    
    public SnPoint getUnitNode() {
    	return XML.load ( this.path );
    }
    
	public  boolean remove() {
		return Filess.deleteFile( this.path );
	}
    
    //************************************************************
    //                       STATIC METHODS
    //************************************************************
    
	public static final UnitFile createNew( Path unitsPath, SnPoint unitModelNode ) {
		var id =  new IdHandler().createHexID();
		UnitFile rv = new UnitFile ( unitsPath, id );
		return rv.save ( unitModelNode ) ? rv : null;
	}
	
	public static final UnitFile get( Path unitsPath, SnPoint unitModelNode ) {
		var id = unitModelNode.attributes.getValue ( "id" );
		return id != null && !id.isEmpty () ? get( unitsPath, id ) : null; 
	}
    
	public static final void get( Path unitsPath, String id, Consumer < UnitFile > consumer ) {
		var rv = get( unitsPath, id);
		if( rv != null )consumer.accept ( rv );
	}
	
	public static final UnitFile get( Path unitsPath, String id ) {
		UnitFile rv = new UnitFile ( unitsPath, id );
        return Files.exists( rv.path ) ? rv : null; 
	}
	
	public static final void getUnitNode( Path path, Consumer < SnPoint > consumer ) {
		var rv = getUnitNode ( path );
		if( rv != null ) consumer.accept ( rv );
	}
	
	public static final SnPoint getUnitNode( Path path ) {
		return XML.load ( path );
	}
	
	public static final UnitFile remove( Path unitsPath, String id ) {
		var rv = get ( unitsPath, id );
		if( rv != null ) rv.remove();
		return rv;
	}
	
}
