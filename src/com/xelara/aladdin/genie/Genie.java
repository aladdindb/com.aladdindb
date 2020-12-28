package com.xelara.aladdin.genie;

import static com.xelara.aladdin.magiclamp.MagicLamp.ADD;
import static com.xelara.aladdin.magiclamp.MagicLamp.GET_ALL;
import static com.xelara.aladdin.magiclamp.MagicLamp.GET_BY_ID;
import static com.xelara.aladdin.magiclamp.MagicLamp.REMOVE;
import static com.xelara.aladdin.magiclamp.MagicLamp.UPDATE;

import java.nio.file.Path;
import java.util.function.Consumer;

import com.xelara.aladdin.magiclamp.model.WishModel;
import com.xelara.aladdin.unit.DbUnits;
import com.xelara.aladdin.unit.model.DbUnitListModel;
import com.xelara.aladdin.unit.model.DbUnitListModelParserDefault;
import com.xelara.aladdin.unit.model.DbUnitModel;
import com.xelara.aladdin.unit.model.DbUnitModelParser;
import com.xelara.core.Var;
import com.xelara.structure.xml.XML;


public class Genie < 
	DUM 	extends DbUnitModel				< DUM >,
	DUMP  	extends DbUnitModelParser 		< DUM >
> {
	
	public final	DUMP 											unitParser;
	public final 	DbUnitListModel					< DUM > 		unitList;
	public final 	DbUnitListModelParserDefault	< DUM, DUMP > 	unitListParser;
	public final	DbUnits							< DUM, DUMP >	units;
	
	public WishModel			wish;
	public Consumer < String > 	respConsumer;
	
	public Genie(
			Path 					dbPath,
			DUMP 					unitParser
	){
		this.unitParser			= unitParser;
		this.unitList			= new DbUnitListModel 				< DUM >			();
		this.unitListParser 	= new DbUnitListModelParserDefault	< DUM, DUMP >	( this.unitParser );
		this.units				= new DbUnits						< DUM, DUMP > 	( dbPath, unitParser);
	}
    
	
	public void process( WishModel wish, Consumer < String > respConsumer ) {
		this.wish = wish;
		this.respConsumer = respConsumer;
		wish.cmd.getValue( cmd -> {
			switch( cmd ) {
				case GET_BY_ID		: getUnitByID		(); break;
				case GET_ALL		: getAllUnits		(); break;
				case ADD			: addUnit			();	break;
				case UPDATE			: updateUnit		(); break;
				case REMOVE			: removeUnit		(); break;
			}
		});
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
    
    
}
