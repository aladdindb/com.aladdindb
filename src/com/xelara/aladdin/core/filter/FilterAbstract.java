package com.xelara.aladdin.core.filter;

import java.util.function.Consumer;

import com.xelara.core.util.Var;
import com.xelara.structure.DataModel;
import com.xelara.structure.DataParser;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.types.SnAttributeAccess;

public abstract class FilterAbstract < UDM extends DataModel < UDM >, VT > 	
		extends 	DataParser	< FilterAbstract < UDM, VT > > 
		implements 	Filter 		< UDM >, 
					DataModel 	< FilterAbstract < UDM, VT > > {

	
	enum ATR { operator, pattern }
	
	
	
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

	public final Var < String 	> operator 	= new Var<>();
	public final Var < String	> pattern 	= new Var<>();

	@Override
	public void fill( FilterAbstract< UDM, VT > model ) {
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
	public FilterAbstract< UDM, VT > toModel( SnPoint src, FilterAbstract< UDM, VT > target ) {
		var srcAtr = new SnAttributeAccess(src);
		srcAtr.asStr.get( ATR.operator	,target.operator );
		srcAtr.asStr.get( ATR.pattern	,target.pattern );
		return target;
	}

	@Override
	public SnPoint toNode( FilterAbstract< UDM, VT > src, SnPoint target ) {
		var srcAtr = new SnAttributeAccess(target);
		srcAtr.asStr.set( ATR.operator	,src.operator 	);
		srcAtr.asStr.set( ATR.pattern	,src.pattern 	);
		return target;
	}

    //****************************************************************
    //
    //****************************************************************
	
}
