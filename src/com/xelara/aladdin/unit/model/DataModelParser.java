package com.xelara.aladdin.unit.model;

import java.util.function.Consumer;

import com.xelara.core.Var;
import com.xelara.structure.snode.SNode;

/**
 *
 * @author Macit Kandemir
 * @param <DATA_MODEL>
 */
public abstract class DataModelParser < DATA_MODEL extends DataModel < DATA_MODEL > > {

    private final String key;

    public DataModelParser( Enum<?> key ) {
        this( key.name() );
    }
    
    public DataModelParser( String key ) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    //****************************************************************
    //
    //****************************************************************

    public final DATA_MODEL parse( SNode node ) {
        return DataModelParser.this.parse( node, newModel() );
    }

    public final void parse( SNode node, Consumer < DATA_MODEL > consumer ) {
        DATA_MODEL model = DataModelParser.this.parse( node );
        if( model != null ) consumer.accept( model );
    }

    public final void parse( SNode node, Var < DATA_MODEL > target ) {
        DataModelParser.this.parse( node, target :: setValue );
    }
    
    //****************************************************************
    //
    //****************************************************************

    
    public final void parse( Var < DATA_MODEL > modelVar, Consumer < SNode > consumer  ) {
        modelVar.getValue( model -> DataModelParser.this.parse( model, consumer ) );
    }
    
    public final void parse( DATA_MODEL model, Consumer < SNode > consumer ) {
        var node = DataModelParser.this.parse( model );
        if( node != null ) consumer.accept( node );
    }

    public final SNode parse( DATA_MODEL model ) {
        return DataModelParser.this.parse( model, new SNode( getKey()) );
    }
    
    public final SNode parse( Var < DATA_MODEL > modelVar, SNode target ) {
        modelVar.getValue( model -> {
            DataModelParser.this.parse( model, target);
        });
        return target;
    }

    //****************************************************************
    //
    //****************************************************************
    
    public abstract DATA_MODEL newModel();
    
    
    public abstract DATA_MODEL  parse ( SNode   	src ,DATA_MODEL   	target );
    public abstract SNode   	parse ( DATA_MODEL 	src ,SNode  		target );
    
    
    //****************************************************************
    //
    //****************************************************************

    public final DATA_MODEL parseFromParent( SNode parentNode ) {
        Var < DATA_MODEL > target = new Var<>();
        parentNode.getChild( getKey(), node -> {
            DataModelParser.this.parse( node, target :: setValue );
        });
        return target.getValue();
    }
    
    public final void parseFromParent( SNode parentNode,  DATA_MODEL  model ) {
    	parseFromParent( parentNode, model :: fill );
    }
    
    public final void parseFromParent( SNode parentNode, Consumer < DATA_MODEL > consumer ) {
        DATA_MODEL target = DataModelParser.this.parseFromParent( parentNode );
        if( target != null ) consumer.accept( target );
    }

    public final void parseFromParent( SNode parentNode, Var < DATA_MODEL > target ) {
        DataModelParser.this.parseFromParent( parentNode, target :: setValue );
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
    
    public final void parseToParent( Var < DATA_MODEL > modelVar, SNode parentNode ) {
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
    
    public final void parseToParent( DATA_MODEL model, SNode parentNode ) {
        this.parse( model, parentNode :: addChild );
    }

    
    
    
}
