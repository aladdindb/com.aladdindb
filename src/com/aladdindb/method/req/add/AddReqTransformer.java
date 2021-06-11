package com.aladdindb.method.req.add;

import com.aladdindb.method.Method;
import com.aladdindb.method.req.ReqTransformer;
import com.aladdindb.structure.DataModel;
import com.aladdindb.structure.XTransformer;
import com.aladdindb.structure.sn.SnPoint;

/**
 *
 * @author Macit Kandemir
 * @param <DUM>
 */
public final class AddReqTransformer < UDM extends DataModel< UDM > > extends ReqTransformer< AddReq< UDM > >  {
	

	public final Class< UDM > udmClass;
    
    
    //****************************************************************
    //
    //****************************************************************

    public AddReqTransformer( Class< UDM > udmClass ) {
		super( Method.add.store() );
		this.udmClass = udmClass;
	}
    
    //****************************************************************
    //
    //****************************************************************

    @Override
	public AddReq< UDM > newModel() {
		return new AddReq< UDM >();
	}
	
    //****************************************************************
    //
    //****************************************************************
    
    @Override
    public AddReq< UDM >  toModel( SnPoint src, AddReq< UDM > target ) {
    	super.toModel( src, target );
        
//        dataTransformer.toModelFromParent( src ,target.unitData );
        
        if( this.udmClass != null ) {
            var dataNode = src.children.get( this.udmClass.getSimpleName() );
    		var t = new XTransformer< UDM >( this.udmClass.getSimpleName(), this.udmClass );
    		t.toModel(dataNode, dataModel-> {
    			target.unitData.set(dataModel);
    		});
        }
        
        return target;
    }
    
    @Override
    public SnPoint toNode( AddReq< UDM > src, SnPoint target ) {
    	super.toNode( src, target );
        
//        dataTransformer.toParentNode( src.unitData ,target );

        if( this.udmClass != null ) {
  		var t = new XTransformer< UDM >( this.udmClass.getSimpleName(), this.udmClass );
  		src.unitData.get( dataModel -> {
      		t.toNode( dataModel, dataNode -> {
      			target.children.add(dataNode);
      		});
  		});
      }
        
        return target;
    }
    
    //****************************************************************
    //
    //****************************************************************
    
}
