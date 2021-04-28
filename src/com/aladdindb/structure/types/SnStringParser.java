package com.aladdindb.structure.types;

import com.aladdindb.structure.sn.SnPoint;

/**
 *
 * @author Macit Kandemir
 */
public class SnStringParser extends SnAttributeParser < String > {

    public SnStringParser( SnPoint node ) {
    	super(node);
	}
    
    @Override
    public String get( String key ) {
        String rv = node.attributes.getValue( key );
        return   rv ;
    }
    
    @Override
    public void set( String key, String value ) {
        node.attributes.set( key, value );
    }
    
}
