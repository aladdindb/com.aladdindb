package com.aladdindb.sorter;

import java.util.ArrayList;
import java.util.List;

import com.aladdindb.structure.DataModel;
import com.aladdindb.units.Units;
import com.aladdindb.util.IntIndex;


/**
 * 
 * @author Macit Kandemir
 *
 */
public  class SorterList < UDM extends DataModel < UDM > > implements Sorter< UDM > {

	
	public final List< Sorter< UDM > > sorters = new ArrayList<>();
	
	private IntIndex si = new IntIndex();
	
	
	public void addSorter(  Sorter< UDM > sorter ) {
		this.sorters.add(sorter);
	}
	
	public void addSorter(  Sorter< UDM >... sorters ) {
		for( var sorter : sorters ) this.sorters.add( sorter );
	}
	
	public List<String> sort( List< String > unitIdArray ) {
		var rv = new ArrayList<String>();
		if( sorters != null && sorters.size() > 0 ) {
			sortRecursiv(  sorters.get( si.inc() ).sortBlockWise( unitIdArray ), rv );
			return rv;
		} return unitIdArray;
	}
	
	private void sortRecursiv( List< List<String> > blockList, List<String> rv ) {
		blockList.forEach( block -> {
			if( block.size() > 1 ) {
				if( si.inc() < sorters.size()  ) {
					sortRecursiv( sorters.get( si.get() ).sortBlockWise( block ), rv );
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
