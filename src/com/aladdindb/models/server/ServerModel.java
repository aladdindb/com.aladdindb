package com.aladdindb.models.server;

import com.aladdindb.structure.DefaultDataModel;
import com.aladdindb.util.Parent;
import com.aladdindb.util.Var;

/**
 *
 * @author Macit Kandemir
 */
public class ServerModel extends DefaultDataModel < ServerModel > {
    
    public final Var < String   > host    = new Var<>( this );
    public final Var < Integer  > port    = new Var<>( this );

    
    public ServerModel( Parent parent ) {
    	this( parent, null, null );
    }
    
    public ServerModel( Parent parent, String host, Integer port ) {
    	super( parent );
    	
        this.host.set( host );
        this.port.set( port );
    }

	@Override
	public void fill( ServerModel unit) {
		host.set( unit.host );
		port.set( unit.port );
		
	}
    
}
