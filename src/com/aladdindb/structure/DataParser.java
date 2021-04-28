package com.aladdindb.structure;

import java.util.function.Consumer;

import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.util.Var;

/**
 *
 * @author Macit Kandemir
 * @param <MODEL>
 */
public abstract class DataParser < MODEL extends DataModel < MODEL > > {

    private final String key;

    public DataParser( Enum<?> key ) {
        this( key.name() );
    }
    
    public DataParser( String key ) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    //****************************************************************
    //
    //****************************************************************
    
    public abstract MODEL newModel();
    
    //****************************************************************
    //
    //****************************************************************

    public final MODEL toModel( SnPoint src ) {
        return DataParser.this.toModel( src , newModel() );
    }

    public final void toModel( SnPoint src , Consumer < MODEL > target ) {
        MODEL model = DataParser.this.toModel( src );
        if( model != null ) target.accept( model );
    }

    public final void toModel( SnPoint src , Var < MODEL > target ) {
        DataParser.this.toModel( src, target :: set );
    }
    
    public abstract MODEL  toModel( SnPoint src , MODEL target );
    
    //****************************************************************
    //
    //****************************************************************

    public final MODEL toModelFromParent( SnPoint src ) {
        Var < MODEL > target = new Var<>();
        src.children.get( getKey(), node -> {
            DataParser.this.toModel( node, target :: set );
        });
        return target.get();
    }
    
    public final void toModelFromParent( SnPoint src,  MODEL  target ) {
    	toModelFromParent( src, target :: fill );
    }
    
    public final void toModelFromParent( SnPoint src, Consumer < MODEL > target ) {
        MODEL model = DataParser.this.toModelFromParent( src );
        if( model != null ) target.accept( model );
    }

    public final void toModelFromParent( SnPoint parentNode, Var < MODEL > target ) {
        DataParser.this.toModelFromParent( parentNode, target :: set );
    }

    
    //****************************************************************
    //
    //****************************************************************

    public final void toNode( Var < MODEL > src, Consumer < SnPoint > target  ) {
        src.get( model -> DataParser.this.toNode( model, target ) );
    }
    
    public final void toNode( MODEL src, Consumer < SnPoint > target ) {
        var node = DataParser.this.toNode( src );
        if( node != null ) target.accept( node );
    }

    public final SnPoint toNode( MODEL src ) {
        return DataParser.this.toNode( src, new SnPoint( getKey()) );
    }
    
    public final SnPoint toNode( Var < MODEL > src, SnPoint target ) {
        src.get( model -> {
            DataParser.this.toNode( model, target);
        });
        return target;
    }

    public abstract SnPoint toNode( MODEL src , SnPoint target );
    
    
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
    
    public final void toParentNode( Var < MODEL > src, SnPoint target ) {
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
    
    public final void toParentNode( MODEL src, SnPoint target ) {
        this.toNode( src, target.children :: add );
    } 

    
    
    
}
