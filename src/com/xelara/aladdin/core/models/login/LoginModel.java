package com.xelara.aladdin.core.models.login;

import com.xelara.core.util.Var;
import com.xelara.structure.DataModel;

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
		user	.set ( unit.user 	);
		pwd		.set ( unit.pwd 	);
	}
    
    
}
