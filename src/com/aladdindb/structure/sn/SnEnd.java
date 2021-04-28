package com.aladdindb.structure.sn;

import java.util.function.Consumer;

/**
*
* @author Macit Kandemir
*/
public class SnEnd extends SnFeature {

	
	public SnEnd( SnPoint snPoint ) {
		super( snPoint );
	}
	
    public final boolean is() {
        return !this.node.right.exist();
    }

    public final void get( Consumer< SnPoint> consumer ) {
    	var rv = get();
    	if( rv != null )consumer.accept(node);
    }
    
    public final SnPoint get() {
        return getEndRecursive( this.node );
    }

    private SnPoint getEndRecursive( SnPoint snPoint ) {
        if (snPoint != null) {
            SnPoint right = snPoint.right.get();
            if (right != null) {
                return this.getEndRecursive(right);
            }
        }
        return snPoint;
    }


}
