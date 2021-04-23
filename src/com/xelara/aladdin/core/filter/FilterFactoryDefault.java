package com.xelara.aladdin.core.filter;

import com.xelara.structure.DataModel;
import com.xelara.structure.sn.SnPoint;


public class FilterFactoryDefault < UDM extends DataModel< UDM > > implements FilterFactory< UDM > {


	public static final String FILTER_LOGICAL_AND 	= "filter:logicalOperations:and"; 
	public static final String FILTER_LOGICAL_OR 	= "filter:logicalOperations:or"; 
	
	
	
	public ParserModelFilter< UDM, ?, ? > createFilter( SnPoint node ) {
		 
		return switch( node.key.get() ) {
		
			case FILTER_LOGICAL_AND -> new LogicalAndOperations< UDM >( this ).toModel( node );
			
			default -> null;
		};
	}

	
	
}
