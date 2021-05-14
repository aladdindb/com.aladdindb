package com.aladdindb.models.login;

import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;

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
