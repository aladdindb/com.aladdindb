package com.xelara.aladdin.core.units.models;

import com.xelara.aladdin.core.models.text.LabelModel;
import com.xelara.core.util.Var;
import com.xelara.structure.DataModel;

/**
 *
 * @author Macit Kandemir
 */
public class Meta implements DataModel< Meta > { 
    
	
	public final LabelModel label 		= new LabelModel();
	public final TimeStamp 	timeStamp	= new TimeStamp();

	
	@Override
	public void fill( Meta  model ) {
		this.label		.fill( model.label );
		this.timeStamp	.fill( model.timeStamp );
	}

    
    
}
