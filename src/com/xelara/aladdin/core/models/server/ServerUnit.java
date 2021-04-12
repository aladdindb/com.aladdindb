package com.xelara.aladdin.core.models.server;

import com.xelara.aladdin.core.DataModel;
import com.xelara.core.util.Var;

/**
 *
 * @author Macit Kandemir
 */
public class ServerUnit implements DataModel < ServerUnit > {
    
    public final Var < String   > host    = new Var<>();
    public final Var < Integer  > port    = new Var<>();

    
    public ServerUnit() {
    }
    
    public ServerUnit( String host, Integer port ) {
        this.host.set( host );
        this.port.set( port );
    }

	@Override
	public void fill( ServerUnit unit) {
		host.set( unit.host );
		port.set( unit.port );
		
	}
    
    
}
