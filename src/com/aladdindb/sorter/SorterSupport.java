package com.aladdindb.sorter;

import java.util.function.Consumer;
import java.util.function.Function;

import com.aladdindb.UnitAnalyzer;
import com.aladdindb.sorter.types.LocalDateSorter;
import com.aladdindb.sorter.types.StringSorter;
import com.aladdindb.store.models.Unit;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.util.Var;

public class SorterSupport < UDM extends DataModel< UDM > > {
	
	
	public final Class < UDM > 	udmClass;
	
	public final UnitAnalyzer< UDM > analyzer;;

	private final Function< Unit< UDM >, Var<?> >[] functions;
	
	
	public SorterSupport(  Class < UDM > udmClass, Function< Unit< UDM >, Var<?> >... functions  ) {
		this.udmClass 		= udmClass;
		this.functions 		= functions;
		this.analyzer 		= new UnitAnalyzer<>(udmClass);
	}

	public Sorter<UDM, ?> newSorter( String fieldId ) {
		if( this.functions != null ) {
			var function =  this.analyzer.getEquals( fieldId,  this.functions );
			return function != null ? newSorter( function ) : null;
		}
		return null;
	}
	
	public Sorter < UDM, ? > newDateSorter(  Function< Unit< UDM >, Var < ? > > function ) {
		return this.newSorter( function );
	}
	
	public Sorter < UDM, ? > newSorter(  Function< Unit< UDM >, Var < ? > > function ) {
		return this.newSorter( SortOrder.ASCENDING,  function );
	}
	
	public Sorter < UDM, ? > newSorter(SortOrder sortOrder,  Function< Unit< UDM >, Var < ? > > function ) {
		var varType = this.analyzer.getVarType( function); 
		
		if( varType != null ) {
			return switch( varType ) {
				
				case STRING 		->  new StringSorter	< UDM >( sortOrder, this.udmClass ,  function );
				case LOCAL_DATE 	->  new LocalDateSorter	< UDM >( sortOrder, this.udmClass ,  function );
				
				default -> null;
				
			};
		}
		return null;
	}

    //****************************************************************
    //						  Sorter
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
				var function =  this.analyzer.getEquals( atr.fieldId, this.functions );
				if(function != null) {
					return newSorter( atr.sortOrder, function );
				}
			}
		}
		return null;
	}
	
	public Sorter< UDM, ? > newSorterByType( String sorterType ) { 
		return 	sorterType.equals( Sorter.LIST ) ? new SorterList< UDM >( this ) : null; 
	}
	
	//**********************************************************
	//					Sorters
	//**********************************************************
	

	
}
