package com.xelara.aladdin.magiclamp.model;

import com.xelara.aladdin.unit.model.DataModel;
import com.xelara.core.Var;

/**
 *
 * @author Macit Kandemir
 */ 
public class WishModel  implements DataModel < WishModel > {
    
    public final Var < String 	> invokeID	= new Var<>();
    public final Var < String 	> cmd 		= new Var<>();
    public final Var < String 	> sbj		= new Var<>();
    public final Var < String 	> userID	= new Var<>();
    public final Var < String 	> object 	= new Var<>();

    public WishModel () {
    }

    public WishModel ( String invokeID, String cmd, String sbj) {
    	this.invokeID	.set( invokeID ); 
    	this.cmd		.set( cmd		);
    	this.sbj		.set( sbj );
    }
    
	@Override
	public void fill( WishModel model ) {
		invokeID	.fill( model.invokeID );
		cmd			.fill( model.cmd );
		sbj			.fill( model.sbj );
		userID		.fill( model.userID );
		object		.fill( model.object );
	}
	
}
