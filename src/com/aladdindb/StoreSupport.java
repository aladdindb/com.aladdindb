package com.aladdindb;

import java.nio.file.Path;
import java.util.function.Consumer;

import com.aladdindb.finder.Finder;
import com.aladdindb.finder.logical.LogicalAndFinders;
import com.aladdindb.finder.logical.LogicalOrFinders;
import com.aladdindb.sorter.Sorter;
import com.aladdindb.sorter.SorterList;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.sn.SnPoint;

public interface StoreSupport < UDM extends DataModel< UDM > > {
	
    //****************************************************************
    //						 StoreId 
    //****************************************************************
	
	public String getStoreId();

	default void getStoreId( Consumer < String > storeIdConsumer ) {
		var rv = getStoreId();
		if( rv != null )storeIdConsumer.accept( rv );
	}

	//****************************************************************
    //						 Origin 
    //****************************************************************
	
	public Path getStoreOrigin();

	default void getStoreOrigin( Consumer< Path > originConsumer ) {
		var rv = getStoreOrigin();
		if( rv != null )originConsumer.accept( rv );
	}

	//****************************************************************
    //						 Transformer 
    //****************************************************************
	
	public Class< UDM >	udmClass( );
	
	default void udmClass( Consumer< Class < UDM > > udmClassConsumer ) {
		var rv = udmClass(); 
		if( rv != null )udmClassConsumer.accept( rv );
	}

	//****************************************************************
    //						 MagicLamp 
    //****************************************************************
	
	default void newMagicLamp( GenieConnection genieConnection, Consumer< MagicLamp < UDM > > magicLampConsumer ) {
		var rv = newMagicLamp( genieConnection );
		if( rv != null )magicLampConsumer.accept( rv );
	}
	
	default MagicLamp<UDM> newMagicLamp( GenieConnection genieConnection ) {
		return new MagicLamp <> ( this, genieConnection );
	}
	
	//****************************************************************
    //						 Genie 
    //****************************************************************

	default void newGenie( Consumer< Genie < UDM > > genieConsumer ) {
		var rv = newGenie();
		if( rv != null )genieConsumer.accept( rv );
	}
	
	default Genie < UDM > newGenie() {
		return new Genie <> ( this.getStoreOrigin().resolve( getStoreId() ), this );
	}
	
    //****************************************************************
    //						 Finder 
    //****************************************************************
	
	default void newFinder( SnPoint finderNode, Consumer < Finder < UDM, ? extends DataModel<?> > > finderConsumer ) {
		var rv = newFinder( finderNode );
		if( rv != null ) finderConsumer.accept( rv );
	}
	
	default Finder< UDM, ? extends DataModel < ? > > newFinder( SnPoint finderNode ) {
		var finder = this.newFinder( finderNode.key.get() );
		return finder != null ? ( Finder< UDM, ? extends DataModel < ? > >) finder.newTransformer().toModel( finderNode ) : null;
	}
	
	default Finder< UDM, ? > newFinder( String finderType ) {
		return 	finderType.equals( MethodField.LOGICAL_AND	.asFinderList() ) ? new LogicalAndFinders	< UDM >( this ) : 
				finderType.equals( MethodField.LOGICAL_OR	.asFinderList() ) ? new LogicalOrFinders	< UDM >( this ) : null; 
	}
	
    //****************************************************************
    //						 Or Finders 
    //****************************************************************
	
	default void newOrFinders( Consumer < LogicalOrFinders< UDM > > orFindersConsumer ) {
		var rv = newOrFinders();
		if( rv != null )orFindersConsumer.accept( rv );
	}

	default LogicalOrFinders < UDM > newOrFinders() {
		return new LogicalOrFinders	< UDM >( this );
	}

	default LogicalOrFinders< UDM > newOrFinders( Finder... finders ) {
		var rv = new LogicalOrFinders< UDM >( this );
		rv.addFinder( finders );
		return rv;
	}
	
    //****************************************************************
    //						 And Finders 
    //****************************************************************

	default void newAndFinders( Consumer < LogicalAndFinders< UDM > > andFindersConsumer ) {
		var rv = newAndFinders();
		if( rv != null )andFindersConsumer.accept( rv );
	}

	default LogicalAndFinders< UDM > newAndFinders() {
		return new LogicalAndFinders < UDM >( this );
	}

	default LogicalAndFinders< UDM > newAndFinders( Finder... finders ) {
		var rv = new LogicalAndFinders	< UDM >( this );
		rv.addFinder( finders );
		return rv;
	}
	
    //****************************************************************
    //						  Sorter
    //****************************************************************
	
	default void newSorter( SnPoint sorterNode, Consumer < Sorter < UDM, ? extends DataModel<?> > > sorterConsumer ) {
		var rv = newSorter( sorterNode );
		if( rv != null ) sorterConsumer.accept( rv );
	}
	
	default Sorter< UDM, ? extends DataModel < ? > > newSorter( SnPoint sorterNode ) {
		var sorter = this.newSorter( sorterNode.key.get() );
		return sorter != null ? ( Sorter< UDM, ? extends DataModel < ? > >) sorter.newTransformer().toModel( sorterNode ) : null;
	}
	
	default Sorter< UDM, ? > newSorter( String sorterType ) { 
		return 	sorterType.equals( MethodField.LIST.asSorterList() ) ? new SorterList< UDM >( this ) : null; 
	}
	
	//**********************************************************
	//					Sorters
	//**********************************************************
	
	default void newSorters( Consumer < SorterList< UDM > > sortersConsumer ) {
		var rv = newSorters();
		if( rv != null )sortersConsumer.accept( rv );
	}

	default SorterList< UDM > newSorters() {
		return new SorterList	< UDM >( this );
	}

	default SorterList< UDM > newSorters( Sorter...sorters ) {
		var rv = new SorterList	< UDM >( this );
		rv.addSorter(sorters);
		return rv;
	}


	
}
