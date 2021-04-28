package com.aladdindb.structure.sn;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

import com.aladdindb.structure.KeyValue;
import com.aladdindb.structure.SElement;
import com.aladdindb.structure.sa.Sattributes;
import com.aladdindb.structure.sa.props.SaPoint;
import com.aladdindb.structure.sn.props.Sn;
import com.aladdindb.structure.sn.props.SnKey;
import com.aladdindb.structure.sn.props.SnLeft;
import com.aladdindb.structure.sn.props.SnRight;
import com.aladdindb.structure.sn.props.SnTop;
import com.aladdindb.structure.sn.props.SnValue;
import com.aladdindb.structure.sn.props.SnValueType;

/**
 * Provide as an encapsulation for the 
 * creation and administration of SN object structures.
 *
 * @version 1.0, 2005/03/07
 * @author Macit Kandemir
 *
 */
public class SnPoint implements Iterable< SnPoint >, SElement {

	public enum ITER { LEFT_TO_RIGHT, RIGHT_TO_LEFT }

	
    private ITER iterType = ITER.LEFT_TO_RIGHT;

    public final SnValueType	valueType;
    
    public final SnKey			key;
    public final SnValue		value;
    
    public final Sattributes 	attributes;

    public final SnTop			parent;
    
    public final SnLeft			left;
    public final SnRight		right;
    
    public final SnChildren 	children;
    
    public final SnStart		start;
    public final SnEnd			end;
    
    
    /**
     * The real encapsulated "Sn" structure object.
     */
    private Sn sn;

    
    // ******************************************************************
    // *                      Constructors      
    // ******************************************************************
    
	public SnPoint( String key ) {
		this( key, "" );
	}

	/**
     * Create a new "Sn" structure object and 
     * encapsulate it in this object.
	 */
    public SnPoint() {
        this( "", "" );
    }

    /**
     * Create a new "Sn" structure object and 
     * encapsulate it in this object.
     * 
     * @param key
     * @param value
     */
    public SnPoint( String key, String value ) {
        this( new Sn() );
        
        this.key	.set( key 	);
        this.value	.set( value	);
    }

    /**
     * Encapsulate a existing "Sn" object structure.
     * 
     * @param sn
     */
    public SnPoint( SnPoint snode ) {
    	this( Objects.requireNonNull( snode ).getSN() );
    }

    public SnPoint( Sn sn ) {
    	Objects.requireNonNull( sn );
    	
        this.sn 		= sn;

        this.valueType 		= new SnValueType	( this ); 

        this.key 			= new SnKey			( this );
        this.value			= new SnValue		( this );
        this.parent			= new SnTop			( this );
        this.attributes 	= new Sattributes	( this );
        this.left			= new SnLeft		( this );
        this.right			= new SnRight		( this );
        this.children 		= new SnChildren	( this );
        this.start			= new SnStart		( this );
        this.end			= new SnEnd			( this );
    }

    
    /**
     * Get the real encapsulated node object.
     * 
     * @return
     */
    public final Sn getSN() {
        return sn;
    }

    // ******************************************************************
    // ******************************************************************
    // *          Neighbours Operations ( Horizontal Access )          
    // ******************************************************************
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

    /**
     * Der Knotenlinie wird ein weiterer Knoten als letzer Knoten hinzugefügt.
     * (Betrachtung von Links nach Rechts)
     *
     * @param snPoint
     */
    
//    public final void add( SnPoint... newPoints ) {
//    	for( SnPoint newPoint : newPoints ) {
//    		this.addFlat( newPoint );
//    	}
//    }
    
    public final void add( SnPoint snPoint ) {
        if (snPoint != null) {
            snPoint.parent.set( this.parent.get() );
            link( this.end.get(), snPoint );
        }
    }

    // ******************************************************************
    // *                           Get
    // ******************************************************************
    
    /**
     * Der Knoten mit dem übergebenem Schlüssel wird zurückgegeben.
     *
     * @param key Schlüssel der zur suchenden Knoten
     * @return Der gefundene Knoten.
     */
    
    public final SnPoint get( String key ) {
        return this.getRecursiv( this.start.get(), key );
    }

    private SnPoint getRecursiv( SnPoint node, String key ) {
        if ( node != null ) {
            if ( node.key.equals( key ) ) return node;
            return this.getRecursiv( node.right.get(), key );
        }
        return null;
    }

    /**
     * Hier wird die Knoten-Linie von links nach rechts durchlaufen. 
     * Wenn alle Attribute mit den Attributen des Knotens übereinstimmen  
     * wird der Knoten zurückgegeben.
     *
     * @param node
     * @param key
     * @param atrb
     * @return
     */
    
    public SnPoint get( String key, KeyValue... atrb ) {
        return this.getRecursive( this.start.get(), key, atrb );
    }

    private SnPoint getRecursive( SnPoint node, String key, KeyValue... atrb ) {
        if ( node != null ) {
            if ( node.key.equals(key) ) {
                boolean state = false;
                for ( KeyValue kv : atrb ) {
                    state = false;
                    SaPoint sa = node.attributes.get( kv.key );
                    if ( sa == null ) {
                        break;
                    }
                    if ( !sa.value.exist() ) {
                        break;
                    }
                    if ( !sa.value.equals( kv.value ) ) {
                        break;
                    }
                    state = true;
                }
                if (state) return node;
            }
            return this.getRecursive(node.right.get(), key, atrb);
        }
        return null;
    }

    /**
     * Zwei Knoten werden miteinander verbunden.
     *
     * @param leftNode
     * @param rightNode
     */
    public final void link( SnPoint left, SnPoint right ) {
        if (left != null) {
            left.right.set(right);
        }
        if (right != null) {
            right.left.set(left);
        }
    }

    /**
     * Der aktuelle Knoten wird durch den übergebenen ersetzt.
     * @param newPoint
     */
    public final void replace( SnPoint newPoint ) {
        SnPoint left 	= this.left		.get();
        SnPoint right 	= this.right	.get();
        SnPoint high 	= this.parent	.get();

        newPoint.left		.set( left );
        newPoint.right		.set( right );
        newPoint.parent		.set( high );

        if (left != null) {
            left.right.set(newPoint);
        }
        if (right != null) {
            right.left.set(newPoint);
        }
        if (this.start.is() && high != null) {
            high.children.snBottom.set( newPoint );
        }

        this.left	.set( null );
        this.right	.set( null );
        this.parent	.set( null );
    }
    
    // ******************************************************************

    public final boolean isAllone() {
        return !this.left.exist() && !this.right.exist() ? true : false;
    }
    
    // ******************************************************************

    public final void remove() {
        SnPoint left 		= this.left		.get();
        SnPoint right 	= this.right	.get();
        SnPoint parent 	= this.parent	.get();

        if ( isAllone() && this.parent.exist() ) {
            parent.children.snBottom.set( null );
        } else {
            if ( this.start.is() ) {
                parent.children.snBottom.set( right );
                right.left.set(null);
            } else if (this.end.is() ) {
                left.right.set(null);
            } else {
                link(left, right);
            }
        }
        this.left	.set( null );
        this.right	.set( null );
        this.parent	.set( null );
    }

    // ******************************************************************
    // *                        Flat Iteration
    // ******************************************************************

    public final void forEach( ITER iterType, Consumer< SnPoint > consumer ) {
    	this.iterType = iterType;
    	this.forEach( consumer );
    }
    
    @Override
    public final Iterator< SnPoint> iterator() {
        Iterator< SnPoint> rv = null;
        switch (iterType) {
            case LEFT_TO_RIGHT:
                rv = new SnLineIteratorLeftToRight(this);
                break;
            case RIGHT_TO_LEFT:
                rv = new SnLineIteratorRightToLeft(this);
                break;
        }
        return rv;
    }

}
