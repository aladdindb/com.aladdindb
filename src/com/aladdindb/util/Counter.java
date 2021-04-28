package com.aladdindb.util;


/**
*
* @author Macit Kandemir
*/
public class Counter {
    
    private int index = 0;
   
    
    public int inc() {
        return inc(1);
    }
    
    public int inc(int step) {
        index+=step;
        return index;
    }

    public int dec() {
        return dec(1);
    }
    
    public int dec(int step) {
        index-=step;
        return index;
    }
    
    public void reset() {
        index = 0;
    }
    
    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
    	return Integer.toString(index);
    }
    
}
