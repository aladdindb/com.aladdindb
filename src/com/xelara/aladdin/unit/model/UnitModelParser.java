package com.xelara.aladdin.unit.model;

import java.util.function.Consumer;

import com.xelara.core.Var;
import com.xelara.structure.snode.SNode;

/**
 *
 * @author Macit Kandemir
 * @param <UM>
 */
public abstract class UnitModelParser < UM extends UnitModel < UM > > {

    private final String key;

    public UnitModelParser( Enum<?> key ) {
        this( key.name() );
    }
    
    public UnitModelParser( String key ) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    //****************************************************************
    //
    //****************************************************************

    public UM parse( SNode node ) {
        return UnitModelParser.this.parse( node, newModel() );
    }

    public void parse( SNode node, Consumer < UM > consumer ) {
        UM model = UnitModelParser.this.parse( node );
        if( model != null ) consumer.accept( model );
    }

    public void parse( SNode node, Var < UM > target ) {
        UnitModelParser.this.parse( node, target :: setValue );
    }
    
    //****************************************************************
    //
    //****************************************************************

    
    public void parse( Var < UM > modelVar, Consumer < SNode > consumer  ) {
        modelVar.getValue( model -> UnitModelParser.this.parse( model, consumer ) );
    }
    
    public void parse( UM model, Consumer < SNode > consumer ) {
        var node = UnitModelParser.this.parse( model );
        if( node != null ) consumer.accept( node );
    }

    public SNode parse( UM model ) {
        return UnitModelParser.this.parse( model, new SNode( getKey()) );
    }
    
    public SNode parse( Var < UM > modelVar, SNode target ) {
        modelVar.getValue( model -> {
            UnitModelParser.this.parse( model, target);
        });
        return target;
    }

    //****************************************************************
    //
    //****************************************************************
    
    public abstract UM newModel();
    
    
    public abstract UM     	parse ( SNode   src ,UM   	target );
    public abstract SNode   parse ( UM    	src ,SNode  target );
    
    
    //****************************************************************
    //
    //****************************************************************

    public UM parseFromParent( SNode parentNode ) {
        Var < UM > target = new Var<>();
        parentNode.getChild( getKey(), node -> {
            UnitModelParser.this.parse( node, target :: setValue );
        });
        return target.getValue();
    }
    
    public void parseFromParent( SNode parentNode,  UM  model ) {
    	parseFromParent( parentNode, model :: fill );
    }
    
    public void parseFromParent( SNode parentNode, Consumer < UM > consumer ) {
        UM target = UnitModelParser.this.parseFromParent( parentNode );
        if( target != null ) consumer.accept( target );
    }

    public void parseFromParent( SNode parentNode, Var < UM > target ) {
        UnitModelParser.this.parseFromParent( parentNode, target :: setValue );
    }

    
    //****************************************************************
    //
    //****************************************************************
    
    /**
     * Holt das generische PROP Objekt aus dem Var Objekt und übergibt es
     * der Methode  
     * @see #parseToParent(com.xelara.core.Var, com.xelara.core.structure.snode.SNode).  
     * 
     * @param modelVar       Enthält das zu parsende generische PROP Objekt.
     * @param parentNode    Älternknoten als Ziel Konoten.
     */
    
    public void parseToParent( Var < UM > modelVar, SNode parentNode ) {
        modelVar.getValue( model -> {
            parseToParent( model, parentNode );
        });
    }
    
    /**
     * Der aus dem generischem PROP Objekt erzeugter Knoten wird nach dem
     * Parsen, dem Ältern Konoten hinzugefügt.
     * 
     * @param model          Das zu parsende PROP Objekt.
     * @param parentNode    Älternknoten als Ziel Konoten. 
     */
    
    public void parseToParent( UM model, SNode parentNode ) {
        this.parse( model, parentNode :: addChild );
    }

    
    
    
}
