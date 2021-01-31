package com.xelara.aladdin.unit.model;

import java.util.function.Consumer;

import com.xelara.core.Var;
import com.xelara.structure.sn.SnPoint;

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

    public final DATA_MODEL fromNode( SnPoint node ) {
        return DataModelParser.this.fromNode( node , newModel() );
    }

    public final void fromNode( SnPoint node , Consumer < DATA_MODEL > consumer ) {
        DATA_MODEL model = DataModelParser.this.fromNode( node );
        if( model != null ) consumer.accept( model );
    }

    public final void fromNode( SnPoint node , Var < DATA_MODEL > modelVar ) {
        DataModelParser.this.fromNode( node, modelVar :: setValue );
    }
    
    public abstract DATA_MODEL  fromNode( SnPoint node , DATA_MODEL model );
    
    //****************************************************************
    //
    //****************************************************************

    public final void toNode( Var < DATA_MODEL > modelVar, Consumer < SnPoint > consumer  ) {
        modelVar.getValue( model -> DataModelParser.this.toNode( model, consumer ) );
    }
    
    public final void toNode( DATA_MODEL model, Consumer < SnPoint > consumer ) {
        var node = DataModelParser.this.toNode( model );
        if( node != null ) consumer.accept( node );
    }

    public final SnPoint toNode( DATA_MODEL model ) {
        return DataModelParser.this.toNode( model, new SnPoint( getKey()) );
    }
    
    public final SnPoint toNode( Var < DATA_MODEL > modelVar, SnPoint node ) {
        modelVar.getValue( model -> {
            DataModelParser.this.toNode( model, node);
        });
        return node;
    }

    public abstract SnPoint toNode( DATA_MODEL model , SnPoint node );
    
    //****************************************************************
    //
    //****************************************************************

    public final DATA_MODEL fromParentNode( SnPoint parentNode ) {
        Var < DATA_MODEL > target = new Var<>();
        parentNode.deepLine.get( getKey(), node -> {
            DataModelParser.this.fromNode( node, target :: setValue );
        });
        return target.getValue();
    }
    
    public final void fromParentNode( SnPoint parentNode,  DATA_MODEL  model ) {
    	fromParentNode( parentNode, model :: fill );
    }
    
    public final void fromParentNode( SnPoint parentNode, Consumer < DATA_MODEL > consumer ) {
        DATA_MODEL target = DataModelParser.this.fromParentNode( parentNode );
        if( target != null ) consumer.accept( target );
    }

    public final void fromParentNode( SnPoint parentNode, Var < DATA_MODEL > modelVar ) {
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
    
    public final void toParentNode( Var < DATA_MODEL > modelVar, SnPoint parentNode ) {
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
    
    public final void toParentNode( DATA_MODEL model, SnPoint parentNode ) {
        this.toNode( model, parentNode.deepLine :: add );
    }

    
    
    
}
