package com.aladdindb;

import java.nio.file.Path;

import com.aladdindb.finder.Finder;
import com.aladdindb.finder.logical.LogicalAndOperations;
import com.aladdindb.finder.logical.LogicalOrOperations;
import com.aladdindb.sorter.Sorter;
import com.aladdindb.sorter.SorterList;
import com.aladdindb.structure.DataModel;


public abstract class DefaultSupport < UDM extends DataModel< UDM > > implements Support < UDM > {
	
	public final String storeId;
	
	public final Path 	origin;
	
	//**********************************************************
	//
	//**********************************************************
	
	public DefaultSupport( String storeId, Path origin ) {
		this.storeId 	= storeId;
		this.origin 	= origin;
	}

	//**********************************************************
	//
	//**********************************************************
	
	@Override
	public String getStoreId() {
		return this.storeId;
	}
	
	@Override
	public Path getOrigin() {
		return this.origin;
	}
	
	
	//**********************************************************
	//
	//**********************************************************

	@Override
	public Finder< UDM, ? > newFinder( String finderType ) {
		return 	finderType.equals( Type.LOGICAL_AND	.finder() ) ? new LogicalAndOperations	< UDM >( this ) : 
				finderType.equals( Type.LOGICAL_OR	.finder() ) ? new LogicalOrOperations	< UDM >( this ) : null; 
	}

	@Override
	public Sorter< UDM, ? > newSorter( String sorterType ) { 
		return 	sorterType.equals( Type.LIST.sorter() ) ? new SorterList< UDM >( this ) : null; 
	}
	
	//**********************************************************
	//
	//**********************************************************

	@Override
	public MagicLamp<UDM> newMagicLamp( GenieConnection genieConnection ) {
		return new MagicLamp <> ( this, genieConnection );
	}
	
	//**********************************************************
	//
	//**********************************************************

	public Genie < UDM > newGenie() {
		return new Genie <> ( this.getOrigin().resolve( getStoreId() ), this );
	}
	
	//**********************************************************
	//
	//**********************************************************

	public LogicalOrOperations < UDM > newOrList() {
		return new LogicalOrOperations	< UDM >( this );
	}

	public LogicalOrOperations< UDM > newOrList( Finder... finders ) {
		var rv = new LogicalOrOperations< UDM >( this );
		rv.addFinder( finders );
		return rv;
	}

	//**********************************************************
	//
	//**********************************************************

	public LogicalAndOperations< UDM > newAndList() {
		return new LogicalAndOperations	< UDM >( this );
	}

	public LogicalAndOperations< UDM > newAndList( Finder... finders ) {
		var rv = new LogicalAndOperations	< UDM >( this );
		rv.addFinder( finders );
		return rv;
	}

	//**********************************************************
	//
	//**********************************************************
	
	public SorterList< UDM > newSortList() {
		return new SorterList	< UDM >( this );
	}

	public SorterList< UDM > newSortList( Sorter...sorters ) {
		var rv = new SorterList	< UDM >( this );
		rv.addSorter(sorters);
		return rv;
	}
	
	//**********************************************************
	//
	//**********************************************************
}
