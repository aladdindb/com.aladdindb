package com.aladdindb;

import java.nio.file.Path;

import com.aladdindb.finder.Finder;
import com.aladdindb.finder.logical.LogicalAndFinders;
import com.aladdindb.finder.logical.LogicalOrFinders;
import com.aladdindb.sorter.Sorter;
import com.aladdindb.sorter.SorterList;
import com.aladdindb.structure.DataModel;


public abstract class DefaultSupport < UDM extends DataModel< UDM > > implements Support < UDM > {
	
	private final String 	storeId;
	private final Path 		storeOrigin;
	
	
	public DefaultSupport( String storeId, Path storeOrigin ) {
		this.storeId 		= storeId;
		this.storeOrigin 	= storeOrigin;
	}

	
	@Override
	public String getStoreId() {
		return this.storeId;
	}
	
	@Override
	public Path getStoreOrigin() {
		return this.storeOrigin;
	}
	
}
