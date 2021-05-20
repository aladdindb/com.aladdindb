package com.aladdindb.store;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.xml.XML;
import com.aladdindb.util.IdHandler;

/**
 * 
 * @author Macit Kandemir
 */
public class UnitFile {
	
	public final Path unitPath;
	
	public final String  unitID ;

    private UnitFile( Path storePath, String unitID ) {
		this.unitID		= unitID;
		this.unitPath 	= storePath.resolve ( unitID+".xml");
    }
    
    public boolean saveUnitNode( SnPoint unitNode ) {
        unitNode.attributes.set( "id", unitID );
        return XML.save ( this.unitPath, unitNode ) ;
    }
    
    public void getUnitNode ( Consumer < SnPoint > consumer ) {
    	var rv = getUnitNode ();
    	if( rv != null )consumer.accept ( rv );
    }
    
    public SnPoint getUnitNode() {
    	return XML.load ( this.unitPath );
    }
    
	public  boolean removeUnitFile() {
		try {
			Files.delete( this.unitPath );
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
    
    //************************************************************
    //                       STATIC METHODS
    //************************************************************
    
	public static final UnitFile createNew( Path storePath, SnPoint unitNode ) {
		var unitId =  new IdHandler().createHexID();
		UnitFile rv = new UnitFile ( storePath, unitId );
		return rv.saveUnitNode ( unitNode ) ? rv : null;
	}
	
	public static final UnitFile get( Path storePath, SnPoint unitNode ) {
		var unitId = unitNode.attributes.getValue ( "id" );
		return unitId != null && !unitId.isEmpty () ? get( storePath, unitId ) : null; 
	}
    
	public static final void get( Path storePath, String unitId, Consumer < UnitFile > consumer ) {
		var rv = get( storePath, unitId);
		if( rv != null )consumer.accept ( rv );
	}
	
	public static final UnitFile get( Path storePath, String unitId ) {
		UnitFile rv = new UnitFile ( storePath, unitId );
        return Files.exists( rv.unitPath ) ? rv : null; 
	}
	
	public static final void getUnitNode( Path unitPath, Consumer < SnPoint > consumer ) {
		var rv = getUnitNode ( unitPath );
		if( rv != null ) consumer.accept ( rv );
	}
	
	public static final SnPoint getUnitNode( Path unitPath ) {
		return XML.load ( unitPath );
	}
	
	public static final UnitFile remove( Path storePath, String unitId ) {
		var rv = get ( storePath, unitId );
		if( rv != null ) rv.removeUnitFile();
		return rv;
	}
	
}
