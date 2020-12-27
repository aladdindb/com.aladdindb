package com.xelara.aladdin.unit;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

import com.xelara.core.IdHandler;
import com.xelara.core.io.Filess;
import com.xelara.structure.snode.SNode;
import com.xelara.structure.xml.XML;


public class DbUnitFile {
	
	public final Path path;
	
	public final String  unitID ;

    private DbUnitFile( Path unitsPath, String unitID ) {
		this.unitID		= unitID;
		this.path 		= unitsPath.resolve ( unitID+".xml");
    }
    
    public boolean save( SNode unitNode ) {
        unitNode.setAttribute( "id", unitID );
        return XML.save ( this.path, unitNode ) ;
    }
    
    public void getUnitNode ( Consumer < SNode > consumer ) {
    	var rv = getUnitNode ();
    	if( rv != null )consumer.accept ( rv );
    }
    
    public SNode getUnitNode() {
    	return XML.load ( this.path );
    }
    
	public  boolean remove() {
		return Filess.deleteFile( this.path );
	}
    
    //************************************************************
    //                       STATIC METHODS
    //************************************************************
    
	public static final DbUnitFile createNew( Path unitsPath, SNode unitNode ) {
		var id =  new IdHandler().createHexID();
		DbUnitFile rv = new DbUnitFile ( unitsPath, id );
		return rv.save ( unitNode ) ? rv : null;
	}
	
	public static final DbUnitFile get( Path unitsPath, SNode unitNode ) {
		var id = unitNode.getAttribute ( "id" );
		return id != null && !id.isEmpty () ? get( unitsPath, id ) : null; 
	}
    
	public static final void get( Path unitsPath, String id, Consumer < DbUnitFile > consumer ) {
		var rv = get( unitsPath, id);
		if( rv != null )consumer.accept ( rv );
	}
	
	public static final DbUnitFile get( Path unitsPath, String id ) {
		DbUnitFile rv = new DbUnitFile ( unitsPath, id );
        return Files.exists( rv.path ) ? rv : null; 
	}
	
	public static final void getUnitNode( Path path, Consumer < SNode > consumer ) {
		var rv = getUnitNode ( path );
		if( rv != null ) consumer.accept ( rv );
	}
	
	public static final SNode getUnitNode( Path path ) {
		return XML.load ( path );
	}
	
	public static final DbUnitFile remove( Path unitsPath, String id ) {
		var rv = get ( unitsPath, id );
		if( rv != null ) rv.remove();
		return rv;
	}
	
}
