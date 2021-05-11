package com.aladdindb.sorter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.aladdindb.structure.DataModel;
import com.aladdindb.units.Units;
import com.aladdindb.util.IntIndex;


/**
 * 
 * @author Macit Kandemir
 *
 */
public  class SorterListSorter < UDM extends DataModel	< UDM > > implements Sorter< UDM > {

	
	public final List< Sorter< UDM > > sorters;
	
	private IntIndex si = new IntIndex();
	
	public SorterListSorter(  Sorter< UDM >... sorters ) {
		this( Arrays.asList( sorters ) );
	}
	
	public SorterListSorter(  List < Sorter< UDM > > sorters ) {
		this.sorters 	= sorters;
	}
	
	public List<String> sort( List< String > unitIdArray ) {
		var rv = new ArrayList<String>();
		if( sorters != null && sorters.size() > 0 ) {
			sortRecursiv(  sorters.get( si.inc() ).sortBlockWise( unitIdArray ), rv );
			return rv;
		} return unitIdArray;
	}
	
//	public List<String> sort( List< String > unitIdArray ) {
//		var rv = new ArrayList<String>();
//		sorters.get(0).sortBlockWise(unitIdArray).forEach( block -> {
//			block.forEach( rv :: add );
//		});
//		return rv;
//	}

	private void sortRecursiv( List< List<String> > blockList, List<String> rv ) {
		blockList.forEach( block -> {
			if( block.size() > 1 ) {
				if( si.get() < sorters.size()  ) {
					sortRecursiv( sorters.get( si.inc() ).sortBlockWise( block ), rv );
					si.dec();
				} else block.forEach( rv :: add );
			} else rv.add( block.get(0) );
		});
	}

	@Override
	public List<List<String>> sortBlockWise(List<String> unitIdList) {
		return null;
	}
	
	@Override
	public void setUnits(Units<UDM> units) {
		this.sorters.forEach( sorter -> sorter.setUnits(units) );
	}
	
}
