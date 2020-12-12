package com.xelara.aladdin.genie;

import static com.xelara.aladdin.magiclamp.MagicLamp.ADD;
import static com.xelara.aladdin.magiclamp.MagicLamp.GET_ALL;
import static com.xelara.aladdin.magiclamp.MagicLamp.GET_BY_ID;
import static com.xelara.aladdin.magiclamp.MagicLamp.REMOVE;
import static com.xelara.aladdin.magiclamp.MagicLamp.UPDATE;

import java.util.function.Consumer;

import com.xelara.aladdin.magiclamp.model.WishModel;
import com.xelara.aladdin.unit.DbUnits;
import com.xelara.aladdin.unit.model.DbUnitModel;
import com.xelara.aladdin.unit.model.DbUnitModelParser;
import com.xelara.aladdin.unit.model.DbUnitListModel;
import com.xelara.aladdin.unit.model.DbUnitListModelParser;
import com.xelara.core.Var;
import com.xelara.structure.xml.XML;


public class Genie < 

	DUM 	extends DbUnitModel				< DUM >,
	DUMP  	extends DbUnitModelParser 		< DUM >,
	ULM 	extends DbUnitListModel			< DUM, ULM >,
	ULMP 	extends DbUnitListModelParser	< DUM, ULM, DUMP >,
	UNITS 	extends DbUnits					< DUM, DUMP >

> {
	
	public final 	WishModel 	wish;
	
	public final	DUMP 	unitParser;
	public final 	ULM 	unitList;
	public final 	ULMP 	unitListParser;
	public final	UNITS	units;
	
	public final 	Consumer < String > respConsumer;
	
//	public final	Var< Consumer < Event > > eventAdd 			= new Var<>();
//	public final	Var< Consumer < Event > > eventRemove 		= new Var<>();
//	public final	Var< Consumer < Event > > updateListener 	= new Var<>();
	
	public Genie( 
			WishModel  wish,
			
			DUMP 		unitParser,
			ULM 		unitList,
			ULMP		unitListParser,
			UNITS 		units,
			
			Consumer < String > respConsumer
	){
		
		this.wish 				= wish;
		
		this.unitParser			= unitParser;
		this.unitList			= unitList;
		this.unitListParser 	= unitListParser;
		
		this.units				= units;
		
		this.respConsumer 		= respConsumer;
	}
    
	
	public void process( String cmd ) {
		switch( cmd ) {
			case GET_BY_ID		: getUnitByID		(); break;
			case GET_ALL		: getAllUnits		(); break;
			case ADD			: addUnit			();	break;
			case UPDATE			: updateUnit		(); break;
			case REMOVE			: removeUnit		(); break;
		}
	}
	
	public void getUnitByID() {
		getUnitByID( unitList::add);
		parseUnitList();
	}

	public void getUnitByID( Consumer < DUM > consumer ) {
		wish.sbj.getValue( unitID -> {
			units.getUnitModel ( unitID, consumer ); 
		});
	}

	public void getAllUnits() {
		units.forEach( unitList :: add );
		parseUnitList();
	}

    public void removeUnit() {
		wish.sbj.getValue( wishSbj -> {
			boolean rv = units.removeUnit( wishSbj );
			respConsumer.accept( Boolean.toString(rv) );
		});
    }

    public String addUnit() {
    	Var < String > rv = new Var<> ();
		wish.object.getValue( unitXmlStr -> {
			XML.parse( unitXmlStr, unitNode -> {
				unitParser.parse( unitNode, unit -> {
					String newID = units.addUnit( unit ) ;
					rv.setValue ( newID );
					respConsumer.accept( newID );
				});
			});
		});
		return rv.getValue ();
    }

    public DUM updateUnit() { 
    	Var < DUM > rv2 = new Var<> ();
		wish.object.getValue( unitXmlStr -> {
			XML.parse( unitXmlStr, unitNode -> {
				unitParser.parse( unitNode, unit -> {
					rv2.setValue ( unit );
					boolean rv = units.updateUnit( unit );
					respConsumer.accept( Boolean.toString( rv ) );
				});
			});
		});
		return rv2.getValue ();
    }

    public void parseUnitList() {
		unitListParser.parse( unitList, itemListNode -> {
			XML.parse( itemListNode, respConsumer );
		});
    }
    
//    public static class Event {
//    	
//    	public final Var < String > 	wishSbj 	= new Var<>(); 
//    	public final Var < String > 	wishObj 	= new Var<>(); 
//    	public final Var < Boolean > 	successful 	= new Var<>(); 
//    	public final Var < String > 	unitID 		= new Var<>(); 
//
//    }
    
}
