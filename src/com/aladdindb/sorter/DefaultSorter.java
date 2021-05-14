package com.aladdindb.sorter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aladdindb.structure.DataModel;
import com.aladdindb.units.Units;
import com.aladdindb.units.models.Unit;
import com.aladdindb.util.Var;


/**
 * 
 * @author Macit Kandemir
 *
 */
public abstract class DefaultSorter <

	UDM 			extends DataModel		< UDM >, 
	SORTER_MODEL	extends DefaultSorter	< UDM, SORTER_MODEL, VT >,
	VT 

> implements Sorter < UDM, SORTER_MODEL > {

	
	public final Var 						< String > 		sortOrder = new Var<>();
	
	public final Var						< Units< UDM > > 	units = new Var<>();
	
	public final Comparator					< VT > 				comparator;
	public final UnitIdListBlocksGenerator	< UDM, VT>  		blockWise;
	
    //****************************************************************
    //						Constractor 
    //****************************************************************
	
	public DefaultSorter( SortOrder sortOrder ) {
		
		this.sortOrder	.set( sortOrder.name()	);
		this.comparator 	= this.newComparator();
		this.blockWise 		= new UnitIdListBlocksGenerator<>( this );
	}
	
    //****************************************************************
    //					DataModel Implements  
    //****************************************************************
	
	@Override
	public void fill( SORTER_MODEL model) {
		this.sortOrder	.set( model.sortOrder 	);
	}

    //****************************************************************
    //					Sorter Implements  
    //****************************************************************
	
	public List<String> sort( List<String> unitIdArray ) {
		
		Map< String, VT > map = new HashMap<>();
		this.units.get( units -> {
			unitIdArray.forEach( unitID -> {
				units.get( unitID, unit -> {
					var fieldVar = this.getField( unit );
					fieldVar.get( value -> map.put( unitID, value ) );
				});
			});
		});

		var rv 	= new ArrayList<String>();
		
		map.entrySet().stream().sorted( Map.Entry.comparingByValue( comparator ) ).forEach( entry -> {
			rv.add( entry.getKey() );
		});
		
		return rv;
	}
	
	
	@Override
	public void setUnits(Units<UDM> units) {
		this.units.set(units);
	}
	
	@Override
	public List< List < String> > sortBlockWise( List < String > unitIdArray ) {
		return this.blockWise.createUnitIdListBlocks( this.sort(unitIdArray) );
	}
	
    //****************************************************************
    //					 
    //****************************************************************
	
	public abstract Comparator<VT> newComparator();
	
	public abstract Var< VT > getField( Unit<UDM> model );

	
}
