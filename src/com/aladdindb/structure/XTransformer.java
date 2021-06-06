package com.aladdindb.structure;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;

import com.aladdindb.structure.sn.SnPoint;
import com.aladdindb.util.Var;

public class XTransformer < UDM extends DataModel< UDM > >  extends Transformer < UDM > {

	
	private Class< UDM > udmClass;
	
	
	private enum XType { STRING, INT, DOUBLE, FLOAT, BYTE, BOOLEAN, LOCAL_DATE }
	
	
	public XTransformer( String key, Class< UDM > udmClass ) {
		super( key );
		this.udmClass = udmClass;
	}
	
	@Override
	public UDM newModel() {
		try {
			return this.udmClass.newInstance();
		} catch ( InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public UDM toModel( SnPoint src, UDM target ) {
		for( var field :target.getClass().getFields() ) {
			switch( field.getType().getSimpleName() ) {
				case "Var":
					this.getType( field.getGenericType(), ta -> {
						switch( ta ) {
							case STRING		: this.< String		> set 	( ta, field, src, target ) ;break;
							case INT 		: this.< Integer	> set	( ta, field, src, target ) ;break;
							case DOUBLE 	: this.< Double		> set	( ta, field, src, target ) ;break;
							case FLOAT 		: this.< Float		> set	( ta, field, src, target ) ;break;
							case BYTE 		: this.< Byte		> set	( ta, field, src, target ) ;break;
							case BOOLEAN 	: this.< Boolean	> set	( ta, field, src, target ) ;break;
							case LOCAL_DATE : this.< LocalDate	> set	( ta, field, src, target ) ;break;
						}
					});break;
					
				default :
					this.toModel( field, src, newModel -> {
						var targetField = this.getModel( field, target );
						targetField.fill( newModel );
					});
			}
		}
		return target;
	}

	@Override
	public SnPoint toNode( UDM src, SnPoint target) {
		for( var field :src.getClass().getFields() ) {
			switch( field.getType().getSimpleName() ) {
				case "Var":
					this.getType( field.getGenericType(), ta -> {
						switch( ta ) {
							case STRING		: this.< String		> set 	( ta, field, src, target ) ;break;
							case INT 		: this.< Integer	> set	( ta, field, src, target ) ;break;
							case DOUBLE 	: this.< Double		> set	( ta, field, src, target ) ;break;
							case FLOAT 		: this.< Float		> set	( ta, field, src, target ) ;break;
							case BYTE 		: this.< Byte		> set	( ta, field, src, target ) ;break;
							case BOOLEAN 	: this.< Boolean	> set	( ta, field, src, target ) ;break;
							case LOCAL_DATE : this.< LocalDate	> set	( ta, field, src, target ) ;break;
						}
					});break;
					
				default :
					this.toNode( field, src, target.children :: add );
			}
		}
		return target;
	}
	
	//**********************************************************
	//
	//**********************************************************
	
	private void toModel( Field field, SnPoint src, Consumer < DataModel > consumer ) {
		var rv = this.toModel ( field, src );
		if( rv != null ) consumer.accept( rv );
	}
	
	private DataModel toModel( Field field, SnPoint src ) {
		var type 		= field.getType(); 
		var transformer = newTransformer(field);
		var node 		= src.children.get( type.getSimpleName() );
		return node != null ? transformer.toModel( node ) : null;
	}

	//**********************************************************
	//
	//**********************************************************
	
	private void toNode( Field field, UDM src, Consumer< SnPoint > consumer ) {
		var rv = this.toNode(field, src);
		if( rv != null ) consumer.accept( rv );
	}
	
	private SnPoint toNode( Field field, UDM src ) {
		var transformer = newTransformer(field);
		var srcModel 	= getModel(field, src);
		return srcModel != null ? transformer.toNode( srcModel ) : null;
	}
	
	//**********************************************************
	//
	//**********************************************************
	
	private Transformer newTransformer( Field field ) {
		var type = field.getType();
		return new XTransformer( type.getSimpleName(), type );
	}
	
	//**********************************************************
	//
	//**********************************************************
	
	private <T> Var < T > getRealType(  Field field, UDM model ) {
		try {
			return (Var<T>)field.get( model );
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private <T> void set( XType xtype, Field field, UDM src, SnPoint target ) {
		var realVar = this.<T>getRealType( field, src ); 
		realVar.get( realValue -> {
			
			switch( xtype ) {
				case LOCAL_DATE:
					target.attributes.set( field.getName(), ( ( LocalDate ) realValue ).format( DateTimeFormatter.ISO_LOCAL_DATE ) );
					break;
					
				default :target.attributes.set( field.getName(), realValue.toString() );
			}
			
		});
	}
	
	private <T> void set( XType xtype, Field field, SnPoint src, UDM target ) {
		var realVar = this.<T>getRealType( field, target ); 
		if( field != null && src != null ) {
			String realValue = src.attributes.getValue( field.getName() );
			if( realVar != null) {
				switch( xtype ) {
					case STRING 	: ((Var< String		>)realVar).set( realValue ) ;break;
					case INT 		: ((Var< Integer	>)realVar).set( Integer		.parseInt		( realValue ) ) ;break;
					case DOUBLE 	: ((Var< Double		>)realVar).set( Double		.parseDouble	( realValue ) ) ;break;
					case FLOAT 		: ((Var< Float		>)realVar).set( Float		.parseFloat		( realValue ) ) ;break;
					case BYTE 		: ((Var< Byte		>)realVar).set( Byte		.parseByte		( realValue ) ) ;break;
					case BOOLEAN 	: ((Var< Boolean	>)realVar).set( Boolean		.parseBoolean	( realValue ) ) ;break;
					case LOCAL_DATE : ((Var< LocalDate	>)realVar).set( LocalDate	.parse			( realValue, DateTimeFormatter.ISO_LOCAL_DATE ) ) ;break;
				}
			}
		}
	}
	
	//**********************************************************
	//
	//**********************************************************

	private DataModel getModel( Field field, UDM model ) {
		try {
			return (DataModel)field.get( model );
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	//**********************************************************
	//
	//**********************************************************

	private void getType( Type type, Consumer< XType  > consumer ) {
		
	    if ( type instanceof ParameterizedType ) {
	    	
	        ParameterizedType pt = (ParameterizedType) type;
	        
//	        String rt = pt.getRawType().getTypeName();
	        var types = pt.getActualTypeArguments();
	        
	        if( types.length > 0 ) {
	        	
		        String ta = types[0].getTypeName();
		        
    	        if( ta != null && !ta.isEmpty()  ) {
    	        	
    	        	var xtype = switch( ta ) {
    	        	
    	        		case "java.lang.String" 	-> XType.STRING; 
    	        		case "java.lang.Integer" 	-> XType.INT;
    	        		case "java.lang.Double" 	-> XType.DOUBLE;
    	        		case "java.lang.Float" 		-> XType.FLOAT;
    	        		case "java.lang.Byte" 		-> XType.BYTE;
    	        		case "java.lang.Boolean" 	-> XType.BOOLEAN;
    	        		case "java.time.LocalDate" 	-> XType.LOCAL_DATE;
    	        		
    	        		default -> null;
    	        	};
    	        	
    	        	if( xtype != null) consumer.accept( xtype );
    	        }
	        }
	    }
	}
	
	//**********************************************************
	//
	//**********************************************************

	
	
}