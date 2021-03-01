package com.xelara.aladdin.genie;

import static com.xelara.aladdin.magiclamp.MagicLamp.ADD;
import static com.xelara.aladdin.magiclamp.MagicLamp.GET_ALL;
import static com.xelara.aladdin.magiclamp.MagicLamp.GET_BY_ID;
import static com.xelara.aladdin.magiclamp.MagicLamp.REMOVE;
import static com.xelara.aladdin.magiclamp.MagicLamp.UPDATE;

import java.nio.file.Path;
import java.util.function.Consumer;

import com.xelara.aladdin.magiclamp.model.WishModel;
import com.xelara.aladdin.unit.Units;
import com.xelara.aladdin.unit.model.UnitListModel;
import com.xelara.aladdin.unit.model.UnitListModelParser;
import com.xelara.aladdin.unit.model.UnitModel;
import com.xelara.aladdin.unit.model.UnitModelParser;
import com.xelara.aladdin.unit.model.DataModel;
import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.core.Var;
import com.xelara.structure.xml.XML;


public class Genie < DATA_MODEL extends DataModel < DATA_MODEL > > {
	
	private final	UnitModelParser 		< DATA_MODEL > unitParser;
	private final 	UnitListModel			< DATA_MODEL > unitList;
	public final 	UnitListModelParser		< DATA_MODEL > unitListParser;
	public final	Units					< DATA_MODEL > units;
	
	public WishModel			wish;
	public Consumer < String > 	respConsumer;
	
	public Genie( Path dbPath, DataModelParser < DATA_MODEL > dataModelParser) {
		this.unitParser			= new UnitModelParser 		< DATA_MODEL > ( dataModelParser );
		this.unitList			= new UnitListModel 		< DATA_MODEL > ();
		this.unitListParser 	= new UnitListModelParser	< DATA_MODEL > ( dataModelParser );
		this.units				= new Units					< DATA_MODEL > ( dbPath, dataModelParser );
	}
    
	
	public void process( WishModel wish, Consumer < String > respConsumer ) {
		this.wish = wish;
		this.respConsumer = respConsumer;
		wish.cmd.get( cmd -> {
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

	public void getUnitByID( Consumer < UnitModel < DATA_MODEL > > consumer ) {
		wish.sbj.get( unitID -> {
			units.getUnitModel ( unitID, consumer ); 
		});
	}

	public void getAllUnits() {
		units.forEach( unitList :: add );
		parseUnitList();
	}

    public void removeUnit() {
		wish.sbj.get( wishSbj -> {
			boolean rv = units.removeUnit( wishSbj );
			respConsumer.accept( Boolean.toString(rv) );
		});
    }

    public String addUnit() {
    	Var < String > rv = new Var<> ();
		wish.object.get( unitXmlStr -> {
			XML.parse( unitXmlStr, unitNode -> {
				unitParser.fromNode( unitNode, unit -> {
					unit.data.get( dataModel -> {
						String newID = units.addUnit( dataModel ) ;
						rv.set ( newID );
						respConsumer.accept( newID );
					});
				});
			});
		});
		return rv.get ();
    }

    public UnitModel < DATA_MODEL > updateUnit() { 
    	Var < UnitModel < DATA_MODEL > > rv2 = new Var<> ();
		wish.object.get( unitXmlStr -> {
			XML.parse( unitXmlStr, unitNode -> {
				unitParser.fromNode( unitNode, unit -> {
					rv2.set ( unit );
					boolean rv = units.updateUnit( unit );
					respConsumer.accept( Boolean.toString( rv ) );
				});
			});
		});
		return rv2.get ();
    }

    public void parseUnitList() {
		unitListParser.toNode( unitList, itemListNode -> {
			XML.parse( itemListNode, respConsumer );
		});
    }
    
    
    
}
