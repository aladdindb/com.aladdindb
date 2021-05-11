package com.aladdindb.util;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.function.Consumer;

/**
*
* @author Macit Kandemir
*/
public final class Util {
	

    public static String createString(int len) {
        return createString(len, ' ');
    }

    public static String createString(int len, char fill) {
        if (len < 1) {
            return "";
        }
        char[] cs = new char[len];
        Arrays.fill(cs, fill);
        return new String(cs);
    }

    public static String createString(byte[] bytes) {
        return createString(bytes, "UTF8");
    }

    public static String createString(byte[] bytes, String charSetName) {
        try {
            return new String(bytes, charSetName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        };
        return null;
    }

    public static byte[] createBytes(String str) {
        return createBytes(str, "UTF8");
    }

    public static byte[] createBytes(String str, String charSetName) {
        byte[] rv = new byte[0];
        try {
            if (str != null) {
                rv = str.getBytes(charSetName);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        };
        return rv;
    }

    public static String get2CcharString(int i) {
        return i > 9 ? Integer.toString(i) : "0" + Integer.toString(i);
    }


    //*****************************************************************************
    //								Hash Code
    //*****************************************************************************
    
    public static String createAlphabeticHashCode(int length) {
        return createHashCode("abcdefghijklmnoprstuwxyz", length);
    }

    public static String createAlphaNumericHashCode(int length) {
        return createHashCode("abcdefghijklmnoprstuwxyz123456789", length);
    }

    public static String createAlphaNumericInclUpperCaseHashCode(int length) {
        return createHashCode("abcdefghijklmnoprstuwxyzABCDEFGHIJKLMNOPRSTUWXYZ0123456789", length);
    }

    public static String createHashCodeForProject() {
        return createHashCode("abcdefghijklmnoprstuwxyz0123456789", 40);
    }

    public static String createHashCode(String str, int length) {
        String rv = "";
        for (int i = 0; i < length; i++) {
            int z = (int) Math.round(Math.random() * (str.length() - 1));
            rv += str.substring(z, z + 1);
        }
        return rv;
    }

    //*****************************************************************************
    //							
    //*****************************************************************************

	public static <T> void ifNotNull( T o, Consumer<T> consumer ) {
		if( o != null ) consumer.accept(o);
	}
	
	public static boolean isUsable( String value ) {
		return value != null && !value.trim().isEmpty();
	}

	
	public static void stopWatch( Runnable process ) {
		System.out.println("---------------");
		System.out.println(" Start Process ");
		System.out.println("---------------");
		System.out.println("\n");
		var start = System.currentTimeMillis();
		process.run();
		long stop = System.currentTimeMillis()-start;
		System.out.println("\n\n");
		System.out.println("---------------------------");
		System.out.println(" End Process :"+stop+" ms"	);
		System.out.println("---------------------------");
	}
	
	//****************************************************
	//						System
	//****************************************************
	
	public static final String getUserHome() {
		return System.getProperty("user.home");
	}
	
	public static final String getJavaHome() {
		return System.getProperty("java.home");
	}

	public static final String getUserDir() {
		return System.getProperty("user.dir");
	}
	
	
}
