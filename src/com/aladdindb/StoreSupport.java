package com.aladdindb;

import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.function.Function;

import com.aladdindb.finder.Finder;
import com.aladdindb.finder.logical.LogicalAndFinders;
import com.aladdindb.finder.logical.LogicalOrFinders;
import com.aladdindb.finder.types.DateFinder;
import com.aladdindb.finder.types.StringFinder;
import com.aladdindb.sorter.Sorter;
import com.aladdindb.sorter.SorterList;
import com.aladdindb.store.models.Unit;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.util.Var;

public class StoreSupport < UDM extends DataModel< UDM > > {
	
	
	public final String 		storeId;
	public final Path 			storeOrigin;
	public final Class < UDM > 	udmClass;
	
	public final DataObjectAnalyzer< UDM > analyzer;;

	
	public StoreSupport( String storeId, Path storeOrigin, Class < UDM > udmClass ) {
		this.storeId 		= storeId;
		this.storeOrigin 	= storeOrigin;
		this.udmClass 		= udmClass;
		this.analyzer 		= new DataObjectAnalyzer<>(udmClass);
	}

	public Finder<UDM, ?> newFinder(  Function< Unit< UDM >, Var<?> > function ) {
		var varType = this.analyzer.getVarType( function);
		
		if( varType != null ) {
			return switch( varType ) {
				
				case STRING 		->  new StringFinder 	< UDM > ( this.udmClass ,  function );
				case LOCAL_DATE 	->  new DateFinder 		< UDM > ( this.udmClass ,  function );
				
				default -> null;
				
			};
		}
		return null;
	}
	
	//****************************************************************
    //						 MagicLamp 
    //****************************************************************
	
	public void newMagicLamp( GenieConnection genieConnection, Consumer< MagicLamp < UDM > > magicLampConsumer ) {
		var rv = newMagicLamp( genieConnection );
		if( rv != null )magicLampConsumer.accept( rv );
	}
	
	public MagicLamp<UDM> newMagicLamp( GenieConnection genieConnection ) {
		return new MagicLamp <> ( this, genieConnection );
	}
	
	//****************************************************************
    //						 Genie 
    //****************************************************************

	public void newGenie( Consumer< Genie < UDM > > genieConsumer ) {
		var rv = newGenie();
		if( rv != null )genieConsumer.accept( rv );
	}
	
	public Genie < UDM > newGenie() {
		return new Genie <> ( this.storeOrigin.resolve( this.storeId ), this );
	}
	
    //****************************************************************
    //						 Finder 
    //****************************************************************
	
	public void newFinder( SnPoint finderNode, Consumer < Finder < UDM, ? extends DataModel<?> > > finderConsumer ) {
		var rv = newFinder( finderNode );
		if( rv != null ) finderConsumer.accept( rv );
	}
	
	public Finder< UDM, ? extends DataModel < ? > > newFinder( SnPoint finderNode ) {
		var finder = this.newFinder( finderNode.key.get() );
		return finder != null ? ( Finder< UDM, ? extends DataModel < ? > >) finder.newTransformer().toModel( finderNode ) : null;
	}
	
	public Finder< UDM, ? > newFinder( String finderType ) {
		return 	finderType.equals( MethodField.LOGICAL_AND	.asFinderList() ) ? new LogicalAndFinders	< UDM >( this ) : 
				finderType.equals( MethodField.LOGICAL_OR	.asFinderList() ) ? new LogicalOrFinders	< UDM >( this ) : null; 
	}
	
    //****************************************************************
    //						 Or Finders 
    //****************************************************************
	
	public void newOrFinders( Consumer < LogicalOrFinders< UDM > > orFindersConsumer ) {
		var rv = newOrFinders();
		if( rv != null )orFindersConsumer.accept( rv );
	}

	public LogicalOrFinders < UDM > newOrFinders() {
		return new LogicalOrFinders	< UDM >( this );
	}

	public LogicalOrFinders< UDM > newOrFinders( Finder... finders ) {
		var rv = new LogicalOrFinders< UDM >( this );
		rv.addFinder( finders );
		return rv;
	}
	
    //****************************************************************
    //						 And Finders 
    //****************************************************************

	public void newAndFinders( Consumer < LogicalAndFinders< UDM > > andFindersConsumer ) {
		var rv = newAndFinders();
		if( rv != null )andFindersConsumer.accept( rv );
	}

	public LogicalAndFinders< UDM > newAndFinders() {
		return new LogicalAndFinders < UDM >( this );
	}

	public LogicalAndFinders< UDM > newAndFinders( Finder... finders ) {
		var rv = new LogicalAndFinders	< UDM >( this );
		rv.addFinder( finders );
		return rv;
	}
	
    //****************************************************************
    //						  Sorter
    //****************************************************************
	
	public void newSorter( SnPoint sorterNode, Consumer < Sorter < UDM, ? extends DataModel<?> > > sorterConsumer ) {
		var rv = newSorter( sorterNode );
		if( rv != null ) sorterConsumer.accept( rv );
	}
	
	public Sorter< UDM, ? extends DataModel < ? > > newSorter( SnPoint sorterNode ) {
		var sorter = this.newSorter( sorterNode.key.get() );
		return sorter != null ? ( Sorter< UDM, ? extends DataModel < ? > >) sorter.newTransformer().toModel( sorterNode ) : null;
	}
	
	public Sorter< UDM, ? > newSorter( String sorterType ) { 
		return 	sorterType.equals( MethodField.LIST.asSorterList() ) ? new SorterList< UDM >( this ) : null; 
	}
	
	//**********************************************************
	//					Sorters
	//**********************************************************
	
	public void newSorters( Consumer < SorterList< UDM > > sortersConsumer ) {
		var rv = newSorters();
		if( rv != null )sortersConsumer.accept( rv );
	}

	public SorterList< UDM > newSorters() {
		return new SorterList	< UDM >( this );
	}

	public SorterList< UDM > newSorters( Sorter...sorters ) {
		var rv = new SorterList	< UDM >( this );
		rv.addSorter(sorters);
		return rv;
	}


	
}
