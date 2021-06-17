package com.aladdindb.models.login;

import com.aladdindb.structure.DefaultDataModel;
import com.aladdindb.util.Parent;
import com.aladdindb.util.Var;

/**
 *
 * @author Macit Kandemir
 */
public class LoginModel extends DefaultDataModel < LoginModel > {
    
    public final Var < String > user    = new Var<>( this );
    public final Var < String > pwd     = new Var<>( this );

    public LoginModel( Parent parent ) {
    	this( parent, null, null );
    }
    
    public LoginModel( Parent parent, String user, String pwd ) {
    	super( parent );
        this.user	.set( user	);
        this.pwd	.set( pwd	);
    }

	@Override
	public void fill( LoginModel unit ) {
		user	.set ( unit.user 	);
		pwd		.set ( unit.pwd 	);
	}
    
    
}
