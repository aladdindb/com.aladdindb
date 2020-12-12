package com.xelara.aladdin.model.text;

import com.xelara.aladdin.unit.model.UnitModel;
import com.xelara.core.Var;

/**
 *
 * @author Macit Kandemir
 */
public class TextModel extends Var< String > implements UnitModel < TextModel > {
    
    public TextModel() {
    }
    
    public TextModel( String text ) {
    	this.setValue(text);
    }
    
    public void fill( TextModel unit ) {
    	super.fill( unit );
//    	unit.getValue( this :: setValue );
    }
    
}
