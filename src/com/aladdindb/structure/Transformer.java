package com.aladdindb.structure;

import java.util.function.Consumer;

import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.util.Var;

/**
 *
 * @author Macit Kandemir
 * @param <DATA_MODEL>
 */
public abstract class Transformer < DATA_MODEL extends DataModel < DATA_MODEL > > {

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
    
    public abstract DATA_MODEL newModel();
    
    //****************************************************************
    //
    //****************************************************************

    public final DATA_MODEL toModel( SnPoint src ) {
        return Transformer.this.toModel( src , newModel() );
    }

    public final void toModel( SnPoint src , Consumer < DATA_MODEL > target ) {
        DATA_MODEL model = Transformer.this.toModel( src );
        if( model != null ) target.accept( model );
    }

    public final void toModel( SnPoint src , Var < DATA_MODEL > target ) {
        Transformer.this.toModel( src, target :: set );
    }
    
    public abstract DATA_MODEL  toModel( SnPoint src , DATA_MODEL target );
    
    //****************************************************************
    //
    //****************************************************************

    public final DATA_MODEL toModelFromParent( SnPoint src ) {
        Var < DATA_MODEL > target = new Var<>();
        src.children.get( getKey(), node -> {
            Transformer.this.toModel( node, target :: set );
        });
        return target.get();
    }
    
    public final void toModelFromParent( SnPoint src,  DATA_MODEL  target ) {
    	toModelFromParent( src, target :: fill );
    }
    
    public final void toModelFromParent( SnPoint src, Consumer < DATA_MODEL > target ) {
        DATA_MODEL model = Transformer.this.toModelFromParent( src );
        if( model != null ) target.accept( model );
    }

    public final void toModelFromParent( SnPoint parentNode, Var < DATA_MODEL > target ) {
        Transformer.this.toModelFromParent( parentNode, target :: set );
    }

    
    //****************************************************************
    //
    //****************************************************************

    public final void toNode( Var < DATA_MODEL > src, Consumer < SnPoint > target  ) {
        src.get( model -> Transformer.this.toNode( model, target ) );
    }
    
    public final void toNode( DATA_MODEL src, Consumer < SnPoint > target ) {
        var node = Transformer.this.toNode( src );
        if( node != null ) target.accept( node );
    }

    public final SnPoint toNode( DATA_MODEL src ) {
        return Transformer.this.toNode( src, new SnPoint( getKey()) );
    }
    
    public final SnPoint toNode( Var < DATA_MODEL > src, SnPoint target ) {
        src.get( model -> {
            Transformer.this.toNode( model, target);
        });
        return target;
    }

    public abstract SnPoint toNode( DATA_MODEL src , SnPoint target );
    
    
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
    
    public final void toParentNode( Var < DATA_MODEL > src, SnPoint target ) {
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
    
    public final void toParentNode( DATA_MODEL src, SnPoint target ) {
        this.toNode( src, target.children :: add );
    } 

    
    
    
}
