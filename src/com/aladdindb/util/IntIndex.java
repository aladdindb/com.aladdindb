package com.aladdindb.util;


/**
*
* @author Macit Kandemir
*/
public class IntIndex {
    
    private int i 			= 0;
    private int startIndex 	= 0;

    public IntIndex() {
    	this( 0 );
	}
    
    public IntIndex( int startIndex ) {
    	this.startIndex = (startIndex-1);
    	this.reset();
	}
    
    public int inc() {
        return inc(1);
    }
    
    public int inc(int step) {
        i+=step;
        return i;
    }

    public int dec() {
        return dec(1);
    }
    
    public int dec(int step) {
        i-=step;
        return i;
    }
    
    public void reset() {
        this.i = this.startIndex;
    }
    
    public int get() {
        return i;
    }

    @Override
    public String toString() {
    	return Integer.toString(i);
    }
    
}
