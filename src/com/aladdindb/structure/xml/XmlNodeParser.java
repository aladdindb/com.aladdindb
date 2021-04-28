package com.aladdindb.structure.xml;

import java.util.Arrays;
import java.util.stream.Stream;

import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.structure.sn.props.SnValueType;

/**
 * Kapselt eine XML Zeile aus einer XML Datei für den Konfortablen zugriff auf
 * die XML Elemente.
 *
 * @version 1.0, 04/08/23
 * @author Macit Kandemir
 */
public class XmlNodeParser {

    private final int       tabStep         = 4;
    private int             tab             = 0;

    private final String lineSep = "\n";

    
    public String parse( SnPoint node ) {
        tab = -tabStep;
        var rv = new StringBuilder();
        parseRekursive( node, rv );
        return rv.toString();
    }

    private void parseRekursive( SnPoint node, StringBuilder sb ) {
    	
    	node.valueType.set( this.getAutoValueType(node) );
    	
        tab += tabStep;
                        createLineStart  ( node, tab, sb );
        var tagClosed = createLineStop   ( node, sb );
        
        newLine( sb );

        if( !tagClosed ) {
            
            switch( node.valueType .get() ) {
                
                case SnValueType.CDATA:
                    tab += tabStep;
                    createCDATA( node, sb );
                    tab -= tabStep;
                    newLine( sb );
                break;
                
                case SnValueType.MULTI_LINE:
                	if( node.children.exist() ) {
                        node.children.forEach( childNode -> {
                            parseRekursive( childNode, sb  );
                            tab -= tabStep;
                        });
                	} else {
                        tab += tabStep;
                        createValue( node, sb );
                        tab -= tabStep;
                	}
                break;
                
                default:
                    tab += tabStep;
                    createValue( node, sb );
                    tab -= tabStep;
                break;
                
            }

            closeTag( node, sb );
            newLine( sb );
        }
    }
    
    private void createLineStart( SnPoint node, int tabs, StringBuilder rv ) {
        rv.append( createString( tabs, ' ' ) ).append( '<' ).append( node.key.get() ).append( createAttributes(node) );
    }
    
    private boolean createLineStop( SnPoint node, StringBuilder rv ) {

        boolean tagClosed = false;
        
    	if( !this.isMultiLineSnPoint( node ) ) {
            switch( node.valueType.get() ) {
                case SnValueType.SINGLE_LINE:
                	if( node.value.exist() ) { 
                		this.singleLineStopWidthValue( node, rv ); 
                	} else rv.append( "/>" );
                    tagClosed = true;
                break;

                case SnValueType.SINGLE_LINE_CLOSE:
                	this.singleLineStopWidthValue( node, rv );
                    tagClosed = true;
                break;
                
                case SnValueType.STAND_ALONE_LINE:
                    rv.append( ">" );
                    tagClosed = true;
                break;
                
                case SnValueType.CDATA: rv.append( ">" ); break;
            }
    	} else rv.append(">");
    	
        return tagClosed;
    }
    
    private void singleLineStopWidthValue( SnPoint node, StringBuilder rv ) {
		var value 	= node.value.exist() ? node.value.get() : "";
        rv.append(">").append( value ); 
        this.cleanCloseTag( node, rv );
    }
    
    private void createCDATA( SnPoint node, StringBuilder rv ) {
        
        var space = createString( tab, ' ' );
        
        rv.append( space ).append( "<![CDATA[" );

        newLine( rv );
        
        tab += tabStep;
        var space2 = createString( tab, ' ' );
        Stream.of( node.value.get().split( lineSep ) ).forEach( line -> {
            rv.append( space2 ).append( "[<-|" ).append(line).append( "|->]" );
            newLine( rv );
        });
        tab -= tabStep;
        
        rv.append( space ).append( "]]>" );
    }


    private void createValue( SnPoint node, StringBuilder rv ) {
        
        var space = createString( tab, ' ' );
        
        Stream.of( node.value.get().split( lineSep ) ).forEach( line -> {
            rv.append( space ).append( line );
            newLine( rv );
        });
    }

    private void closeTag( SnPoint node, StringBuilder rv ) {
        rv.append( createString(tab, ' ') );
        this.cleanCloseTag( node, rv );
    }

    private void cleanCloseTag( SnPoint node, StringBuilder rv ) {
        rv.append( "</" ).append( node.key.get() ).append( ">");
    }

    private String createAttributes( SnPoint node ) {
    	StringBuilder sb = new StringBuilder();
//        String rv = "";
        node.attributes.forEach( snAttribute -> {
            var key     = snAttribute.key.get();
            var value   = snAttribute.value.get();
            sb.append(" " + key + "=" + "\"" + value + "\"");
//            rv += " " + key + "=" + "\"" + value + "\"";
        });
        return sb.toString();
    }

    public String createString(int len, char fill) {
        if (len < 0) {
            return null;
        }
        char[] cs = new char[len];
        Arrays.fill(cs, fill);
        return new String(cs);
    }

    private void newLine( StringBuilder sb ) {
        sb.append( lineSep );
    }
    
    private byte getAutoValueType( SnPoint node ) {
    	var rv = node.valueType.get();
    	if( rv == SnValueType.AUTO_INIT ) {
    		if( this.isMultiLineSnPoint(node) ) return SnValueType.MULTI_LINE;
			return SnValueType.SINGLE_LINE;
    	} 
    	return rv;
    }

    private boolean isMultiLineSnPoint( SnPoint node ) {
    	if( node.children.exist() ) return true;
    	String value = node.value.get();
    	if( node.value.exist() ) {
        	if( node.attributes.exist() ) return true; 
    		if(	value.length() > 60 || value.split("\n").length > 1 ) return true;
    	}
    	return false;
    }
}
