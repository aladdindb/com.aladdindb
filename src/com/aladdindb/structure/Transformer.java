package com.aladdindb.structure;

import java.util.function.Consumer;

import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.util.Var;

/**
 *
 * @author Macit Kandemir
 * @param <STORE>
 */
public abstract class Transformer < STORE extends Store < STORE > > {

    private final String key;

    public Transformer( Enum<?> key ) {
        this( key.name() );
    }
    
    public Transformer( String key ) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    //****************************************************************
    //
    //****************************************************************
    
    public abstract STORE newStore();
    
    //****************************************************************
    //
    //****************************************************************

    public final STORE toStore( SnPoint src ) {
        return Transformer.this.toStore( src , newStore() );
    }

    public final void toStore( SnPoint src , Consumer < STORE > target ) {
        STORE model = Transformer.this.toStore( src );
        if( model != null ) target.accept( model );
    }

    public final void toStore( SnPoint src , Var < STORE > target ) {
        Transformer.this.toStore( src, target :: set );
    }
    
    public abstract STORE  toStore( SnPoint src , STORE target );
    
    //****************************************************************
    //
    //****************************************************************

    public final STORE toStoreFromParent( SnPoint src ) {
        Var < STORE > target = new Var<>();
        src.children.get( getKey(), node -> {
            Transformer.this.toStore( node, target :: set );
        });
        return target.get();
    }
    
    public final void toStoreFromParent( SnPoint src,  STORE  target ) {
    	toStoreFromParent( src, target :: fill );
    }
    
    public final void toStoreFromParent( SnPoint src, Consumer < STORE > target ) {
        STORE model = Transformer.this.toStoreFromParent( src );
        if( model != null ) target.accept( model );
    }

    public final void toStoreFromParent( SnPoint parentNode, Var < STORE > target ) {
        Transformer.this.toStoreFromParent( parentNode, target :: set );
    }

    
    //****************************************************************
    //
    //****************************************************************

    public final void toNode( Var < STORE > src, Consumer < SnPoint > target  ) {
        src.get( model -> Transformer.this.toNode( model, target ) );
    }
    
    public final void toNode( STORE src, Consumer < SnPoint > target ) {
        var node = Transformer.this.toNode( src );
        if( node != null ) target.accept( node );
    }

    public final SnPoint toNode( STORE src ) {
        return Transformer.this.toNode( src, new SnPoint( getKey()) );
    }
    
    public final SnPoint toNode( Var < STORE > src, SnPoint target ) {
        src.get( model -> {
            Transformer.this.toNode( model, target);
        });
        return target;
    }

    public abstract SnPoint toNode( STORE src , SnPoint target );
    
    
    //****************************************************************
    //
    //****************************************************************
    
    /**
     * Holt das generische PROP Objekt aus dem Var Objekt und übergibt es
     * der Methode  
     * @see #parseToParent(com.xelara.core.Var, com.xelara.core.structure.snode.SNode).  
     * 
     * @param src       Enthält das zu parsende generische PROP Objekt.
     * @param target    Älternknoten als Ziel Konoten.
     */
    
    public final void toParentNode( Var < STORE > src, SnPoint target ) {
        src.get( model -> {
            toParentNode( model, target );
        });
    }
    
    /**
     * Der aus dem generischem PROP Objekt erzeugter Knoten wird nach dem
     * Parsen, dem Ältern Konoten hinzugefügt.
     * 
     * @param src          Das zu parsende PROP Objekt.
     * @param target    Älternknoten als Ziel Konoten. 
     */
    
    public final void toParentNode( STORE src, SnPoint target ) {
        this.toNode( src, target.children :: add );
    } 

    
    
    
}
