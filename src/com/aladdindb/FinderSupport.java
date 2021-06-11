package com.aladdindb;

import java.util.function.Consumer;
import java.util.function.Function;

import com.aladdindb.finder.Finder;
import com.aladdindb.finder.logical.LogicalAndFinders;
import com.aladdindb.finder.logical.LogicalOrFinders;
import com.aladdindb.finder.types.DateFinder;
import com.aladdindb.finder.types.StringFinder;
import com.aladdindb.store.models.Unit;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.util.Var;

public class FinderSupport < UDM extends DataModel< UDM > > {
	
	
	public final Class < UDM > 	udmClass;
	
	public final DataObjectAnalyzer< UDM > analyzer;;

	
	public FinderSupport( Class < UDM > udmClass ) {
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
    //						      
	//****************************************************************
	
}
