package com.aladdindb;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.function.Consumer;
import java.util.function.Function;

import com.aladdindb.store.models.Unit;
import com.aladdindb.structure.DataModel;
import com.aladdindb.util.Var;

public class UnitAnalyzer< UDM extends DataModel< UDM > > {
	
	
	public enum VarType { STRING, INT, DOUBLE, FLOAT, BYTE, BOOLEAN, LOCAL_DATE, ZONED_DATE_TIME }
	
	
	public final Class < UDM > 	udmClass;
	public final UDM 			emptyDataObject;
	public final Unit< UDM > 	emptyUnit;
	
	public UnitAnalyzer( Class < UDM > udmClass ) {
		this.udmClass 			= udmClass;
		this.emptyDataObject 	= this.newDataObject();
		this.emptyUnit = new Unit<>(emptyDataObject );
	}
	
	public Function< Unit< UDM >, Var<?> > getFunction( String fieldId, Function< Unit< UDM >, Var<?> >... functions   ) {
		for( var function : functions ) {
			if( varEqualsFieldId( fieldId, function ) ) return function;
		}
		return null;
	}
	
	public boolean varEqualsFieldId( String fieldId, Function< Unit< UDM >, Var<?> > function   ) {
		var varObject = this.getVarObject( function );
		String key = varObject.key();
		return varObject != null ? key.equals( fieldId ) : false; 
	}

	//----------------------------------

	public VarType getVarsGenericTypeAsVarType( Var<?> varObject ) {
		var field = this.varToField( varObject );
		return field != null ? this.getType( field.getGenericType() ) : null;
	}
	
	public VarType getVarsGenericTypeAsVarType( Function< Unit< UDM >, Var< ? > > function ) {
		var field = this.varToField( function );
		return field != null ? this.getType( field.getGenericType() ) : null;
	}
	
	//----------------------------------
	
	public Field varToField( Function< Unit< UDM >, Var< ? > > function ) {
		var varObject = this.getVarObject( function );
		if( varObject != null ) {
			var field = varToField( varObject );
			if( field != null )return field;
		}
		return null;
	}

	//----------------------------------

	public Field varToField( Var<?> varObject ) {
		var parent = varObject.getParent();
		if( parent != null ) {
	    	Class<?> clazz = parent.getClass();
	    	for( var field : clazz.getFields()) {
	    		try {
	    			Object o = field.get( parent );
	    	    	if( o == varObject ) return field;
	    		} catch (IllegalArgumentException | IllegalAccessException e) {
	    			e.printStackTrace();
	    		}
	    	}
		}
		return null;
	}
	
	public VarType getType( Type type ) {

		if ( type instanceof ParameterizedType ) {
	    	
	        ParameterizedType pt = (ParameterizedType) type;
	        
	        var types = pt.getActualTypeArguments();
	        
	        if( types.length > 0 ) {
	        	
		        String ta = types[0].getTypeName();
		        
    	        if( ta != null && !ta.isEmpty()  ) {
    	        	
    	        	return switch( ta ) { 
    	        	
    	        		case "java.lang.String" 		-> VarType.STRING; 
    	        		case "java.lang.Integer" 		-> VarType.INT;
    	        		case "java.lang.Double" 		-> VarType.DOUBLE;
    	        		case "java.lang.Float" 			-> VarType.FLOAT;
    	        		case "java.lang.Byte" 			-> VarType.BYTE;
    	        		case "java.lang.Boolean" 		-> VarType.BOOLEAN;
    	        		case "java.time.LocalDate" 		-> VarType.LOCAL_DATE;
    	        		
    	        		case "java.time.ZonedDateTime" 	-> VarType.ZONED_DATE_TIME;
    	        		
    	        		default -> null;
    	        	};
    	        	
    	        }
	        }
	    }
	    return null;
	}
	
	//**********************************************************
	//
	//**********************************************************
	
	public Var< ? >  getVarObject( Function< Unit< UDM >, Var< ? > > function ) {
		return function.apply( this.emptyUnit );
	}

	public UDM newDataObject() {
		try {
			return this.udmClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void getGenericParamType( Type type, Consumer< Class<?>  > consumer ) {
		if ( type instanceof ParameterizedType ) {
	        ParameterizedType pt = (ParameterizedType) type;
	        var types = pt.getActualTypeArguments();
	        if( types.length > 0 ) {
				try {
		        	consumer.accept(Class.forName( types[0].getTypeName() ));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
	        }
	    }
	}
	
	
}
