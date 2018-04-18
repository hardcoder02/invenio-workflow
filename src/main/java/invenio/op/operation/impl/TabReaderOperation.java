package invenio.op.operation.impl;

import invenio.data.InvenioGraph;
import invenio.operator.data.DataFormatException;

import java.io.IOException;
import java.util.Map;

import qng.client.QueryException;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

// TODO: process attributes
public class TabReaderOperation extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "tabReader";
	
	public static final Class[] EXPECTED_TYPES = {
		String.class,
		String.class
	};
	
	public TabReaderOperation() {
		super(null, OP_NAME);
	}
	
	public TabReaderOperation(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}
	

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		String nodeFile = (String) args[0];
    	String edgeFile = (String) args[1];
    	
//    	try {
    		// TODO: fix, uncomment the following and remove the context-based tab reader
			//InvenioGraph g = (new invenio.operator.readers.tabio.TabDelimIO()).readGraph(null, nodeFile, edgeFile);
    		Map<String, InvenioGraph> graphMap = 
    				(Map<String, InvenioGraph>) getContext().getVariable( OperationConstants.VAR_GRAPH_STORE );
    		
    		if (graphMap == null || nodeFile == null)
    			return null;
    		InvenioGraph g = graphMap.get( nodeFile );
			return g;
//		} catch (IOException e) {
//			throw new QueryException("Exception in operation " + getName() + ": " + e.getMessage(), e);
//		} catch (DataFormatException e) {
//			throw new QueryException("Exception in operation " + getName() + ": " + e.getMessage(), e);
//		}
		
	}

}
