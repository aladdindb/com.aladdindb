package com.xelara.aladdin.model.login;

import com.xelara.aladdin.unit.model.UnitModel;
import com.xelara.core.Var;

/**
 *
 * @author Macit Kandemir
 */
public class LoginUnit implements UnitModel < LoginUnit > {
    
    public final Var < String > user    = new Var<>();
    public final Var < String > pwd     = new Var<>();

    
    public LoginUnit() {
    }
    
    public LoginUnit( String user, String pwd ) {
        this.user	.setValue( user	);
        this.pwd	.setValue( pwd	);
    }

	@Override
	public void fill( LoginUnit unit ) {
		user	.fill ( unit.user 	);
		pwd		.fill ( unit.pwd 	);
	}
    
    
}
