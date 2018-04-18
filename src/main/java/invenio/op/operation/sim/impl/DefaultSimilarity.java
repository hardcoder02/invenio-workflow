package invenio.op.operation.sim.impl;

import invenio.data.InvenioEdge;
import invenio.data.InvenioGraph;
import invenio.data.InvenioVertex;
import invenio.op.operation.impl.OperationConstants;
import invenio.op.operation.impl.OperationRegistryFactory;
import invenio.op.operation.impl.OperatorUtils;
import invenio.op.operation.impl.Pair;
import invenio.operator.data.Feature;

import java.util.ArrayList;
import java.util.List;

import qng.client.QueryException;
import qng.core.compiler.OperationRegistry;
import qng.core.executor.OperationManager;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;

// TODO: re-consider strategy for bypassing complete compilation and compiling only one operation on the fly
public class DefaultSimilarity extends AbstractOperation implements
		CompiledOperation {
	
	private String delegateSimilarityName = null;
	private List<Object> delegateArgs = new ArrayList<Object>();
	
	public final static String OP_NAME = "sim";

	public static final Class[] EXPECTED_TYPES = { };
	
	public DefaultSimilarity() {
		super(null, OP_NAME);
	}
	
	public DefaultSimilarity(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}	

	@Override
	public boolean hasExactNumberOfArguments() {
		return false;
	}

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		
		cleanup();
		parseArguments(args);
		
		OperationRegistry reg = OperationRegistryFactory.getInstance();

		CompiledOperation delegateSim = reg.createCompiledOperation( delegateSimilarityName );
		
		OperationManager<CompiledOperation> exec = (OperationManager<CompiledOperation>)
				getContext().getVariable(OperationConstants.VAR_OPERATION_MANAGER);
		
		if (exec == null)
			throw new QueryException("Exception in operator " + getName() + ": cannot find OperationManager in Context");
		
		Object[] argsArray = new Object[ delegateArgs.size() ];
		delegateArgs.toArray( argsArray );
		
		return exec.invokeOperation(delegateSim, argsArray, getContext());
	}

	private void parseArguments(Object[] args) throws QueryException {
		if ( args.length < 2 )
			throw new QueryException( "Exception in operator " + getName() + 
					": at least two arguments are required" );
		
		if ( args.length > 3 )
			throw new QueryException( "Exception in operator " + getName() + 
					": at most three arguments are allowed" );
		
		delegateArgs.add( args[0] );
		delegateArgs.add( args[1] );
		
		if ( args.length >= 3 ) {
			if ( args[2] == null || !(args[2] instanceof SimilarityConfig) )
				throw new QueryException( "Exception in operator " + getName() + 
					": expected argument type is " + SimilarityConfig.class.getName() );
			else {
				SimilarityConfig cfg = (SimilarityConfig) args[2];
				processConfig( cfg );
			}
		}
		
		if ( delegateSimilarityName == null ) {
			delegateSimilarityName = OperatorUtils.retrieveContextParam(
					getContext(), OperationConstants.SIMILARITY_NAME, null);
		}

		if ( delegateSimilarityName == null ) {
			delegateSimilarityName = getSimName( args[0], args[1] );
		}
	}
	
	
	private String getSimName(Object o1, Object o2) throws QueryException {
		String name1 = getSimName( o1 );
		String name2 = getSimName( o2 );
		
		if ( name1 == null ) {
			if ( name2 == null )
				throw new QueryException( "Exception in operator " + getName() + 
						": default similarity cannot be determined for two null arguments" );
			else
				return name2;
		}
		else {
			if ( name2 == null )
				return name1;
			else {
				if ( name1.equals( name2) )
					return name1;
				else
					throw new QueryException( "Exception in operator " + getName() + 
						": default similarity cannot be determined for different argument types" );
			}
		}
	}
	
	
	private String getSimName( Object o) throws QueryException {
		if ( o == null)
			return null;
		else if ( o instanceof Feature ) {
			return SimilarityConstants.DEFAULT_ATTR_SIM;
		}
		else if ( o instanceof InvenioEdge ) {
			return SimilarityConstants.DEFAULT_EDGE_SIM;
		}
		else if ( o instanceof InvenioVertex ) {
			return SimilarityConstants.DEFAULT_NODE_SIM;
		}
		else if ( o instanceof InvenioGraph ) {
			return SimilarityConstants.DEFAULT_GRAPH_SIM;
		}

		throw new QueryException( "Exception in operator " + getName() + 
				": default similarity not found for argument type " + o.getClass().getName() );
	}

	private void processConfig(SimilarityConfig cfg) throws QueryException {
		for (Pair<String, Object> p : cfg ) {
			if ( p.first.equals( OperationConstants.SIMILARITY_NAME) ) {
				if ( p.second instanceof String )
					delegateSimilarityName = (String) p.second;
				else
					throw new QueryException( "Exception in operator " + getName() + 
						": String argument expected under name " + OperationConstants.SIMILARITY_NAME );
			}
			else
				delegateArgs.add( p.second );
		}
	}

	private void cleanup() {
		delegateSimilarityName = null;
		delegateArgs.clear();
	}

}
