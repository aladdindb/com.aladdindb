package com.aladdindb.structure.types;

import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.sn.props.SnValueType;

/**
 *
 * @author Macit Kandemir
 */
public class SnCDataParser extends SnAttributeParser < String > {
    
    
	public SnCDataParser( SnPoint node ) {
    	super(node);
	}
	
    @Override
    public String get( String key ) {
    	SnPoint childNode = node.children.get( key );
    	if( childNode != null )  {
    		return childNode.value.get();
    	}
        return null;
    }
    
    @Override
    public void set( String key, String value ) {
    	var newNode = new SnPoint( key, value );
    	newNode.valueType.set(SnValueType.CDATA);
    	node.children.set( newNode );
    }
    
}
