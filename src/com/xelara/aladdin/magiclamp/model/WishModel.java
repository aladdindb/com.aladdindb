package com.xelara.aladdin.magiclamp.model;

import com.xelara.aladdin.unit.model.UnitModel;
import com.xelara.core.Var;

/**
 *
 * @author Macit Kandemir
 */ 
public class WishModel  implements UnitModel < WishModel > {
    
    public final Var < String 	> section	= new Var<>();
    public final Var < String 	> cmd 		= new Var<>();
    public final Var < String 	> sbj		= new Var<>();
    public final Var < String 	> userID	= new Var<>();
    public final Var < String 	> object 	= new Var<>();

    public WishModel () {
    }

    public WishModel ( String section, String cmd, String sbj) {
    	this.section	.setValue( section ); 
    	this.cmd		.setValue( cmd		);
    	this.sbj		.setValue( sbj );
    }
    
	@Override
	public void fill( WishModel model ) {
		section		.fill( model.section );
		cmd			.fill( model.cmd );
		sbj			.fill( model.sbj );
		userID		.fill( model.userID );
		object		.fill( model.object );
	}
	
}
