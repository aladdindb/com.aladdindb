package com.xelara.aladdin.genie;

import static com.xelara.aladdin.magiclamp.MagicLamp.ADD;
import static com.xelara.aladdin.magiclamp.MagicLamp.GET_ALL;
import static com.xelara.aladdin.magiclamp.MagicLamp.GET_BY_ID;
import static com.xelara.aladdin.magiclamp.MagicLamp.REMOVE;
import static com.xelara.aladdin.magiclamp.MagicLamp.UPDATE;

import java.nio.file.Path;
import java.util.function.Consumer;

import com.xelara.aladdin.magiclamp.model.WishModel;
import com.xelara.aladdin.magiclamp.wishes.addunit.AddRemoteProcess;
import com.xelara.aladdin.magiclamp.wishes.addunit.RemoteProcess;
import com.xelara.aladdin.unit.Units;
import com.xelara.aladdin.unit.model.DataModel;
import com.xelara.aladdin.unit.model.DataModelParser;
import com.xelara.aladdin.unit.model.UnitListModel;
import com.xelara.aladdin.unit.model.UnitListModelParser;
import com.xelara.aladdin.unit.model.UnitModel;
import com.xelara.aladdin.unit.model.UnitModelParser;
import com.xelara.core.util.Var;
import com.xelara.structure.sn.SnPoint;
import com.xelara.structure.xml.XML;


public class Genie < UGM extends DataModel < UGM > > { 
	
	
	private final	UnitModelParser 		< UGM > unitModelParser;
	private final 	UnitListModel			< UGM > unitListModel;
	public final 	UnitListModelParser		< UGM > unitListModelParser;
	public final	Units					< UGM > units;
	public final	DataModelParser 		< UGM > dataModelParser;
	
	public WishModel			wish;
	public Consumer < String > 	respConsumer;
	
	
	public Genie( Path dbPath, DataModelParser < UGM > dataModelParser ) {
		this.dataModelParser		= dataModelParser;
		this.unitModelParser		= new UnitModelParser 		< UGM > ( dataModelParser );
		this.unitListModel			= new UnitListModel 		< UGM > ();
		this.unitListModelParser 	= new UnitListModelParser	< UGM > ( dataModelParser );
		this.units					= new Units					< UGM > ( dbPath, dataModelParser );
	}
    
	public void process( SnPoint cmdSnPoint, Consumer < String > respConsumer ) {
		
		var cmd = getCommand(cmdSnPoint);
		cmd.process( cmdSnPoint, respConsumer );
		
	}
	
//	public void process( WishModel wish, Consumer < String > respConsumer ) {
//		this.wish = wish;
//		this.respConsumer = respConsumer;
//		wish.cmd.get( cmd -> {
//			switch( cmd ) {
//				case GET_BY_ID		: getUnitByID		(); break;
//				case GET_ALL		: getAllUnits		(); break;
//				case ADD			: addUnit			();	break;
//				case UPDATE			: updateUnit		(); break;
//				case REMOVE			: removeUnit		(); break;
//			}
//		});
//	}
	
	public void getUnitByID() {
		getUnitByID( unitListModel::add);
		parseUnitList();
	}

	public void getUnitByID( Consumer < UnitModel < UGM > > consumer ) {
		wish.sbj.get( unitID -> {
			units.getUnitModel ( unitID, consumer ); 
		});
	}

	public void getAllUnits() {
		units.forEach( unitListModel :: add );
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
				unitModelParser.toModel( unitNode, unit -> {
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

    public UnitModel < UGM > updateUnit() { 
    	Var < UnitModel < UGM > > rv2 = new Var<> ();
		wish.object.get( unitXmlStr -> {
			XML.parse( unitXmlStr, unitNode -> {
				unitModelParser.toModel( unitNode, unit -> {
					rv2.set ( unit );
					boolean rv = units.updateUnit( unit );
					respConsumer.accept( Boolean.toString( rv ) );
				});
			});
		});
		return rv2.get ();
    }

    public void parseUnitList() {
		unitListModelParser.toNode( unitListModel, unitListNode -> {
			XML.parse( unitListNode, respConsumer );
		});
    }
    
    private RemoteProcess getCommand( SnPoint cmdReqSnPoint ) throws IllegalArgumentException {
		return switch( cmdReqSnPoint.key.get() ) {
		
			case "wish:addUnit" -> new AddRemoteProcess<UGM>( this );
			
			default -> throw new IllegalArgumentException("Unexpected value: " + cmdReqSnPoint.key.get() ); 
		}; 
    	
    }
	
    
    
}
