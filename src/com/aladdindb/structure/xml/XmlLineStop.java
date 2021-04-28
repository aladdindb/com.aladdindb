package com.aladdindb.structure.xml;

/**
 * 
 * @version 2.0, 2019/05/23
 * @author Macit Kandemir
 */
public class XmlLineStop {
    
    public static final byte NONE               = 0;

    public static final byte CDATA_LINE_CLOSE   = 51;
    public static final byte CDATA_CLOSE        = 52;
    public static final byte TAG_CLOSE          = 53;
    public static final byte TAG_STOP           = 54;
    
    public byte getType( StringBuilder line) {
        int l = line.length();
        
        if( line.lastIndexOf( "|->]"        ) == ( l - 4 ) ) return CDATA_LINE_CLOSE;
        if( line.lastIndexOf( "]]>"         ) == ( l - 3 ) ) return CDATA_CLOSE;
        if( line.lastIndexOf( "/>"          ) == ( l - 2 ) ) return TAG_CLOSE;
        if( line.lastIndexOf( ">"           ) == ( l - 1 ) ) return TAG_STOP;

        return NONE;
    }

}
