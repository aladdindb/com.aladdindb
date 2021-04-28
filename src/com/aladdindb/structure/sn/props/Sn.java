package com.aladdindb.structure.sn.props;

import com.aladdindb.structure.sa.props.Sa;

/**
 * Durch Verbindungen von Sn Objekten können komplexe Datenstrukturen 
 * im RAM gehalten werden.
 * 
 * @version 1.0, 04/08/05
 * @version 2.0, 2010/03/27
 * @author Macit Kandemir
 * 
 */
public class Sn  {
    
    protected byte     	valueType   = SnValueType.AUTO_INIT;

    protected String	key;
    protected String	value;

    protected Sa		attribute;

    protected Sn		top;
    
    protected Sn		left;
    protected Sn		right;

    protected Sn		bottom;


	
}
