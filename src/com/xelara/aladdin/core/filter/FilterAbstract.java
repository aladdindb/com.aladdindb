package com.xelara.aladdin.core.filter;

import java.util.function.Consumer;

import com.xelara.core.util.Var;
import com.xelara.structure.DataModel;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.SnAttributeAccess;


public abstract class FilterAbstract < 
	UDM 		extends DataModel 		< UDM >, 
	DATA_MODEL 	extends FilterAbstract	< UDM, DATA_MODEL, VT >,
	VT
> 

extends ParserModelFilter < UDM, DATA_MODEL, VT > {  	

	
	public enum ATR { operator, pattern }

	public final Var < String 	> operator 	= new Var<>();
	public final Var < String	> pattern 	= new Var<>();
	
	
    //****************************************************************
    //						Constructor 
    //****************************************************************

	public FilterAbstract( String tagKey, String operator, String pattern ) {
		super( tagKey );
		this.operator	.set( operator	);
		this.pattern	.set( pattern	);
	}
	
    //****************************************************************
    //					DataModel Implements
    //****************************************************************

	@Override
	public void fill( DATA_MODEL model ) {
		this.operator	.set( model.operator );
		this.pattern	.set( model.pattern  );
	}
	
	
    //****************************************************************
    //				Filter Implements ( prove ) 
    //****************************************************************
	
	@Override
	public boolean prove( UDM model ) {
		Var<Boolean> rv = new Var<>(false);
		this.getFieldValue( model, value -> {
			rv.set( provePattern( value ) );
		});
		return rv.get();
	}
	
	public abstract boolean provePattern( VT value );
	
    //****************************************************************
    //					
    //****************************************************************
	
	
	public void getFieldValue ( UDM model, Consumer< VT > consumer ) {
		var field = this.getField(model);
		if( field != null) {
			field.get( consumer );
		}
	}
	
	public abstract Var< VT > getField( UDM model );


    //****************************************************************
    //					DataParser Implements
    //****************************************************************
	
	@Override
	public DATA_MODEL toModel( SnPoint src, DATA_MODEL target ) {
		var srcAtr = new SnAttributeAccess(src);
		srcAtr.asStr.get( ATR.operator	,target.operator );
		srcAtr.asStr.get( ATR.pattern	,target.pattern );
		return target;
	}

	@Override
	public SnPoint toNode( DATA_MODEL src, SnPoint target ) {
		var srcAtr = new SnAttributeAccess(target);
		srcAtr.asStr.set( ATR.operator	,src.operator 	);
		srcAtr.asStr.set( ATR.pattern	,src.pattern 	);
		return target;
	}

    //****************************************************************
    //
    //****************************************************************
	
}
