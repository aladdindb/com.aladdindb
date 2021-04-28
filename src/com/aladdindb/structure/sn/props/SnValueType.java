package com.aladdindb.structure.sn.props;

import com.aladdindb.structure.sn.SnByteFeature;
import com.aladdindb.structure.sn.SnPoint;

/**
*
* @author Macit Kandemir
*/
public class SnValueType extends SnByteFeature {

	
    public static final byte AUTO_INIT				 = 0;
	
    /**
     * <pre>{@code 
     *      <Color red="150" green="200" blue="150"/>
     * }</pre>
     */
    public static final byte SINGLE_LINE               = 51;

    /**
     * <pre>{@code 
     *  <properties>
     *      <Color red="150" green="200" blue="150">
     *      <Color red="150" green="200" blue="150">
     *  </properties>
     * }</pre>
     */
    public static final byte STAND_ALONE_LINE       	= 52;

    /**
     * <pre>{@code 
     *      <text>My Text</text>
     * }</pre>
     * 
     * <pre>{@code 
     *      <Color red="150" green="200" blue="150"></color>
     * }</pre>
     */
    public static final byte SINGLE_LINE_CLOSE            = 54;

    /**
     * <pre>{@code 
     *  <text>
     *      1. My Text
     *      2. My Text
     *  </text>
     * }</pre>
     * 
     * <pre>{@code 
     *    <Background>
     *        <Color red="150" green="200" blue="150" alpha="100"/>
     *    </Background>
     * }</pre>
     * 
     */
    public static final byte MULTI_LINE               		= 55;

    /**
     * <pre>{@code 
     *  <data>
     *      <![CDATA[
     *          [<-| 1. Zeile Hasdf  asdf  asdf sadf   |->]
     *          [<-|   2. Zeile Hasdf  asdf  asdf sadf      |->]
     *      ]]>
     *  </data>
     * }</pre>
     */
    public static final byte CDATA                   	= 56;
    
    
    
	public SnValueType( SnPoint node  ) {
		super( node );
	}	

    public boolean isValueType( byte valueType ) {
        return sn.valueType == valueType;
    }
	
    @Override
    protected byte getReal() {
    	return sn.valueType;
    }
    
    @Override
    protected void setReal( byte value ) {
    	this.sn.valueType = value;
    }
	
    
    
	
}
