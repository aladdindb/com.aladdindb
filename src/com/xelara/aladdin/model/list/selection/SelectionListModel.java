package com.xelara.aladdin.model.list.selection;

import java.util.Map;
import java.util.StringTokenizer;

import com.xelara.aladdin.unit.model.DbUnitListModel;
import com.xelara.core.Var;

public class SelectionListModel extends DbUnitListModel < SelectionModel, SelectionListModel  > {
	
	
	public SelectionListModel() {
		super();
	}
	
	public SelectionListModel( SelectionModel... modelArray ) {
		super( modelArray );
	}
	
	/**
	 * 
	 * @param selections Eine mit " " getrennte folderID Liste
	 */
	public SelectionListModel( String selections ) {
		var list = new StringTokenizer(selections, " ");
		while( list.hasMoreElements() ) {
			this.add( new SelectionModel( list.nextToken()));
		}
	}
	
    public String toString( Map < String, String > map ) {
    	StringBuilder sb = new StringBuilder();
    	if( this.size() == 1) {
    		this.get(0).refUnitID.getValue( refUnitID -> sb.append( cover(refUnitID, map ) ) );
    	} else {
        	Var<Boolean> first = new Var<Boolean>(true );
        	this.forEach( item -> {
        		item.refUnitID.getValue( refUnitID -> {
        			sb.append( first.getValue() ? cover(refUnitID, map) : "," + cover(refUnitID, map ) );
        		});
        		first.setValue( false );
        	});
    	}
    	return sb.toString();
    }
    
    private String cover( String refUnitID, Map < String, String > map ) {
    	return ""+map.get(refUnitID)+"";
    }
    
	
	public boolean existSelection( String selectionID ) {
        Var < Boolean > rv = new Var<>( false );
        this.forEach( selection -> {
	        if( selection.equalsRefUnitID( selectionID ) ) rv.setValue( true );
		});
		return rv.getValue();
	}
}
