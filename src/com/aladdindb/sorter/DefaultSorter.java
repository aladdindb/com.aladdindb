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
public abstract class DefaultSorter < UDM extends DataModel	< UDM >, VT > implements Sorter< UDM > {

	
	public final Var < SortOrder > sortOrder 	= new Var<>();
	
	public final Comparator			< VT > 		comparator;
	public final UnitIdListBlocksGenerator	< UDM, VT>  blockWise;
	
	public final Var< Units< UDM > > units = new Var<>();
	
	public DefaultSorter( SortOrder sortOrder ) {
		
		this.sortOrder	.set( sortOrder	);
		this.comparator 	= this.createComparator();
		this.blockWise 		= new UnitIdListBlocksGenerator<>( this );
	}
	
	
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
	
	public abstract Comparator<VT> createComparator();
	
	public abstract Var< VT > getField( Unit<UDM> model );

	
}
