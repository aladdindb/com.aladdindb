package com.xelara.aladdin.model.server;

import com.xelara.aladdin.unit.model.DataModel;
import com.xelara.core.Var;

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
		host.fill( unit.host );
		port.fill( unit.port );
		
	}
    
    
}
