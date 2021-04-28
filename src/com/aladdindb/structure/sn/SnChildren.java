package com.aladdindb.structure.sn;

import java.util.function.Consumer;

import com.aladdindb.structure.KeyValue;
import com.aladdindb.structure.sn.SnPoint.ITER;
import com.aladdindb.structure.sn.props.SnBottom;


/**
*
* @author Macit Kandemir
*/
public class SnChildren extends SnFeature {

	public final SnBottom snBottom;
	
    public SnChildren( SnPoint snPoint ) {
    	super( snPoint );
    	this.snBottom = new SnBottom( snPoint );
	}

    // ******************************************************************

    public final void set( SnPoint newPoint ) {
        if ( newPoint != null ) {
            SnPoint existPoint = get( newPoint.key.get() );
            if ( existPoint != null ) {
                existPoint.replace( newPoint );
            } else {
                this.add( newPoint );
            }
        }
    }

    public final void add( SnPoint... newPoints ) {
    	for( SnPoint newPoint : newPoints ) {
    		this.add(newPoint);
    	}
    }
    
    public final void add( SnPoint newPoint ) {
    	if( this.snBottom.exist() ) {
    		this.snBottom.get().add( newPoint );
    	} else {
            this.snBottom.set( newPoint );
    	}
    }

    public final void get( String key, Consumer < SnPoint > consumer ) {
        SnPoint snPoint = get( key );
        if( snPoint != null ) consumer.accept( snPoint );
    }
    
    public final SnPoint get( String key ) {
        SnPoint start = this.snBottom.get();
        return start != null ? start.get( key ) : null;
    }

    // ******************************************************************

    /**
     * Nur dann wenn alle Attribute mit den Attributen des node's übereinstimmen
     * wird node zurückgegeben.
     *
     * @param key
     * @param attributes
     * @return Der gefundene Knoten
     */
    
    public final SnPoint get( String key, KeyValue... attributes ) {
        SnPoint first = this.snBottom.get();
        return first != null ? first.get( key, attributes ) : null;
    }

    // ******************************************************************

    public final void getValue( String key, Consumer < String > consumer ) {
        get( key, node -> {
            String value = node.value.get();
            if ( value != null ) consumer.accept( value );
        });
    }

    // ******************************************************************

    public final void forEach( Consumer < SnPoint > consumer ) {
        forEach( ITER.LEFT_TO_RIGHT, consumer);
    }

    public final void forEach( ITER iterType, Consumer < SnPoint > consumer ) {
    	this.snBottom.get( first -> first.forEach( iterType, consumer ) );
    }

    // ******************************************************************

    public final boolean exist() {
        return this.snBottom.exist();
    }

    // ******************************************************************

    public final void remove( String key ) {
    	get( key, snPoint -> snPoint.remove() );
    }
    
}
