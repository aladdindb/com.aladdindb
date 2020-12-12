package com.xelara.aladdin.index;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Consumer;

import com.xelara.aladdin.index.model.IndexModel;
import com.xelara.aladdin.model.list.selection.SelectionListModel;
import com.xelara.aladdin.model.list.selection.SelectionModel;
import com.xelara.aladdin.unit.model.DbUnitModel;
import com.xelara.core.io.Filess;

/**
 * 
 * Werte von Unit Feldern einer DB können RefID's zu Units einer fremden DB sein.
 * Für jedes Unit der refferenzierten fremden DB, kann eine Liste erzeugt werden, 
 * in welcher alle Units dieser DB welche ein RefID auf das Unit der fremden DB 
 * enthält, eingetragen werden.
 * Suchergebnisse von allen Units, die ein RefID zur Unit einer bestimmten Fremden DB
 * enhalten, könne auf dieser Weise wesentlich schneller erzeugt werden.
 * 
 * @author macit
 *
 */
public class IndexOrigin {
	
	private Path path;
	
	public IndexOrigin( Path path ) {
		this.path = path;
		try {
			Files.createDirectories(path);
		} catch (IOException e) {
//				Logger.getLogger( getClass().getName()).log( Level.SEVERE, "",  e );
		}
	}
	
	public void add( SelectionListModel selections,  DbUnitModel<?> dbUnit ) {
		selections.forEach( selection -> {
			getIndex( selection, index -> {
				index.addUnit( dbUnit );
			});
		});
	}
	
	public void update( SelectionListModel oldSelections, SelectionListModel newSelections, DbUnitModel<?> dbUnitOld, DbUnitModel<?> dbUnitNew  ) {
		this.remove	( oldSelections, dbUnitOld );
		this.add	( newSelections, dbUnitNew );
	}

	public void update( SelectionListModel selections, DbUnitModel<?> dbUnit ) {
		selections.forEach( selection -> {
			getIndex( selection, index -> {
				index.updateItem( dbUnit );
			});
		});
	}

	public void remove( SelectionListModel selections, DbUnitModel<?> dbUnit ) {
		selections.forEach( selection -> {
			getIndex( selection, index -> {
				index.removeItem( dbUnit );;
			});
		});
	}
	
	public void getIndex( SelectionModel selection, Consumer< Index > consumer ) {
		selection.refUnitID.getValue( selectionID -> getIndex( selectionID, consumer ) );
	}
	
	public void getIndex( String simpleUnitID, Consumer< Index > consumer ) {
		consumer.accept( new Index( path.resolve( simpleUnitID) ) );
	}

	public void forEachItemInAllIndexes( Consumer< IndexModel > consumer ) {
		forEachIndex( index -> {
			index.forEachItem(consumer);
		});
	}
	
    public void forEachIndex( Consumer < Index > consumer ) {
        Filess.forEachDirStream( path, indexPath -> {
        	consumer.accept( new Index(indexPath) );
        });
    }
	

    public void forEachItemInIndex( String indexID, Consumer < IndexModel > consumer ) {
		var index = new Index( path.resolve( indexID ));
		index.forEachItem( consumer );
    }

    public void removeIndex(String indexID) {
    	Path path = this.path.resolve(indexID);
    	try {
			Files.deleteIfExists(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
