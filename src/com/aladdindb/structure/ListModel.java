package com.aladdindb.structure;

import java.util.ArrayList;

public class ListModel <

		LIST_ITEM_TYPE extends Store < LIST_ITEM_TYPE > 

> extends ArrayList< LIST_ITEM_TYPE > implements Store< ListModel < LIST_ITEM_TYPE > > {

	
	@Override
	public void fill( ListModel<LIST_ITEM_TYPE> model) {
		model.forEach( this :: add );
	}
	
}
