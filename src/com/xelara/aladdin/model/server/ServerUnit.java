package com.xelara.aladdin.model.server;

import com.xelara.aladdin.unit.model.UnitModel;
import com.xelara.core.Var;

/**
 *
 * @author Macit Kandemir
 */
public class ServerUnit implements UnitModel < ServerUnit > {
    
    public final Var < String   > host    = new Var<>();
    public final Var < Integer  > port    = new Var<>();

    
    public ServerUnit() {
    }
    
    public ServerUnit( String host, Integer port ) {
        this.host.setValue( host );
        this.port.setValue( port );
    }

	@Override
	public void fill( ServerUnit unit) {
		host.fill( unit.host );
		port.fill( unit.port );
		
	}
    
    
}
