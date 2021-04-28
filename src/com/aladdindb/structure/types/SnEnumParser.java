package com.aladdindb.structure.types;

import java.util.function.Consumer;

import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.util.Var;

/**
*
* @author Macit Kandemir
*/
public class SnEnumParser extends SnAttributeParser < Enum<?> > {
    

	public SnEnumParser( SnPoint node ) {
    	super(node);
	}

    //****************************************************************
    //
    //****************************************************************

    @Override
    public Enum<?> get( String key ) {
        var value = node.attributes.getValue( key );
        return value != null && !value.trim().isEmpty() ? Enum.valueOf( Enum.class, value ) : null  ;
    }

    
    @Override
    public void set( String key, Enum<?> value ) {
        node.attributes.set( key, value.name() );
    }
    
    //****************************************************************
    //
    //****************************************************************
	
    public <T extends Enum<T> > void get( Enum<?> key, Var < T > target, Class<T> enumType ) {
    	this.get( key, enumType, target :: set );
    }

    public <T extends Enum<T> > void get( String key, Var < T > target, Class<T> enumType ) {
    	this.get( key, enumType, target :: set );
    }
	
    public <T extends Enum<T> > void get( Enum<?> key, Class<T> enumType, Consumer<T> consumer ) {
    	this.get( key.name(), enumType, consumer);
    }

    public <T extends Enum<T> > void get( String key, Class<T> enumType, Consumer<T> consumer ) {
    	var rv = get( key, enumType );
    	if( rv != null ) consumer.accept( rv );
    }
    
    public <T extends Enum<T> > T get( String key, Class<T> enumType ) {
        var value = node.attributes.getValue( key );
        return value != null && !value.trim().isEmpty() ? Enum.valueOf( enumType , value ) : null  ;
    }

    //****************************************************************
    //
    //****************************************************************

    public < T extends Enum< T > > void set( Enum<?> key, Var <T> valueVar, Class<T> enumType ) {
    	valueVar.get( value -> this.set( key, value, enumType ) );
    }

    public < T extends Enum< T > > void set( String key, Var< T > valueVar, Class<T> enumType ) {
    	valueVar.get( value -> this.set( key, value, enumType ) );
    }


    public <T extends Enum<T> > void set( Enum<?> key, T value, Class<T> enumType ) {
    	this.set( key.name(), value, enumType );
    }

    public <T extends Enum<T> > void set( String key, T value, Class<T> enumType ) {
    	node.attributes.set( key, value.name() );
    }
    
    //****************************************************************
    //
    //****************************************************************
	
    
}
