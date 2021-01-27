package com.xelara.aladdin.unit.model;

import java.util.function.Consumer;

import com.xelara.core.Var;
import com.xelara.structure.node.Snode;

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
    
    public abstract DATA_MODEL newModel();
    
    //****************************************************************
    //
    //****************************************************************

    public final DATA_MODEL fromNode( Snode node ) {
        return DataModelParser.this.fromNode( node , newModel() );
    }

    public final void fromNode( Snode node , Consumer < DATA_MODEL > consumer ) {
        DATA_MODEL model = DataModelParser.this.fromNode( node );
        if( model != null ) consumer.accept( model );
    }

    public final void fromNode( Snode node , Var < DATA_MODEL > modelVar ) {
        DataModelParser.this.fromNode( node, modelVar :: setValue );
    }
    
    public abstract DATA_MODEL  fromNode( Snode node , DATA_MODEL model );
    
    //****************************************************************
    //
    //****************************************************************

    public final void toNode( Var < DATA_MODEL > modelVar, Consumer < Snode > consumer  ) {
        modelVar.getValue( model -> DataModelParser.this.toNode( model, consumer ) );
    }
    
    public final void toNode( DATA_MODEL model, Consumer < Snode > consumer ) {
        var node = DataModelParser.this.toNode( model );
        if( node != null ) consumer.accept( node );
    }

    public final Snode toNode( DATA_MODEL model ) {
        return DataModelParser.this.toNode( model, new Snode( getKey()) );
    }
    
    public final Snode toNode( Var < DATA_MODEL > modelVar, Snode node ) {
        modelVar.getValue( model -> {
            DataModelParser.this.toNode( model, node);
        });
        return node;
    }

    public abstract Snode toNode( DATA_MODEL model , Snode node );
    
    //****************************************************************
    //
    //****************************************************************

    public final DATA_MODEL fromParentNode( Snode parentNode ) {
        Var < DATA_MODEL > target = new Var<>();
        parentNode.childs.get( getKey(), node -> {
            DataModelParser.this.fromNode( node, target :: setValue );
        });
        return target.getValue();
    }
    
    public final void fromParentNode( Snode parentNode,  DATA_MODEL  model ) {
    	fromParentNode( parentNode, model :: fill );
    }
    
    public final void fromParentNode( Snode parentNode, Consumer < DATA_MODEL > consumer ) {
        DATA_MODEL target = DataModelParser.this.fromParentNode( parentNode );
        if( target != null ) consumer.accept( target );
    }

    public final void fromParentNode( Snode parentNode, Var < DATA_MODEL > modelVar ) {
        DataModelParser.this.fromParentNode( parentNode, modelVar :: setValue );
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
    
    public final void toParentNode( Var < DATA_MODEL > modelVar, Snode parentNode ) {
        modelVar.getValue( model -> {
            toParentNode( model, parentNode );
        });
    }
    
    /**
     * Der aus dem generischem PROP Objekt erzeugter Knoten wird nach dem
     * Parsen, dem Ältern Konoten hinzugefügt.
     * 
     * @param model          Das zu parsende PROP Objekt.
     * @param parentNode    Älternknoten als Ziel Konoten. 
     */
    
    public final void toParentNode( DATA_MODEL model, Snode parentNode ) {
        this.toNode( model, parentNode.childs :: add );
    }

    
    
    
}
