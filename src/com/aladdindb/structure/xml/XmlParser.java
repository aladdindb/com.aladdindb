package com.aladdindb.structure.xml;

import static com.aladdindb.structure.xml.XmlLineProp.BLK_CLOSE;
import static com.aladdindb.structure.xml.XmlLineProp.BLK_DL;
import static com.aladdindb.structure.xml.XmlLineProp.BLK_OPEN;
import static com.aladdindb.structure.xml.XmlLineProp.BLK_SL_CLOSE;
import static com.aladdindb.structure.xml.XmlLineProp.BLK_SL_VOID;
import static com.aladdindb.structure.xml.XmlLineProp.BLK_SL_VOID_CLOSE;
import static com.aladdindb.structure.xml.XmlLineProp.CDATA_LINE;
import static com.aladdindb.structure.xml.XmlLineProp.CDATA_OPEN;

import java.util.Iterator;

import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.sn.props.SnValueType;
import com.aladdindb.util.Var;

/**
 *
 * @version 2.0, 2019/05/24
 * @author Macit Kandemir
 */
public class XmlParser {
    
    private final XmlLineParser lineParser = new XmlLineParser();

    public final SnPoint parse( Iterator < String > xmlLines ) {
        return parseRekursive( xmlLines, null);
    }
    
    private SnPoint parseRekursive( final Iterator < String > xmlLines, SnPoint parent ) {
        
        Var < SnPoint > rv = new Var<>();
        if( xmlLines.hasNext() ) {
            XmlLineProp lineProp = lineParser.parse( new StringBuilder( xmlLines.next().trim() ) );
            
            lineProp.lineType.get( type -> {
                
                SnPoint  newNode;
        
                switch( type ) {
                    
                    case BLK_OPEN           :
                    case BLK_SL_VOID        :
                    case BLK_SL_VOID_CLOSE  : 
                    case BLK_SL_CLOSE       :
                        newNode = lineProp.createNode();
                        if( parent != null ) parent.children.add( newNode );
                        parseRekursive  ( xmlLines, type == BLK_OPEN ? newNode : parent );
                        rv.set     ( newNode );
                        break;

                    case CDATA_OPEN :
                        parent.valueType.set( SnValueType.CDATA );
                        parseCData      ( xmlLines, parent );
                        parseRekursive  ( xmlLines, parent.parent.get() );
                        break;
                       
                    case BLK_DL:
//                    	if(parent != null ) {
                            parent.valueType.set( SnValueType.MULTI_LINE );
                            lineProp.value.get( parent.value :: set );
                            parseData       ( xmlLines, parent );
                            parseRekursive  ( xmlLines, parent.parent.get() );
//                    	}
                        break;
                        
                    case BLK_CLOSE  :
                        parseRekursive( xmlLines, parent.parent.get() );
                        break;

                }
            });
        }
        
        return rv.get();
    }
    
    private void parseCData(  Iterator < String > xmlLines, SnPoint parent  ) {

        var rv = new StringBuilder();
        
        var firstPass = true;
        var end = false;
        
        while( xmlLines.hasNext() && !end ) { 

            XmlLineProp lineProp = lineParser.parse( new StringBuilder( xmlLines.next().trim() ) );

            switch ( lineProp.lineType.get() ) {
                
                case CDATA_LINE:
                    if(!firstPass)rv.append("\n");
                    lineProp.value.get( rv :: append );
                    break;
                    
                default:
                    parent.value.set( rv.toString() );
                    end = true;
                    
            }
            firstPass = false;
        }
    }
    
    private void parseData(  Iterator < String > xmlLines, SnPoint parent  ) {

        var rv = new StringBuilder( parent.value.get() );
        
        var end = false;
        
        while( xmlLines.hasNext() && !end ) { 

            XmlLineProp lineProp = lineParser.parse( new StringBuilder( xmlLines.next().trim() ) );

            switch ( lineProp.lineType.get() ) {
                
                case BLK_DL:
                    rv.append("\n");
                    lineProp.value.get( rv :: append );
                    break;
                    
                default:
                    parent.value.set( rv.toString() );
                    end = true;
            }
        }
    }

}
