package com.aladdindb.finder;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.function.Consumer;
import java.util.function.Function;

import com.aladdindb.UnitAnalyzer;
import com.aladdindb.UnitAnalyzer.VarType;
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
import com.aladdindb.util.time.ALocalDate;
import com.aladdindb.util.time.AZonedDateTime;

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
    //						  Finder 
    //****************************************************************
	
	
	public Finder < UDM, ? > newIntFinder( String operator, Integer value,  Function< Unit< UDM >, Var < ? > > function ) {
		return this.newFinder( operator, value.toString(), function );
	}
	
	public Finder < UDM, ? > newIntFinder( String operator, Integer value, Var < ? >  varObject ) {
		return this.newFinder( operator, value.toString(), varObject );
	}
	
	//---------------------------------------

	public Finder < UDM, ? > newDoubleFinder( String operator, Double value,  Function< Unit< UDM >, Var < ? > > function ) {
		return this.newFinder( operator, value.toString(), function );
	}
	
	public Finder < UDM, ? > newDoubleFinder( String operator, Double value,  Var < ? >  varObject ) {
		return this.newFinder( operator, value.toString(), varObject );
	}

	//---------------------------------------

	public Finder < UDM, ? > newFloatFinder( String operator, Float value,  Function< Unit< UDM >, Var < ? > > function ) {
		return this.newFinder( operator, value.toString(), function );
	}

	public Finder < UDM, ? > newFloatFinder( String operator, Float value,  Var < ? >  varObject ) {
		return this.newFinder( operator, value.toString(), varObject );
	}

	//---------------------------------------

	public Finder < UDM, ? > newByteFinder( String operator, Byte value,  Function< Unit< UDM >, Var < ? > > function ) {
		return this.newFinder( operator, value.toString(), function );
	}

	public Finder < UDM, ? > newByteFinder( String operator, Byte value, Var < ? >  varObject ) {
		return this.newFinder( operator, value.toString(), varObject );
	}
	
	//---------------------------------------

	public Finder < UDM, ? > newBooleanFinder( String operator, Boolean value,  Function< Unit< UDM >, Var < ? > > function ) {
		return this.newFinder( operator, value.toString(), function );
	}

	public Finder < UDM, ? > newBooleanFinder( String operator, Boolean value,  Var < ? >  varObject ) {
		return this.newFinder( operator, value.toString(), varObject );
	}

	//---------------------------------------

	public Finder < UDM, ? > newDateFinder( String operator, LocalDate date,  Function< Unit< UDM >, Var < ? > > function ) {
		return this.newFinder( operator, ALocalDate.toISO( date ), function );
	}
	
	public Finder < UDM, ? > newDateFinder( String operator, LocalDate date,  Var < ? >  varObject ) {
		return this.newFinder( operator, ALocalDate.toISO( date ), varObject );
	}

	//---------------------------------------

	public Finder < UDM, ? > newZondedDateTimeFinder( String operator, ZonedDateTime dateTime,  Function< Unit< UDM >, Var < ? > > function ) {
		return this.newFinder( operator, AZonedDateTime.toISO(dateTime), function );
	}
	
	public Finder < UDM, ? > newZondedDateTimeFinder( String operator, ZonedDateTime dateTime,  Var < ? >  varObject ) {
		return this.newFinder( operator, AZonedDateTime.toISO(dateTime), varObject );
	}

	//****************************************************************
    //				Finder by function  and fieldId
    //****************************************************************
	
	public Finder < UDM, ? > newFinder( String operator, String pattern, Var < ? > varObject ) {
		
		var varType = this.analyzer.getVarsGenericTypeAsVarType( varObject );
		
		return varType != null ? switch( varType ) {
		
			case STRING 		->  new StringFinder 	< UDM > ( this.udmClass, operator, pattern ,  varObject );
			case INT 			->  new IntFinder 		< UDM > ( this.udmClass, operator, pattern ,  varObject );
			case DOUBLE 		->  new DoubleFinder 	< UDM > ( this.udmClass, operator, pattern ,  varObject );
			case FLOAT 			->  new FloatFinder 	< UDM > ( this.udmClass, operator, pattern ,  varObject );
			case BYTE 			->  new ByteFinder 		< UDM > ( this.udmClass, operator, pattern ,  varObject );
			case BOOLEAN 		->  new BooleanFinder 	< UDM > ( this.udmClass, operator, pattern ,  varObject );
			case LOCAL_DATE 	->  new DateFinder 		< UDM > ( this.udmClass, operator, pattern ,  varObject );
			
			case ZONED_DATE_TIME  ->  new ZonedDateTimeFinder < UDM > ( this.udmClass, operator, pattern ,  varObject );
			
			default -> null;
		} : null;
	}

	public Finder < UDM, ? > newFinder(  String operator, String pattern, Function< Unit< UDM >, Var < ? > > function ) {
		var varType = this.analyzer.getVarsGenericTypeAsVarType( function );
		return  varType != null ? newFinder( varType, operator, pattern, function ) : null; 
	}
	
	public Finder < UDM, ? > newFinder( VarType varType, String operator, String pattern, Function< Unit< UDM >, Var < ? > > function ) {
		return switch( varType ) {
			
			case STRING 		->  new StringFinder 	< UDM > ( this.udmClass, operator, pattern ,  function );
			case INT 			->  new IntFinder 		< UDM > ( this.udmClass, operator, pattern ,  function );
			case DOUBLE 		->  new DoubleFinder 	< UDM > ( this.udmClass, operator, pattern ,  function );
			case FLOAT 			->  new FloatFinder 	< UDM > ( this.udmClass, operator, pattern ,  function );
			case BYTE 			->  new ByteFinder 		< UDM > ( this.udmClass, operator, pattern ,  function );
			case BOOLEAN 		->  new BooleanFinder 	< UDM > ( this.udmClass, operator, pattern ,  function );
			case LOCAL_DATE 	->  new DateFinder 		< UDM > ( this.udmClass, operator, pattern ,  function );
			
			case ZONED_DATE_TIME  ->  new ZonedDateTimeFinder < UDM > ( this.udmClass, operator, pattern ,  function );
			
			default -> null;
		};
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
				var function =  this.analyzer.getFunction( atr.fieldId, this.functions );
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
