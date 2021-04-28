package com.aladdindb.structure.xml;


import static com.aladdindb.structure.xml.XmlLineProp.BLK_CLOSE;
import static com.aladdindb.structure.xml.XmlLineProp.BLK_DL;
import static com.aladdindb.structure.xml.XmlLineProp.BLK_OPEN;
import static com.aladdindb.structure.xml.XmlLineProp.BLK_SL_CLOSE;
import static com.aladdindb.structure.xml.XmlLineProp.BLK_SL_VOID;
import static com.aladdindb.structure.xml.XmlLineProp.BLK_SL_VOID_CLOSE;
import static com.aladdindb.structure.xml.XmlLineProp.CDATA_LINE;
import static com.aladdindb.structure.xml.XmlLineProp.CDATA_OPEN;
import static com.aladdindb.structure.xml.XmlLineProp.CDATA_OPEN_CLOSE;

import com.aladdindb.structure.KeyValue;

/**
 * Überprüft und bewertet eine übergebenen XML Zeile
 * 
 * @version 3.0, 2019/06/11
 * @author Macit Kandemir
 */
public class XmlLineParser {

    
//    private String line;

    private final XmlLineStart        lineStart       = new XmlLineStart();
    private final XmlLineStop         lineStop        = new XmlLineStop();
    
    
    public XmlLineProp parse( StringBuilder line ) {
        
        final XmlLineProp prop = new XmlLineProp();
        
        this.parse( line, prop );
        
        return prop;
    }
    
    
    private void parse( StringBuilder sb, XmlLineProp prop ) {
        
//        var startType   = lineStart .getType( sb );
//        var stopType    = lineStop  .getType( sb );
        
        var openTag     = "";
        var tagName     = "";
        var tagValue    = "";
        
        byte lineType = getLineType( sb );
        
        prop.lineType.set( lineType );
        
        switch( lineType ) {
            
            case BLK_OPEN           : 
            case BLK_SL_VOID_CLOSE  : 
            case BLK_SL_CLOSE       :
                openTag = sb.substring( 0 ,sb.indexOf( ">"  ) + 1);
                tagName = parseAttributes( openTag, prop ) ? 
                    openTag.substring( openTag.indexOf('<')+1, openTag.indexOf(' ')).trim() :
                    openTag.substring( openTag.indexOf('<')+1, openTag.indexOf('>')).trim();
                if( lineType == BLK_SL_CLOSE ) {                
                    tagValue = sb.substring( sb.indexOf( ">" ) +1 ,sb.indexOf( "</" ) );
                }
                break;

            case BLK_SL_VOID        :
                openTag = sb.substring( 0 ,sb.indexOf( "/>"  ) + 2);
                tagName = parseAttributes( openTag, prop ) ? 
                    openTag.substring( openTag.indexOf('<')+1, openTag.indexOf(' ')).trim() :
                    openTag.substring( openTag.indexOf('<')+1, openTag.indexOf("/>")).trim();
                break;
                
            case CDATA_LINE :
                tagValue = sb.substring( 4 ,sb.lastIndexOf("|->]" ) ) ;
                break;
                
            case BLK_DL :
                tagValue = sb.toString();
                break;
        }
        
        prop.tagName    .set( tagName  );
        prop.value      .set( tagValue );
        
    }
    
    private byte getLineType( StringBuilder sb ) {

        var startType   = lineStart .getType( sb );
        var stopType    = lineStop  .getType( sb );
        
        switch( startType ) {
            
            //------------------------------------------------------
            case XmlLineStart.TAG_START:
                
                switch( stopType ) {
                    
                    case XmlLineStop.TAG_STOP:
                        if( sb.lastIndexOf("</") > 2 ) {
                            var tagValue = sb.substring( sb.indexOf( ">" ) +1 ,sb.indexOf( "</" ) );
                            return tagValue.isEmpty() ? BLK_SL_VOID_CLOSE : BLK_SL_CLOSE;
                        } else return BLK_OPEN;

                    case XmlLineStop.TAG_CLOSE: return BLK_SL_VOID;
                }
            //------------------------------------------------------
            case XmlLineStart.TAG_CLOSE_START:
                switch( stopType ) {
                    case XmlLineStop.TAG_STOP: return BLK_CLOSE;
                }
            //------------------------------------------------------
            case XmlLineStart.CDATA_OPEN:
                switch( stopType ) {
                    case XmlLineStop.CDATA_CLOSE: return CDATA_OPEN_CLOSE;
                }
                return CDATA_OPEN;
            //------------------------------------------------------
            case XmlLineStart.CDATA_LINE_OPEN:
                switch( stopType ) {
                    case XmlLineStop.CDATA_LINE_CLOSE: return CDATA_LINE;
                }
            default : return BLK_DL;
                
        }

    }
    
    
    private boolean parseAttributes( String openTag, XmlLineProp prop ) {

        if ( openTag.indexOf(' ') > 0 ) {
            
            prop.params.get( params -> {

                var attributes  = openTag.substring( openTag.indexOf(' ')+1, openTag.indexOf('>'));
                
                try {
                    while (!attributes.isEmpty() ) {

                        String key;
                        String value;

                        int i = attributes.indexOf('=');

                        key = attributes.substring( 0, i).trim();

                        attributes = attributes.substring( i + 1).trim();

                        i = attributes.indexOf('"', 1);
                        value = attributes.substring(1, i).trim();

                        params.add( new KeyValue( key, value ) );

                        if (i < 0) {
                            attributes = "";
                        } else {
                            attributes = attributes.substring( i + 1 );
                        }

                        attributes = attributes.trim();
                    }
                } catch( IndexOutOfBoundsException ex ) {}
            });
            return true;
        }
        return false;
    }

    
}
