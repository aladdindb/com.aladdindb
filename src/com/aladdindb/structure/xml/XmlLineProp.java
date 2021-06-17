package com.aladdindb.structure.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.aladdindb.structure.KeyValue;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.sn.props.SnValueType;
import com.aladdindb.util.Var;


/**
 * 
 * DL = DATA_LINE
 * SL = SINGLE_LINE
 * 
 * @version 2.0, 2019/05/23
 * @author Macit Kandemir
 */
public class XmlLineProp {

    public static final byte NONE = 0;

    public static final byte BLK_OPEN                    = 10;       //  <blk>
    public static final byte BLK_CLOSE                   = 11;       //  </blk>
    public static final byte BLK_SL_VOID                 = 12;       //  <blk atr=".."/>
    public static final byte BLK_SL_VOID_CLOSE           = 13;       //  <blk atr=".."></blk>
    public static final byte BLK_SL_CLOSE                = 14;       //  <blk>label</blk>
    public static final byte BLK_DL                      = 15;      

    public static final byte CDATA_OPEN                  = 50;
    public static final byte CDATA_CLOSE                 = 51;
    public static final byte CDATA_OPEN_CLOSE            = 52;
    public static final byte CDATA_LINE                  = 53;


    public final Var < Byte     > lineType      = new Var<>();
    public final Var < String   > tagName       = new Var<>();
    public final Var < String   > value         = new Var<>();

    public final Var< List < KeyValue > > params = new Var<>( null, new ArrayList<>() );

    
    
    public SnPoint createNode() {
        
        Var < SnPoint > rv = new Var<>( null, new SnPoint( tagName.get() ));
        
        rv.get( newNode -> {

            params.get( attributes -> {
                attributes.forEach( atr -> {
                    newNode.attributes.set( atr.key, atr.value );
                });
            });
            
            lineType.get( type -> {
                getSnValueType( type, newNode.valueType :: set ); 
                switch( type ) {
                    case BLK_SL_CLOSE: 
                        this.value.get( newNode.value :: set );
                    break;
                }
            });
            
        });
        
        return rv.get();
    }


    public void getSnValueType( int lineType, Consumer < Byte > consumer ) {
        var rv = getSnValueType( lineType );
        if( rv != null ) consumer.accept( rv );
    }
    
    public Byte getSnValueType( int lineType ) {
            
        switch( lineType ) {
        	case BLK_OPEN          	:return SnValueType.MULTI_LINE      	;
        	case BLK_DL          	:return SnValueType.MULTI_LINE      	;
            case BLK_SL_VOID       	:return SnValueType.SINGLE_LINE      	;
            case BLK_SL_CLOSE      	:return SnValueType.SINGLE_LINE_CLOSE	;
            case BLK_SL_VOID_CLOSE 	:return SnValueType.SINGLE_LINE_CLOSE	;
            case CDATA_OPEN        	:return SnValueType.CDATA            	;
        }
        
        return null;
    }
    
    
}
