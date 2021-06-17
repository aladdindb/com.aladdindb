package com.aladdindb.sorter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.aladdindb.store.Store;
import com.aladdindb.store.models.Unit;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;


/**
 * 
 * @author Macit Kandemir
 *
 */
public abstract class DefaultSorter <

	UDM 			extends DataModel			< UDM >, 
	SORTER_MODEL	extends DefaultSorter	< UDM, SORTER_MODEL, VT >,
	VT 

> implements Sorter < UDM, SORTER_MODEL > {

	
	public final Var < String > 		sortOrderVar 	= new Var<>();
	public final Var < Store< UDM > > 	storeVar 		= new Var<>();
	public final Var < String> 			fieldId 		= new Var<>();

	public final Comparator					< VT > 				comparator;
	public final UnitIdListBlocksGenerator	< UDM, VT>  		blockWise;
	
	public final Function < Unit < UDM >, Var< ? > > 			fieldGetter;
	
	public final Class<UDM> udmClass;
	
    //****************************************************************
    //						Constractor 
    //****************************************************************
	
	public DefaultSorter( Class<UDM> udmClass, SortOrder sortOrder, Function < Unit < UDM >, Var< ? > > fieldGetter ) {
		
		this.sortOrderVar	.set( sortOrder.name()	);
		this.comparator 	= this.newComparator();
		this.blockWise 		= new UnitIdListBlocksGenerator<>( this );
		
		this.udmClass 		= udmClass;
		this.fieldGetter	= fieldGetter;
	}
	
	public String getField() {
		if( this.fieldGetter != null ) {
			var field = this.fieldGetter.apply( new Unit<>( newDataObject()) );
			if( field != null) return field.key();
		}
		return null;
	}
	
	public UDM newDataObject() {
		try {
			return this.udmClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
	
    //****************************************************************
    //					DataModel Implements  
    //****************************************************************
	
	@Override
	public void fill( SORTER_MODEL model) {
		this.sortOrderVar	.set( model.sortOrderVar 	);
	}

    //****************************************************************
    //					Sorter Implements  
    //****************************************************************
	
	public List<String> sort( List<String> unitIdArray ) {
		
		Map< String, VT > map = new HashMap<>();
		this.storeVar.get( store -> {
			unitIdArray.forEach( unitId -> { 
				store.getUnitById( unitId, unit -> {
					var field = this.fieldGetter.apply( unit );
					if( field != null) {
						field.get( value -> map.put( unitId, (VT)value ) );
					}
				});
			});
		});

		var rv 	= new ArrayList<String>();
		
		
		var orderType = switch ( SortOrder.valueOf( sortOrderVar.get() ) ) {
			case DESCENDING -> comparator.reversed();
			default 		-> comparator;
		};
		
		map.entrySet().stream().sorted( Map.Entry.comparingByValue( orderType ) ).forEach( entry -> {
			rv.add( entry.getKey() );
		});
		
		return rv;
	}
	
	
	@Override
	public void setStore( Store<UDM> store ) {
		this.storeVar.set(store);
	}
	
	@Override
	public List< List < String> > sortBlockWise( List < String > unitIdArray ) {
		return this.blockWise.createUnitIdListBlocks( this.sort(unitIdArray) );
	}
	
    //****************************************************************
    //					 
    //****************************************************************
	
	public abstract Comparator<VT> newComparator();
	

}
