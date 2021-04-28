package com.aladdindb.structure.xml;

/**
 * 
 * @version 2.0, 2019/05/23
 * @author Macit Kandemir
 */
public class XmlLineStart {


    public static final byte NONE               = 0;
    
    public static final byte CDATA_OPEN         = 50;
    public static final byte CDATA_LINE_OPEN    = 51;
    public static final byte CDATA_CLOSE        = 52;
    public static final byte TAG_START          = 53;
    public static final byte TAG_CLOSE_START    = 54;
    
    
    public byte getType( StringBuilder line ) {
        
        if( line.indexOf( "<![CDATA["   ) == 0 ) return CDATA_OPEN;
        if( line.indexOf( "]]>"         ) == 0 ) return CDATA_CLOSE;
        if( line.indexOf( "[<-|"        ) == 0 ) return CDATA_LINE_OPEN;
        if( line.indexOf( "</"          ) == 0 ) return TAG_CLOSE_START;
        if( line.indexOf( "<"           ) == 0 ) return TAG_START;
        
        return NONE;
    }

}
