package com.aladdindb.units;

import com.aladdindb.MagicLamp;
import com.aladdindb.method.resp.get.block.BlockNavResp;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.sn.SnLineNavi;
import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.units.models.Unit;
import com.aladdindb.util.LineNavigator;
import com.aladdindb.util.Var;



public class UnitsNavi < UDM extends DataModel < UDM > > implements LineNavigator < Unit < UDM > >  {

	
	private BlockNavResp 			blockResp;
	private SnLineNavi 				unitIdNav;
	
	private final MagicLamp< UDM > 	magicLamp;
	
	public UnitsNavi( BlockNavResp blockResp, MagicLamp< UDM > magicLamp ) {
		
		this.blockResp	= blockResp;
		this.magicLamp 	= magicLamp;
		
		this.blockResp.unitsIdBlock.get( idsStr -> {
			 var idArray = idsStr.split(",");

			 SnPoint rootNode = new SnPoint();
			 for( var id : idArray ) {
				 rootNode.children.add( new SnPoint("", id.trim() ) );
			 }
			 this.unitIdNav =  new SnLineNavi( rootNode.children.snBottom.get() ); 
		});
	}

	@Override
	public boolean hasRight() {
		return this.unitIdNav.hasRight();
	}

	@Override
	public boolean hasLeft() {
		return this.unitIdNav.hasLeft();
	}

	@Override
	public Unit < UDM > right() {
		Var< Unit< UDM > > rv = new Var<>();
		if( hasRight() ) {
			this.unitIdNav.right().value.get( id -> {
				this.magicLamp.getByID(id, resp -> {
					resp.unit.get( rv :: set );
				} );
			}); 
		}
		return rv.get();
	}

	@Override
	public Unit < UDM > left() {
		Var< Unit< UDM > > rv = new Var<>();
		if( hasLeft() ) {
			this.unitIdNav.left().value.get( id -> {
				this.magicLamp.getByID(id, resp -> {
					resp.unit.get( rv :: set );
				} );
			}); 
		}
		return rv.get();
	}
	
	
	
	
	
}
