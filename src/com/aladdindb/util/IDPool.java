package com.aladdindb.util;


/**
*
* @author Macit Kandemir
*/
public final class IDPool {

    private final int size;
    private final boolean pool[];

    public IDPool() {
        this(100000);
    }

    public IDPool(int size) {
        this.size = size;
        pool = new boolean[size];
    }

    /**
     * Eine freie ID wird zurückgegeben und gleichzeitig als belegt markiert.
     *
     * @return das freie ID
     */
    public Integer getFreeID() {
        for (int i = 0; i < size; i++) {
            if (!pool[i]) {
                return reserve(i);
            }
        }
        return null;
    }

    /**
     * Die übergebene ID wird als belegt markiert.
     *
     * @param i
     * @return Die als belegt markierte ID
     */
    public int reserve(int i) {
        pool[i] = true;
        return i;
    }

    /**
     * Die übergebene ID wird wieder freigegeben
     *
     * @param i Die freizugebende ID
     * @return Die freigewordene ID
     */
    public int clear(int i) {
        pool[i] = false;
        return i;
    }

}
