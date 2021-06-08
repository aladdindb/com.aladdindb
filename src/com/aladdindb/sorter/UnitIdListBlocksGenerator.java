package com.aladdindb.sorter;

import java.util.ArrayList;
import java.util.List;

import com.aladdindb.store.models.Unit;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.IntIndex;
import com.aladdindb.util.Var;


/**
 * 
 * Eine Hilfs-Klasse für das gleichzeige Sortieren nach mehreren Feldern.
 * Mit dieser Klasse wird das sortierte Feld in Blöcke mit gleichem Feld-Inhlat
 * aufgeteilt und als eine Liste mit String UnitId Listen ( List<List<String>>)
 * zurückgegeben.
 * 
 * 
 * @author Macit Kandemir
 *
 */
public class UnitIdListBlocksGenerator < UDM extends DataModel< UDM >, VT >  {

	
	/**
	 * Rückgabe Liste mit String Listen
	 */
	private final ArrayList < List < String > > rv = new ArrayList<>();	
	
	/**
	 * UnitId Index
	 */
	private final IntIndex i = new IntIndex();						
	
	/**
	 * Das aktuelle Unit mit dem alle anderen Verglichen werden.
	 * Bei Gruppenwechsel bekommt es einen neuen Inhalt.
	 *  
	 */
	private final Var< Unit <UDM> > a = new Var<>();
	
	
	/**
	 * Wechselt innerhalb der Gruppe ständig.
	 */
	private final Var< Unit <UDM> > x = new Var<>();
	
	
	/**
	 * Units mit gleichem Feld-Inhalt werden hier als UnitId abgelegt.
	 */
	private final Var < ArrayList< String > > block = new Var<>();
	
	
	/**
	 * Das zuständige Sorter Objekt für das entsprechende Feld.
	 */
	private final DefaultSorter< UDM, ? extends DataModel < ? >, VT> sorter;
	
	
	protected UnitIdListBlocksGenerator(  DefaultSorter< UDM, ? extends DataModel < ? >, VT> sorter ) {
		this.sorter = sorter;
	}
	
	
	protected List < List < String > > createUnitIdListBlocks( List < String > sortedUnitIdList  ) {

		rv.clear(); i.reset();
		
		if( !sortedUnitIdList.isEmpty() ) {
			
			block.set( new ArrayList< String >() );

			sorter.storeVar.get( store -> {
				
				store.getUnitById( sortedUnitIdList.get( i.inc()	), a :: set  );
				
				a.get( unit -> { block.get().add( unit.id.get() ); });
				
				while( i.inc() < sortedUnitIdList.size() ) {
					
					store.getUnitById( sortedUnitIdList.get( i.get() ), x :: set  );
					
					if( this.compare( a.get(), x.get() ) != 0 ) {
						a		.set( x );
						rv		.add( block.get() );
						block	.set(  new ArrayList< String >() );
					}
					x.get( unit -> { block.get().add( unit.id.get() ); });
				}
			});
		}
		
		return rv;
	}
	
	private int compare( Unit <UDM> a, Unit <UDM> b ) {
		var f1 = a != null ? sorter.fieldGetter.apply(a) : null ;
		var f2 = b != null ? sorter.fieldGetter.apply(b) : null ;
		return sorter.comparator.compare( f1 != null ? f1.get(): null, f2 != null ? f2.get() : null );
	}
	
	
}
