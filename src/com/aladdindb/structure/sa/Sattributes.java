package com.aladdindb.structure.sa;

import java.util.function.Consumer;

import com.aladdindb.structure.KeyValue;
import com.aladdindb.structure.sa.props.SaPoint;
import com.aladdindb.structure.sn.SnFeature;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.sn.props.SnAttribute;

/**
 * Provide as an encapsulation for the 
 * creation and administration of SN object structures
 *
 * @version 1.0, 2005/03/07
 * @author Macit Kandemir
 *
 */
public class Sattributes extends SnFeature {

    public final SnAttribute snAttribute;
    
    public Sattributes( SnPoint snPoint ) {
    	super( snPoint );
    	this.snAttribute = new SnAttribute( snPoint );
	}

   // ****************************************************************
    
    public final String getValue( String key ) {
        SaPoint saPoint = get(key);
        return saPoint == null ? null : saPoint.value.get();
    }
    
    // ******************************************************************
    // *              		Get Operations
    // ******************************************************************
	
    public final void get( String key, Consumer < SaPoint > consumer ) {
    	var rv = get( key );
    	if( rv != null )consumer.accept( rv );
    }

    public SaPoint get ( String key ) {
		var start = this.snAttribute.get();
    	return start != null ? start.get(key) : null;
	}
	
    // ******************************************************************
    // *              		Set Attribute
    // ******************************************************************
    
	public final void set( SaPoint... saPoints ) {
		var start = this.snAttribute.get();
		if( start != null ) {
			start.set( saPoints );
		} else {
			this.snAttribute.set( saPoints[0] );
			this.set( saPoints );
		}
	}

	public final void set( KeyValue... keyValues ) {
		var start = this.snAttribute.get();
		if( start != null ) {
			start.set( keyValues );
		} else {
			this.snAttribute.set( new SaPoint( keyValues[0]) );
			this.set( keyValues );
		}
	}
	
    public final void set( String key, String value ) {
		var start = this.snAttribute.get();
    	if( start != null ) {
    		start.set( key, value );
    	} else this.snAttribute.set( new SaPoint( key, value ) );
    }

    // ******************************************************************
    // *              		Last Operations
    // ******************************************************************
	
	public SaPoint getEnd ( ) {
		var start = this.snAttribute.get();
		return start != null ? start.getEnd() :null;
	}
    
    // ******************************************************************
    // *              				remove
    // ******************************************************************
	
    public final void remove( String key ) {
		var start = this.snAttribute.get();
		start.remove(key);
    }

    // ******************************************************************
    // *              				exist
    // ******************************************************************
    
	public boolean exist() {
		return this.snAttribute.get() != null;
	}

	public final boolean exist( String key ) {
		return this.get( key ) != null;
	}

    // ******************************************************************
    // *              			forEach
    // ******************************************************************

	public final void forEach( Consumer< SaPoint > consumer ) {
        var first = this.snAttribute.get();
        if( first != null )first.forEach(consumer);
    }
    
}
