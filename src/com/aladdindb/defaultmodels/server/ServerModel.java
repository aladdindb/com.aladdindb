package com.aladdindb.defaultmodels.server;

import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;

/**
 *
 * @author Macit Kandemir
 */
public class ServerModel implements DataModel < ServerModel > {
    
    public final Var < String   > host    = new Var<>();
    public final Var < Integer  > port    = new Var<>();

    
    public ServerModel() {
    }
    
    public ServerModel( String host, Integer port ) {
        this.host.set( host );
        this.port.set( port );
    }

	@Override
	public void fill( ServerModel unit) {
		host.set( unit.host );
		port.set( unit.port );
		
	}
    
    
}
