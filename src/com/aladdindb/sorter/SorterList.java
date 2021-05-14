package com.aladdindb.sorter;

import java.util.ArrayList;
import java.util.List;

import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.DataTransformer;
import com.aladdindb.units.Units;
import com.aladdindb.util.IntIndex;


/**
 * 
 * @author Macit Kandemir
 *
 */
public  class SorterList < UDM extends DataModel < UDM > > implements Sorter< UDM, SorterList < UDM > > {

	
    //****************************************************************
    //						Class-Attributes 
    //****************************************************************
	
	private final  SorterSupplier < UDM > supplier;
	
	
	public final List< Sorter < UDM, ? extends DataModel< ? > > > sorters = new ArrayList<>();
	
	private IntIndex si = new IntIndex();
	
	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public SorterList( SorterSupplier< UDM > supplier ) {
		this.supplier = supplier;
	}
	
	
	public void addSorter(  Sorter<  UDM,  ? extends DataModel< ? >  > sorter ) {
		this.sorters.add(sorter);
	}
	
	public void addSorter(  Sorter<  UDM,  ? extends DataModel< ? >  >... sorters ) {
		for( var sorter : sorters ) this.sorters.add( sorter );
	}
	
    //****************************************************************
    //				Filter Implements ( prove ) 
    //****************************************************************
	
	@Override
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
	public void setUnits( Units<UDM> units ) {
		this.sorters.forEach( sorter -> sorter.setUnits(units) );
	}
	
    //****************************************************************
    //					DataModel Implements
    //****************************************************************

	@Override
	public void fill( SorterList < UDM > model) {
		this.sorters.clear();
		model.sorters.forEach( this.sorters :: add );
	}
	
    //****************************************************************
    //
    //****************************************************************
	
	@Override
	public DataTransformer< SorterList < UDM > > newTransformer() {
		return new SorterListTransformer< UDM >( this.supplier ); 
	}
	
}
