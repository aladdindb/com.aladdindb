package com.aladdindb.structure;

import java.util.ArrayList;

public class ListModel <

		LIST_ITEM_TYPE extends DataModel < LIST_ITEM_TYPE > 

> extends ArrayList< LIST_ITEM_TYPE > implements DataModel< ListModel < LIST_ITEM_TYPE > > {

	
	public ListModel() {
	}
	
	@Override
	public void fill( ListModel<LIST_ITEM_TYPE> model) {
		model.forEach( this :: add );
	}
	
}
