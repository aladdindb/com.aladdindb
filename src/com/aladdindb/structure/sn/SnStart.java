package com.aladdindb.structure.sn;

import java.util.function.Consumer;

/**
*
* @author Macit Kandemir
*/
public class SnStart extends SnFeature {

	
	public SnStart( SnPoint snPoint ) {
		super( snPoint );
	}
	
    public final boolean is() {
        return !node.left.exist();
    }

    public final void get( Consumer< SnPoint> consumer ) {
    	var rv = get();
    	if( rv != null )consumer.accept(node);
    }
    
    public final SnPoint get() {
        return getStartRecursive( this.node );
    }

    private SnPoint getStartRecursive( SnPoint snPoint) {
        SnPoint left = snPoint.left.get();
        if ( left != null ) return this.getStartRecursive( left );
        return snPoint;
    }


}
