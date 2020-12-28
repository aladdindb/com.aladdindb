package com.xelara.aladdin.unit.model;

import com.xelara.structure.snode.SNode;

public class DbUnitListModelParserDefault < UM extends DbUnitModel< UM >, UMP extends DbUnitModelParser< UM > > extends DbUnitListModelParser < UM, UMP > {

	public DbUnitListModelParserDefault( UMP dbUnitModelParser) {
		super( dbUnitModelParser );
	}
	
	@ Override
	public DbUnitListModel < UM > newModel() { 
		return new DbUnitListModel < UM > (); 
	}
	

	@ Override
	public DbUnitListModel < UM > parse( SNode src, DbUnitListModel < UM > target ) {
		super.parseList ( src, target );
		return target ;
	}
	

	@ Override
	public SNode parse( DbUnitListModel < UM > src, SNode target ) {
		super.parseList ( src, target );
		return target;
	}
}
