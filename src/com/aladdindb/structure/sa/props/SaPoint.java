package com.aladdindb.structure.sa.props;

import java.util.Iterator;

import com.aladdindb.structure.KeyValue;
import com.aladdindb.structure.sa.SaIteratorLeftToRight;
import com.aladdindb.structure.sn.SnPoint;

/**
*
* @author Macit Kandemir
*/
public class SaPoint implements Iterable < SaPoint >  {
	
	private final Sa sa;
	

    public final SaKey			key;
    public final SaValue		value;
    public final SaLeft			left;
    public final SaRight		right;


    
    //*********************************************************
    //                   Constructors
    //*********************************************************
    
    /**
     * Create a new "Sa" Line object and 
     * encapsulate it in this object.
     * 
     * @param key
     * @param value
     */
	public SaPoint ( KeyValue keyValue ) {
		this( keyValue.key, keyValue.value );
	}
	
	public SaPoint ( String key , String value ) {
		this( new Sa() );

		this.key	.set( key 	);
		this.value	.set( value	);
	}
	
    /**
     * Encapsulate a existing "Sa" Line object.
     * 
     * @param sa
     */
	public SaPoint ( Sa sa ) {
        this.sa = sa;

        this.key 		= new SaKey		( this );
        this.value		= new SaValue		( this );
        this.left		= new SaLeft		( this );
        this.right		= new SaRight		( this );
        
	}

    //*********************************************************
    //                   
    //*********************************************************

	public final Sa getSA ( ) {
		return sa;
	}
	
    //*********************************************************
    //                   	Owner
    //*********************************************************

	public final void setOwner ( SnPoint snPoint ) {
		sa.owner = snPoint == null ? null : snPoint.getSN ( );
	}
	
	public final SnPoint getOwner ( ) {
		return sa.owner == null ? null : new SnPoint ( sa.owner );
	}
	
    //*********************************************************
    //                   	Get
    //*********************************************************

	/**
	 * 
	 * @param key
	 * @return
	 */
    public final SaPoint get ( String key ) {
		return this.getRecursive ( key , this.getStart ( ) );
	}
	
	private final SaPoint getRecursive ( String key , SaPoint saPoint ) {
		if ( saPoint != null ) { 
			return saPoint.key.equals( key ) ? 
					saPoint :	this.getRecursive ( key , saPoint.right.get() );
		}
		return null;
	}

    // ******************************************************************
    // *              			Set
    // ******************************************************************
    
	public final void set( KeyValue... keyValues ) {
		for( KeyValue kv: keyValues ) {
			this.set( new SaPoint( kv ) );
		}
	}
	
    public final void set( SaPoint... saPoints ) {
    	for( SaPoint sAttribute: saPoints ) set( sAttribute );
    }
    
    public final void set( String key, String value ) {
    	this.set( new SaPoint( key, value ) );
    }

    public final void set( SaPoint newSaPoint ) {
        SaPoint exist = get( newSaPoint.key.get());
        if ( exist != null ) {
            exist.value.set( newSaPoint.value.get() );
        } else {
            add( newSaPoint );
        }
    }
    
    // ******************************************************************
    // *              				Add
    // ******************************************************************
	
	public final void add ( SaPoint saPoint ) {
        	var end = this.getEnd();
        	end.link( end, saPoint );
			saPoint.setOwner ( this.getOwner() );
	}
    
    // ******************************************************************
    // *              		    isAllone
    // ******************************************************************
	
	public final boolean isAllone ( ) {
		return !left.exist() && !right.exist();
	}
	
    // ******************************************************************
    // *              				Last
    // ******************************************************************
	
	public final boolean isEnd ( ) {
		return !right.exist();
	}

	public final SaPoint getEnd ( ) {
		return  getEndRecursive ( this );
	}
	
	private SaPoint getEndRecursive ( SaPoint saPoint ) {
		if ( saPoint != null ) {
			var right = saPoint.right.get();
			if ( right != null ) return this.getEndRecursive ( right );
		}
		return saPoint;
	}
	
    // ******************************************************************
    // *              			First 
    // ******************************************************************
	
	public final boolean isStart ( ) {
		return !left.exist();
	}

	public final SaPoint getStart() {
        return getStartRecursive( this );
    }

    private SaPoint getStartRecursive( SaPoint saPoint ) {
    	if( saPoint != null ) {
    		var left = saPoint.left.get();
            if ( left != null ) return this.getStartRecursive( left );
    	}
        return saPoint;
    }
	
    // ******************************************************************
    // *              				replace
    // ******************************************************************
	
	public final void replace ( SaPoint saPoint ) {
		SaPoint left 	= this.left.get();
		SaPoint right 	= this.right.get();
		
		SnPoint owner = getOwner ( );
		
		saPoint.left	.set( left );
		saPoint.right	.set( right );
		
		saPoint.setOwner ( owner );
		
		if ( left != null 	) left	.right	.set( saPoint );
		if ( right != null 	) right	.left	.set( saPoint );
		
		if ( isStart ( ) ) owner.attributes.snAttribute.set( saPoint ); 
		
		this.left	.set( null );
		this.right	.set( null );
		
		setOwner ( null );
	}
	
    // ******************************************************************
    // *              			remove
    // ******************************************************************
	
    public final void remove( String key ) {
        SaPoint saPoint = get(key);
        if (saPoint != null) saPoint.remove();
    }
	
	public final void remove ( ) {
		SaPoint 	left 	= this.left		.get();
		SaPoint 	right 	= this.right	.get();
		
		SnPoint 		owner 	= getOwner ( ); 
		
		if ( isAllone ( ) ) {
			owner.attributes.snAttribute.set( null );
		} else {
			if ( isStart ( ) ) {
				owner.attributes.snAttribute.set( right );
				right.left.set ( null );
			} else if ( isEnd ( ) ) {
				left.right.set ( null );
			} else {
				link ( left , right );
			}
		}
		this.left	.set ( null );
		this.right	.set ( null );
		
		setOwner ( null );
	}
	
    public final boolean exist( String key ) {
        return this.get(key) != null;
    }
	
    // ******************************************************************
    // *              			    link
    // ******************************************************************
    
	/**
	 * Zwei Attribute werden miteinander verlinkt.
	 * 
	 * @param left
	 * @param right
	 */
	
	public final void link ( SaPoint left , SaPoint right ) {
		if ( left 	!= null ) left	.right	.set( right );
		if ( right 	!= null ) right	.left	.set( left 	);
	}
	
    // ******************************************************************
    // *              			 Iterator
    // ******************************************************************
	
	@Override
	public Iterator< SaPoint > iterator ( ) {
		return new SaIteratorLeftToRight( getEnd() );  
	}
	
}
