package com.xelara.aladdin.index;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

import com.xelara.aladdin.index.model.IndexListModel;
import com.xelara.aladdin.index.model.IndexListModelParser;
import com.xelara.aladdin.index.model.IndexModel;
import com.xelara.aladdin.unit.model.DbUnitModel;
import com.xelara.core.Var;
import com.xelara.structure.xml.XML;

public class Index {
	
	private Path path;
	
	private IndexListModelParser listModelParser = new IndexListModelParser();
	
	public Index( Path path ) {
		this.path = path;
		this.createFile();
	}
	
	public void addUnit( DbUnitModel<?> dbUnitModel ) {
		getItems( list -> {
			list.add( new IndexModel( dbUnitModel.id.getValue() ) );
			saveItems( list );
		});
	}
	
	public void updateItem( DbUnitModel<?> dbUnit ) {
		getItems( list -> {
			list.updateItem( dbUnit.id.getValue() );
			saveItems( list );
		});
	}

	public void removeItem( DbUnitModel<?> dbUnit ) {
		getItems( list -> {
			list.remove( dbUnit.id.getValue() );
			saveItems( list );
		});
	}
	
	private void getItems( Consumer< IndexListModel  > consumer ) {
		XML.load( path, rootNode -> {
			listModelParser.parse( rootNode, consumer );
		});
	}
	
	private void saveItems( IndexListModel items ) {
		listModelParser.parse( items, listNode -> {
			XML.save( path, listNode);
		});
	}
	
	private void createFile() {
		if( !Files.exists( path )) {
			try {
				Files.createFile( path );
				listModelParser.parse( new IndexListModel(), index -> {
					XML.save(path, index);
				});
			} catch (IOException e) {
//				Logger.getLogger( getClass().getName() ).log(Level.SEVERE, "", e);
			}
		}
	}
	
    public void forEachItem( Consumer < IndexModel > consumer ) {
    	getItems( list -> {
    		list.forEach( consumer );
    	});
    }
	
    public boolean isEmpty() {
    	Var<Boolean> rv = new Var<>(true);
    	getItems( list -> {
    		rv.setValue( list.isEmpty() );
    	});
    	return rv.getValue();
    }
}
