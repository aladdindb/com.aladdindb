package com.aladdindb.structure.xml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.XTransformer;
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
        Var < Boolean > rv = new Var<>( null, false );
        toString( node, str -> {
            try {
                Files.write( path, str.getBytes() );
                rv.set( true );
            } catch (IOException ex) {
//                Logger.getLogger( XML.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return rv.get();
    }
    
    public static void toNode( Path path, Consumer < SnPoint > consumer ) {
        var rv = XML.toNode(path);
        if( rv != null)consumer.accept(rv);
    }

    public static SnPoint toNode( Path path ) {
        try ( var lines = Files.lines( path ) ) {
            return new XmlParser().parse( lines.iterator() );
        } catch( IOException e1) {}
        
        return null;
    }

    public static void toNodeWithoutFirstLine( Path path, Consumer < SnPoint > consumer ) {
        var rv = XML.toNodeWithoutFirstLine( path );
        if( rv != null )consumer.accept( rv );
    }

    public static SnPoint toNodeWithoutFirstLine( Path path ) {
        try ( var lines = Files.lines( path ) ) {
            Iterator < String > iter = lines.iterator();
            if( iter.hasNext() ) iter.next();
            return new XmlParser().parse( iter );
        } catch( IOException e1) {}
        
        return null;
    }
    
    public static final void toNode ( String xmlString, Consumer < SnPoint > consumer ) {
    	var sn =  toNode( xmlString );
    	if( sn != null ) consumer.accept( sn );
    }
    
    public static final SnPoint toNode ( String xmlString ) {
        return new XmlParser().parse( Arrays.asList( xmlString.split("\n")).iterator() );
    }
    
    public static final void toString( SnPoint node, Var < Consumer < String > > consumerVar ) {
    	consumerVar.get( consumer -> toString( node, consumer ) );
    }
    
    public static final void toString( SnPoint node, Consumer < String > consumer ) {
        var rv = toString(node);
        if(rv != null && !rv.trim().isEmpty())consumer.accept(rv);
    }
    
    public static final String toString( SnPoint node ) { 
        return new XmlNodeParser().parse(node);
    }

    public static final < UDM extends DataModel <UDM> > void toString( UDM src, Consumer<String> consumer ) {
    	var rv = XML.<UDM>toString(src);
    	if( rv != null && !rv.trim().isEmpty() )consumer.accept(rv);
    }
    
    public static final < UDM extends DataModel <UDM> > String toString( UDM src ) {
    	var node = XML.<UDM>toNode(src);
    	return XML.toString(node);
    }
    
    public static final < UDM extends DataModel <UDM> > void toNode( UDM src, Consumer<SnPoint> consumer ) {
    	var rv = XML.<UDM>toNode( src );
    	if( rv != null )consumer.accept(rv);
    }
    
    public static final < UDM extends DataModel <UDM> > SnPoint toNode( UDM src ) {
		var t = new XTransformer< UDM >( (Class<UDM>)src.getClass() );
		return t.toNode(src);
    }

    public static final < UDM extends DataModel <UDM> > void toModel( Path path, Class<UDM> clazz, Consumer< UDM > consumer ) {
    	var rv = XML.<UDM>toModel( path, clazz );
    	if( rv != null )consumer.accept(rv);
    }
    
    public static final < UDM extends DataModel <UDM > > UDM toModel( Path path, Class<UDM> clazz ) {
		var t = new XTransformer< UDM >( clazz );
		var node = XML.<UDM>toNode( path);
		return  node != null ? t.toModel(node) : null;
    }

}
