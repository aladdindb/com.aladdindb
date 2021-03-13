package com.xelara.aladdin.model.login;

import com.xelara.aladdin.unit.model.DataModel;
import com.xelara.core.util.Var;

/**
 *
 * @author Macit Kandemir
 */
public class LoginModel implements DataModel < LoginModel > {
    
    public final Var < String > user    = new Var<>();
    public final Var < String > pwd     = new Var<>();

    
    public LoginModel() {
    }
    
    public LoginModel( String user, String pwd ) {
        this.user	.set( user	);
        this.pwd	.set( pwd	);
    }

	@Override
	public void fill( LoginModel unit ) {
		user	.fill ( unit.user 	);
		pwd		.fill ( unit.pwd 	);
	}
    
    
}
