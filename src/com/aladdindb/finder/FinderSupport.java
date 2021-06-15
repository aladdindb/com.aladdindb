package com.aladdindb.finder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;
import java.util.function.Function;

import com.aladdindb.UnitAnalyzer;
import com.aladdindb.finder.logical.LogicalAndFinders;
import com.aladdindb.finder.logical.LogicalOrFinders;
import com.aladdindb.finder.types.BooleanFinder;
import com.aladdindb.finder.types.ByteFinder;
import com.aladdindb.finder.types.DateFinder;
import com.aladdindb.finder.types.DoubleFinder;
import com.aladdindb.finder.types.FloatFinder;
import com.aladdindb.finder.types.IntFinder;
import com.aladdindb.finder.types.StringFinder;
import com.aladdindb.finder.types.ZonedDateTimeFinder;
import com.aladdindb.store.models.Unit;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.util.Var;

public class FinderSupport < UDM extends DataModel< UDM > > {
	
	
	public final Class 			< UDM > udmClass;
	public final UnitAnalyzer	< UDM > analyzer;
	
	private final Function< Unit< UDM >, Var<?> >[] functions;
	
	
    //****************************************************************
    //						  Constructor
    //****************************************************************
	
	public FinderSupport( Class < UDM > udmClass, Function< Unit< UDM >, Var<?> >... functions ) {
		this.udmClass 		= udmClass;
		this.functions 		= functions;
		this.analyzer 		= new UnitAnalyzer<>(udmClass);
	}

    //****************************************************************
    //						  Sorter 
    //****************************************************************
	
	public Finder<UDM, ?> newFinder( String fieldId ) {
		if( this.functions != null ) {
			var function =  this.analyzer.getEquals( fieldId,  this.functions );
			return function != null ? newFinder( function ) : null;
		}
		return null;
	}
	
	public Finder < UDM, ? > newFinder(  Function< Unit< UDM >, Var < ? > > function ) {
		return this.newFinder( null, null, function );
	}

	public Finder < UDM, ? > newDateFinder( String operator, LocalDate date,  Function< Unit< UDM >, Var < ? > > function ) {
		var pattern = date.format( DateTimeFormatter.ISO_LOCAL_DATE );
		return this.newFinder( operator, pattern, function );
	}
	
	public Finder < UDM, ? > newFinder( String operator, String pattern, Function< Unit< UDM >, Var < ? > > function ) {
		var varType = this.analyzer.getVarType( function); 
		
		if( varType != null ) {
			return switch( varType ) {
				
				case STRING 		->  new StringFinder 	< UDM > ( operator, pattern, this.udmClass ,  function );
				case INT 			->  new IntFinder 		< UDM > ( operator, pattern, this.udmClass ,  function );
				case DOUBLE 		->  new DoubleFinder 	< UDM > ( operator, pattern, this.udmClass ,  function );
				case FLOAT 			->  new FloatFinder 	< UDM > ( operator, pattern, this.udmClass ,  function );
				case BYTE 			->  new ByteFinder 		< UDM > ( operator, pattern, this.udmClass ,  function );
				case BOOLEAN 		->  new BooleanFinder 	< UDM > ( operator, pattern, this.udmClass ,  function );
				case LOCAL_DATE 	->  new DateFinder 		< UDM > ( operator, pattern, this.udmClass ,  function );
				
				case ZONED_DATE_TIME  ->  new ZonedDateTimeFinder < UDM > ( operator, pattern, this.udmClass ,  function );
				
				
				default -> null;
				
			};
		}
		return null;
	}
	
    //****************************************************************
    //						 Finder by Node
    //****************************************************************
	
	public void newFinder( SnPoint finderNode, Consumer < Finder < UDM, ? extends DataModel<?> > > finderConsumer ) {
		var rv = newFinder( finderNode );
		if( rv != null ) finderConsumer.accept( rv );
	}
	
	public Finder< UDM, ? extends DataModel < ? > > newFinder( SnPoint finderNode ) {
		var finder = this.newFinderByType( finderNode.key.get() );
		if( finder != null ) {
			return ( Finder< UDM, ? extends DataModel < ? > >) finder.newTransformer().toModel( finderNode ); 
		} else {
			var atr = DefaultFinderTransformer.newFinderAtr(finderNode);
			
			if( atr.fieldId != null && !atr.fieldId.isEmpty() ) {
				var function =  this.analyzer.getEquals( atr.fieldId, this.functions );
				if(function != null) {
					OP.valueOf(atr.operator);
					return newFinder( OP.valueOf(atr.operator).real(), atr.pattern, function );
				}
			}
		}
		return null;
	}
	
    //****************************************************************
    //						 Finder by Type
    //****************************************************************
	
	public Finder< UDM, ? > newFinderByType( String finderType ) {
		return 	finderType.equals( Finder.LOCGICAL_AND 	) ? new LogicalAndFinders	< UDM >( this ) : 
				finderType.equals( Finder.LOCGICAL_OR 	) ? new LogicalOrFinders	< UDM >( this ) : null; 
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
