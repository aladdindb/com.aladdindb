package com.xelara.aladdin.unit.model;

import com.xelara.aladdin.model.text.LabelModel;
import com.xelara.core.util.Var;

/**
 *
 * @author Macit Kandemir
 */
public class MetaModel implements DataModel< MetaModel > { 
    
	
	public final TimeStampModel 	timeStamp	= new TimeStampModel();
	public final LabelModel 		label 		= new LabelModel();

	
	@Override
	public void fill( MetaModel  model ) {
		timeStamp.fill( model.timeStamp );
		this.label.fill( model.label );
	}

    
    
}
