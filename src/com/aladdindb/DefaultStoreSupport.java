package com.aladdindb;

import java.nio.file.Path;

import com.aladdindb.structure.DataModel;


public abstract class DefaultStoreSupport < UDM extends DataModel< UDM > > implements StoreSupport < UDM > {
	
	private final String 	storeId;
	private final Path 		storeOrigin;
	
	
	public DefaultStoreSupport( String storeId, Path storeOrigin ) {
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
