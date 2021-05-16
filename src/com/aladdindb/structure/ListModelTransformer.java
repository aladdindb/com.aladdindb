package com.aladdindb.structure;

import com.aladdindb.structure.sn.SnPoint;

public  class ListModelTransformer < ITEM_TYPE extends DataModel< ITEM_TYPE > > extends Transformer < ListModel< ITEM_TYPE > > {

	
	private final Transformer < ITEM_TYPE > itemTransformer;
	
	
	public ListModelTransformer( String nodeKey, Transformer < ITEM_TYPE > itemTransformer ) {
		super( nodeKey );
		this.itemTransformer = itemTransformer;
	}
	
	@Override
	public ListModel<ITEM_TYPE> newModel() {
		return new ListModel<ITEM_TYPE>();
	}
	
	@Override
	public ListModel < ITEM_TYPE > toModel( SnPoint src, ListModel < ITEM_TYPE > target ) {
		src.children.forEach( node -> {
			this.itemTransformer.toModel( node, target :: add );
		});
		return target;
	}

	@Override
	public SnPoint toNode( ListModel < ITEM_TYPE > src, SnPoint target ) {
		src.forEach( item -> {
			this.itemTransformer.toNode( item, target.children :: add );
		});
		return target;
	}
	
	
}
