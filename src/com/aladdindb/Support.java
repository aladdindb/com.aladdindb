package com.aladdindb;

import java.nio.file.Path;
import java.util.function.Consumer;

import com.aladdindb.finder.Finder;
import com.aladdindb.sorter.Sorter;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.Transformer;
import com.aladdindb.structure.sn.SnPoint;

public interface Support < UDM extends DataModel< UDM > > {
	
	public MagicLamp < UDM > 	newMagicLamp 	( GenieConnection genieConnection );
	public Path 				getOrigin		( );
	public String 				getStoreId		( );
	public Transformer < UDM > 	newTransformer	( );

	
    //****************************************************************
    //						 Finder 
    //****************************************************************
	
	public Finder< UDM, ? > newFinder( String finderType );
	

	default Finder< UDM, ? extends DataModel < ? > > newFinder( SnPoint finderNode ) {
		var finder = this.newFinder( finderNode.key.get() );
		return finder != null ? ( Finder< UDM, ? extends DataModel < ? > >) finder.newTransformer().toModel( finderNode ) : null;
	}
	
	default void newFinder( SnPoint node, Consumer < Finder < UDM, ? extends DataModel<?> > > finderConsumer ) {
		var rv = newFinder( node );
		if( rv != null ) finderConsumer.accept( rv );
	}
	
    //****************************************************************
    //						  Sorter
    //****************************************************************
	
	public Sorter< UDM, ? > newSorter( String sorterType );
	

	default Sorter< UDM, ? extends DataModel < ? > > newSorter( SnPoint sorterNode ) {
		var sorter = this.newSorter( sorterNode.key.get() );
		return sorter != null ? ( Sorter< UDM, ? extends DataModel < ? > >) sorter.newTransformer().toModel( sorterNode ) : null;
	}
	
	default void newSorter( SnPoint sorterNode, Consumer < Sorter < UDM, ? extends DataModel<?> > > sorterConsumer ) {
		var rv = newSorter( sorterNode );
		if( rv != null ) sorterConsumer.accept( rv );
	}
	
}
