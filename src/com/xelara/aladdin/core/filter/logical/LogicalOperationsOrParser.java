package com.xelara.aladdin.core.filter.logical;

import com.xelara.aladdin.core.filter.Filter;
import com.xelara.aladdin.core.filter.FilterEnum;
import com.xelara.aladdin.core.filter.FilterFactory;
import com.xelara.structure.DataModel;
import com.xelara.structure.DataParser;
import com.xelara.structure.sn.SnPoint;


public class LogicalOperationsOrParser < UDM extends DataModel< UDM > > extends DataParser< LogicalOperationsOr < UDM > > {

	
	private final  FilterFactory < UDM > factory; 
	
	
    //****************************************************************
    //						Constructor 
    //****************************************************************
	
	public LogicalOperationsOrParser( FilterFactory < UDM > factory ) { 
		
		super( FilterEnum.FILTER_LOGICAL_OR.cmd() );
		
		this.factory = factory;
	}
	
    //****************************************************************
    //					DataParser Implements
    //****************************************************************

	@Override
	public LogicalOperationsOr < UDM > newModel() {
		return new LogicalOperationsOr< UDM >( this.factory );
	}

	@Override
	public LogicalOperationsOr < UDM > toModel( SnPoint src, LogicalOperationsOr < UDM > target ) {
		
		src.children.forEach( node -> {
			this.factory.createFilter( node, target.filterList :: add );
		});
		
		return target;
	}

	@Override
	public SnPoint toNode( LogicalOperationsOr < UDM > src, SnPoint target ) {

		var array = src.filterList.toArray( new Filter[ src.filterList.size() ] );
//		 
		for( var filter : array ) {
			var node = filter.createParser().toNode( filter );
			target.children.add( node );  
		}
//		
		return target;
	}

    //****************************************************************
    //
    //****************************************************************
	
	
}
