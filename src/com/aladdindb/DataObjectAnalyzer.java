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

public class DataObjectAnalyzer< UDM extends DataModel< UDM > > {
	
	
	public enum VarType { STRING, INT, DOUBLE, FLOAT, BYTE, BOOLEAN, LOCAL_DATE, ZONED_DATE_TIME }
	
	
	private final Class < UDM > udmClass;
	private final UDM 			emptyDataObject;

	public DataObjectAnalyzer( Class < UDM > udmClass ) {
		this.udmClass 			= udmClass;
		this.emptyDataObject 	= this.newDataObject();
	}
	

	public VarType getVarType( Function< Unit< UDM >, Var< ? > > function ) {
		var varObject = this.getVar( function );
		if( varObject != null ) {
			var field = getFieldType( varObject );
			return field != null ? this.getType( field.getGenericType() ) : null;
		}
		return null;
	}
	
	public Field getFieldType( Var<?> varObject ) {
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
	        
//	        String rt = pt.getRawType().getTypeName();
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
	
	
	
	public Var< ? >  getVar( Function< Unit< UDM >, Var< ? > > function ) {
		return function.apply( new Unit<>( this.emptyDataObject ) );
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

	private void getGenericParamType( Type type, Consumer< Class<?>  > consumer ) {
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
