package com.aladdindb.structure.types;

import java.util.function.Consumer;

import com.aladdindb.structure.sn.SnFeature;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.util.Var;


/**
 *
 * @author Macit Kandemir
 * @param <T>
 */
public abstract class SnAttributeParser < T > extends SnFeature {
    
    public SnAttributeParser( SnPoint node ) {
    	super( node );
	}
    
    //****************************************************************
    //
    //****************************************************************

    public final void get( Enum<?> key, Var < T > target  ) {
        this.get( key, target :: set );
    }

    public final void get( String key, Var < T > target  ) {
        this.get( key, target :: set );
    }
    
    public final void get( Enum<?> key,  Consumer < T > consumer ) {
        this.get( key.name(), consumer );
    }
    
    public final void get( String key, Consumer < T > consumer ) {
        var rv = this.get( key );
        if( rv != null ) consumer.accept( rv );
    }
    
    public abstract T get( String key );
    
    //****************************************************************
    //
    //****************************************************************

    public final void set( String key, Var < T > src ) {
        src.get( value -> this.set( key, value ) );
    }

    public final void set( Enum<?> key, Var < T > src ) {
        src.get( value -> this.set( key, value ) );
    }
    
    public final void set( Enum<?> key, T value ) {
        this.set(key.name(), value );
    }
    
    public abstract void set( String key, T value );
    
    
}
