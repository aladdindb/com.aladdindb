package com.aladdindb.structure.xml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.util.Var;

/**
 *
 * @author Macit Kandemir
 */
public class XML {

	
	/**
	 * if( OK ) return { true } else { false }
	 * 
	 * @param path
	 * @param node
	 * @return
	 */
    public static boolean save( Path path, SnPoint node ) {
        Var < Boolean > rv = new Var<>( false );
        parse( node, str -> {
            try {
                Files.write( path, str.getBytes() );
                rv.set( true );
            } catch (IOException ex) {
//                Logger.getLogger( XML.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return rv.get();
    }
    
    public static void load( Path path, Consumer < SnPoint > consumer ) {
        var rv = XML.load(path);
        if( rv != null)consumer.accept(rv);
    }

    public static SnPoint load( Path path ) {
        try ( var lines = Files.lines( path ) ) {
            return new XmlParser().parse( lines.iterator() );
        } catch( IOException e1) {}
        
        return null;
    }

    public static void loadNotTheFirstLine( Path path, Consumer < SnPoint > consumer ) {
        var rv = XML.loadNotTheFirstLine( path );
        if( rv != null )consumer.accept( rv );
    }

    public static SnPoint loadNotTheFirstLine( Path path ) {
        try ( var lines = Files.lines( path ) ) {
            Iterator < String > iter = lines.iterator();
            if( iter.hasNext() ) iter.next();
            return new XmlParser().parse( iter );
        } catch( IOException e1) {}
        
        return null;
    }
    
    public static final void parse ( String xmlString, Consumer < SnPoint > consumer ) {
    	var sn =  parse( xmlString );
    	if( sn != null ) consumer.accept( sn );
    }
    
    public static final SnPoint parse ( String xmlString ) {
        return new XmlParser().parse( Arrays.asList( xmlString.split("\n")).iterator() );
    }
    
    public static final void parse( SnPoint node, Var < Consumer < String > > consumerVar ) {
    	consumerVar.get( consumer -> parse( node, consumer ) );
    }
    
    public static final void parse( SnPoint node, Consumer < String > consumer ) {
        var rv = parse(node);
        if(rv != null && !rv.trim().isEmpty())consumer.accept(rv);
    }
    
    public static final String parse( SnPoint node ) {
        return new XmlNodeParser().parse(node);
    }


}
