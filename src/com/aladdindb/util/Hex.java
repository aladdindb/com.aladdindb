package com.aladdindb.util;

/**
 *
 * @author Macit Kandemir
 */
public class Hex {

	
    public static String getBytesAsHexString( byte[] bytes ) {
        String rv = "";
        for ( int i = 0; i < bytes.length; i++ ) {
            rv += create2CharHex( bytes[i] );
        }
        return rv;
    }

    public static String create2CharHex( int value ) {
        return create2CharHex( (byte) value );
    }
    
    public static String create2CharHex( byte value ) {
        String hex = Integer.toHexString(value);
        return value < 16 ? "0" + hex : hex;
    }

    public static String create4CharHex( int value ) {
        return createXCharHex(4, value);
    }
    
    public static String createXCharHex( int chars, int value ) {
        String str =  Integer.toHexString( value );
        var l = str.length();
        if( l > chars ) {
            str = str.substring( l- chars );
        } else {
            str = Util.createString( chars -l , '0')+str;
        }
        
        return str;
    }
    
    public static String createUtf8Hex( int value ) {
        String rv = Integer.toHexString( value );
        return Util.createString( 4 - rv.length(), '0') + rv;
    }

    


    
}
