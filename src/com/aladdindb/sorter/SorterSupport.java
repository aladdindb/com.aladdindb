package com.aladdindb.sorter;

import java.util.function.Consumer;
import java.util.function.Function;

import com.aladdindb.UnitAnalyzer;
import com.aladdindb.UnitAnalyzer.VarType;
import com.aladdindb.sorter.types.BooleanSorter;
import com.aladdindb.sorter.types.ByteSorter;
import com.aladdindb.sorter.types.DateSorter;
import com.aladdindb.sorter.types.DoubleSorter;
import com.aladdindb.sorter.types.FloatSorter;
import com.aladdindb.sorter.types.IntSorter;
import com.aladdindb.sorter.types.StringSorter;
import com.aladdindb.sorter.types.ZonedDateTimeSorter;
import com.aladdindb.store.models.Unit;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.util.Var;

public class SorterSupport < UDM extends DataModel< UDM > > {
	
	
	public final Class 			< UDM >	udmClass;
	public final UnitAnalyzer	< UDM > analyzer;;

	private final Function< Unit< UDM >, Var<?> >[] functions;
	
	
    //****************************************************************
    //						  Constructor
    //****************************************************************

	public SorterSupport(  Class < UDM > udmClass, Function< Unit< UDM >, Var<?> >... functions  ) {
		this.udmClass 		= udmClass;
		this.functions 		= functions;
		this.analyzer 		= new UnitAnalyzer<>(udmClass);
	}

    //****************************************************************
    //						  Sorter 
    //****************************************************************
	
	public Sorter < UDM, ? > newSorter(  Function< Unit< UDM >, Var < ? > > function ) {
		return this.newSorter( SortOrder.ASCENDING,  function );
	}

	public Sorter < UDM, ? > newSorter( Var < ? > varObject ) {
		return this.newSorter(null, varObject);
	}
	
	public Sorter < UDM, ? > newSorter( SortOrder sortOrder, Var < ? > varObject ) {
		
		var varType = this.analyzer.getVarsGenericTypeAsVarType( varObject);
		
		return varType != null ? switch( varType ) {
		
			case STRING 		->  new StringSorter	< UDM >( this.udmClass ,sortOrder, varObject );
			case INT 			->  new IntSorter		< UDM >( this.udmClass ,sortOrder, varObject );
			case DOUBLE 		->  new DoubleSorter	< UDM >( this.udmClass ,sortOrder, varObject );
			case FLOAT 			->  new FloatSorter		< UDM >( this.udmClass ,sortOrder, varObject );
			case BYTE 			->  new ByteSorter		< UDM >( this.udmClass ,sortOrder, varObject );
			case BOOLEAN 		->  new BooleanSorter	< UDM >( this.udmClass ,sortOrder, varObject );
			case LOCAL_DATE 	->  new DateSorter		< UDM >( this.udmClass ,sortOrder, varObject );
			
			case ZONED_DATE_TIME -> new ZonedDateTimeSorter	< UDM >( this.udmClass ,sortOrder,   varObject );
			
			default -> null;
		} : null;
		
	}
	
	public Sorter < UDM, ? > newSorter( SortOrder sortOrder, Function< Unit< UDM >, Var < ? > > function ) {
		
		var varType = this.analyzer.getVarsGenericTypeAsVarType( function);
		
		return varType != null ? switch( varType ) {
		
			case STRING 		->  new StringSorter	< UDM >( this.udmClass, sortOrder ,  function );
			case INT 			->  new IntSorter		< UDM >( this.udmClass, sortOrder ,  function );
			case DOUBLE 		->  new DoubleSorter	< UDM >( this.udmClass, sortOrder ,  function );
			case FLOAT 			->  new FloatSorter		< UDM >( this.udmClass, sortOrder ,  function );
			case BYTE 			->  new ByteSorter		< UDM >( this.udmClass, sortOrder ,  function );
			case BOOLEAN 		->  new BooleanSorter	< UDM >( this.udmClass, sortOrder ,  function );
			case LOCAL_DATE 	->  new DateSorter		< UDM >( this.udmClass, sortOrder ,  function );
			
			case ZONED_DATE_TIME -> new ZonedDateTimeSorter	< UDM >( sortOrder, this.udmClass ,  function );
			
			default -> null;
		} : null;
	}
	
    //****************************************************************
    //						  Sorter by Node
    //****************************************************************
	
	public void newSorter( SnPoint sorterNode, Consumer < Sorter < UDM, ? extends DataModel<?> > > sorterConsumer ) {
		var rv = newSorter( sorterNode );
		if( rv != null ) sorterConsumer.accept( rv );
	}
	
	public Sorter< UDM, ? extends DataModel < ? > > newSorter( SnPoint sorterNode ) {
		var sorter = this.newSorterByType( sorterNode.key.get() );
		if( sorter != null ) {
			return ( Sorter< UDM, ? extends DataModel < ? > >) sorter.newTransformer().toModel( sorterNode ); 
		} else {
			var atr = DefaultSorterTransformer.newSorterAtr(sorterNode);
			
			if( atr.fieldId != null && !atr.fieldId.isEmpty() ) {
				var function =  this.analyzer.getFunction( atr.fieldId, this.functions );
				if(function != null) {
					return newSorter( atr.sortOrder, function );
				}
			}
		}
		return null;
	}
	
	//**********************************************************
	//					Sorter by Type
	//**********************************************************

	public Sorter< UDM, ? > newSorterByType( String sorterType ) { 
		return 	sorterType.equals( Sorter.LIST ) ? new SorterList< UDM >( this ) : null; 
	}

	//**********************************************************
	//					Add Sorters
	//**********************************************************

	public SorterList< UDM > newSorterList( Sorter... sorters ) {
		var rv = new SorterList< UDM >( this );
		rv.addSorter( sorters );
		return rv;
	}
	
	
}
