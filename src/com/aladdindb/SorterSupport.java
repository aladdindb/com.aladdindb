package com.aladdindb;

import java.util.function.Consumer;

import com.aladdindb.sorter.Sorter;
import com.aladdindb.sorter.SorterList;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.sn.SnPoint;

public class SorterSupport < UDM extends DataModel< UDM > > {
	
	
	public final Class < UDM > 	udmClass;
	
	public final UnitAnalyzer< UDM > analyzer;;

	
	public SorterSupport(  Class < UDM > udmClass ) {
		this.udmClass 		= udmClass;
		this.analyzer 		= new UnitAnalyzer<>(udmClass);
	}

    //****************************************************************
    //						  Sorter
    //****************************************************************
	
	public void newSorter( SnPoint sorterNode, Consumer < Sorter < UDM, ? extends DataModel<?> > > sorterConsumer ) {
		var rv = newSorter( sorterNode );
		if( rv != null ) sorterConsumer.accept( rv );
	}
	
	public Sorter< UDM, ? extends DataModel < ? > > newSorter( SnPoint sorterNode ) {
		var sorter = this.newSorter( sorterNode.key.get() );
		return sorter != null ? ( Sorter< UDM, ? extends DataModel < ? > >) sorter.newTransformer().toModel( sorterNode ) : null;
	}
	
	public Sorter< UDM, ? > newSorter( String sorterType ) { 
		return 	sorterType.equals( Sorter.LIST ) ? new SorterList< UDM >( this ) : null; 
	}
	
	//**********************************************************
	//					Sorters
	//**********************************************************
	
	public void newSorters( Consumer < SorterList< UDM > > sortersConsumer ) {
		var rv = newSorters();
		if( rv != null )sortersConsumer.accept( rv );
	}

	public SorterList< UDM > newSorters() {
		return new SorterList	< UDM >( this );
	}

	public SorterList< UDM > newSorters( Sorter...sorters ) {
		var rv = new SorterList	< UDM >( this );
		rv.addSorter(sorters);
		return rv;
	}


	
}
