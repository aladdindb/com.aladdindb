package com.xelara.aladdin;

import java.nio.file.Path;
import java.util.function.Consumer;

import com.xelara.aladdin.genie.Genie;
import com.xelara.aladdin.index.IndexOrigin;
import com.xelara.aladdin.magiclamp.MagicLamp;
import com.xelara.aladdin.magiclamp.Monitor;
import com.xelara.aladdin.magiclamp.model.WishModel;
import com.xelara.aladdin.unit.DbUnits;
import com.xelara.aladdin.unit.model.DbUnitListModel;
import com.xelara.aladdin.unit.model.DbUnitListModelParser;
import com.xelara.aladdin.unit.model.DbUnitModel;
import com.xelara.aladdin.unit.model.DbUnitModelParser;

public abstract class ModelFactory <

	DUM 	extends DbUnitModel				< DUM >,
	DUMP	extends DbUnitModelParser		< DUM >,
	DULM 	extends DbUnitListModel			< DUM, DULM >,
	DULMP	extends DbUnitListModelParser	< DUM, DULM, DUMP>,
	DU		extends DbUnits					< DUM, DUMP >,
	ML		extends MagicLamp				< DUM, DUMP >,
	GN		extends Genie					< DUM, DUMP, DULM, DULMP, DU >
> {

	public abstract DUM 	createModel		();
	public abstract DUMP 	createParser	();
	public abstract ML 		createMagicLamp	( Monitor monitor );
	
	public abstract GN		createGenie		( Path dbPath, WishModel wishModel, IndexOrigin indexes, Consumer < String > respConsumer );
}
