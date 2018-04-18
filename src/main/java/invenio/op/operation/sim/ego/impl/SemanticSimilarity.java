package invenio.op.operation.sim.ego.impl;

import invenio.data.InvenioVertex;
import invenio.op.operation.impl.MPVOperation;
import invenio.op.operation.impl.OperationConstants;
import invenio.op.operation.impl.OperationRegistryFactory;
import invenio.op.operation.impl.OperatorUtils;
import invenio.op.operation.impl.Triple;
import invenio.op.operation.sim.impl.DefaultAttribSimilarity;
import invenio.op.operation.sim.impl.MPVAttributeSimilarity;
import invenio.operator.data.CategoricalFeature;
import invenio.operator.data.ComparableFeatureContainer;
import invenio.operator.data.MissingElementException;
import invenio.operator.data.Schema;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Map.Entry;

import qng.client.QueryException;
import qng.core.compiler.OperationRegistry;
import qng.core.executor.OperationManager;
import qng.core.query.AbstractOperation;
import qng.core.query.CompiledOperation;
import qng.core.query.Context;

public class SemanticSimilarity extends AbstractOperation implements
		CompiledOperation {
	
	public final static String OP_NAME = "semanticSimilarity";
	
	public final static boolean DEFAULT_ALIGN = true;
	public final static boolean DEFAULT_SIM_OR = true;
	public final static String DEFAULT_SIM_MEASURE = DefaultAttribSimilarity.OP_NAME;
	
	private boolean align;
	private String simAttr;
	private boolean simOr;
	private String simMeasureName;

	public static final Class[] EXPECTED_TYPES = {
		InvenioVertex.class,
		InvenioVertex.class
	};
	
	public SemanticSimilarity() {
		super(null, OP_NAME);
	}
	
	public SemanticSimilarity(String id) {
		super(id, OP_NAME);
	}
	
	@Override
	public Class[] getExpectedTypes() {
		return EXPECTED_TYPES;
	}

	@Override
	public Object doExecute(Object[] args) throws QueryException {
		InvenioVertex n1 = (InvenioVertex) args[0];
		InvenioVertex n2 = (InvenioVertex) args[1];
		
		if (n1 == null || n2 == null)
			return null;
		
		cleanupParams();
		retrieveParams();
		validateParams();

		if (align) {
			return processAligned(n1, n2);
		}
		else {
			// TODO: fix
			simAttr = "cat_0";
			simMeasureName = MPVAttributeSimilarity.OP_NAME;
			if ( simMeasureName.equals(MPVAttributeSimilarity.OP_NAME) )
				return processUnalignedMPV(n1, n2);
			else
				return processUnaligned(n1, n2);

		}
	}
	
	protected void cleanupParams() {
		align = DEFAULT_ALIGN;
		simAttr = null;
		simOr = DEFAULT_SIM_OR;
		simMeasureName = DEFAULT_SIM_MEASURE;
	}
	
	protected void retrieveParams() throws QueryException {
		// TODO: refactor
		align = OperatorUtils.retrieveContextParamAsBoolean(
				getContext(), OperationConstants.EGOSIM_ALIGN, DEFAULT_ALIGN );
		
		simAttr = OperatorUtils.retrieveContextParam(
				getContext(), OperationConstants.EGOSIM_ATTR_NAME, null);
		
		simOr = OperatorUtils.retrieveContextParamAsBoolean(
				getContext(), OperationConstants.EGOSIM_OR, DEFAULT_SIM_OR );
		
		simMeasureName = OperatorUtils.retrieveContextParam(
				getContext(), OperationConstants.EGOSIM_SIM_MEASURE, DEFAULT_SIM_MEASURE);
	}
	
	protected void validateParams() throws QueryException {
		if ( !align && (simAttr == null) )
			throw new QueryException("Exception in operator " + getName() +
					": unaligned similarity with multiple attributes is not supported.");
	}
	
	private Object processAligned( InvenioVertex n1, InvenioVertex n2 ) throws QueryException {
		EgoNetAligner al = AbstractEgoNetAligner.createInstance(null);
		EgoSemanticAC ac = new EgoSemanticAC( );
		al.align(n1, n2, getContext(), ac );
		return ac.getResult();
	}
	
	// TODO: fix
	private Object processUnalignedMPV( InvenioVertex n1, InvenioVertex n2 ) throws QueryException {
		EgoNetAligner al = AbstractEgoNetAligner.createInstance( AbstractEgoNetAligner.PSEUDO_ALIGNER_NAME );
		UnalignedMpvAC ac = new UnalignedMpvAC();
		al.align(n1, n2, getContext(), ac );
		return ac.getResult();
	}
	
	private Object processUnaligned( InvenioVertex n1, InvenioVertex n2 ) throws QueryException {
		EgoNetAligner al = AbstractEgoNetAligner.createInstance( AbstractEgoNetAligner.CARTESIAN_ALIGNER_NAME );
		UnalignedEgoSemanticAC ac = new UnalignedEgoSemanticAC(n1.degree(), n2.degree());
		al.align(n1, n2, getContext(), ac );
		return ac.getResult();
	}

	private class EgoSemanticAC extends AlignmentCallbackAdapter {
		private final OperationManager<CompiledOperation> exec;
		private final Context ctx;
		
		private int num = 0;
		private double sum = 0;
		
		private EgoSemanticAC() throws QueryException {
			exec = (OperationManager<CompiledOperation>) getContext().getVariable(OperationConstants.VAR_OPERATION_MANAGER);
			if (exec == null)
				throw new QueryException("Exception in operator " + getName() + ": cannot find OperationManager in Context");
			ctx = getContext();
		}
		
		@Override
		public void startAlignVertex(InvenioVertex v1, InvenioVertex v2) throws QueryException {
			if (v1 == null || v2 == null)
				return;
			
			sum+= getSim( v1, v2 );
			num++;
		}
		
		private double getSim(InvenioVertex v1, InvenioVertex v2) throws QueryException {
			if (v1 == null || v2 == null)
				return 0;
			
			ComparableFeatureContainer c1 = OperatorUtils.getFeatureContainer(v1);
			ComparableFeatureContainer c2 = OperatorUtils.getFeatureContainer(v2);
			
			if (c1 == null || c2 == null)
				return 0;
			
			Schema s1 = c1.getSchema();
			Schema s2 = c2.getSchema();
			
			if ( !s1.isEquivalentTo( s2 ) )
				throw new QueryException("Exception in operator " + getName() + 
						": mismatched schemas ");
			
			// single Attribute similarity
			if ( simAttr != null ) {
				CategoricalFeature f1 = OperatorUtils.getAsCategoricalFeature( v1, simAttr, true );
				CategoricalFeature f2 = OperatorUtils.getAsCategoricalFeature( v2, simAttr, true );
				
				return getSim( f1, f2 );
			}
			else {
				boolean allOne = true;
				double curScore = 0;
				int numCategorical = 0;
				for ( int i = 0; i < s1.size(); i++ ) {
					if ( s1.canGetAsCategorical( i ) ) {
						try {
							// TODO: refactor to OperatorUtils
							CategoricalFeature f1 = (CategoricalFeature) c1.getFeature( i );
							CategoricalFeature f2 = (CategoricalFeature) c2.getFeature( i );
							
							double sim = getSim(f1, f2);
							if (sim != 1)
								allOne = false;
							curScore += sim;
							numCategorical++;
						}
						catch (MissingElementException ex) {
							// should not happen
							throw new QueryException( "Exception in operator " + getName() + 
							": " + ex.getMessage(),  ex);
						}
					}
				}
				if (simOr)
					return (numCategorical == 0) ? 1 : curScore / numCategorical;
				else
					return (allOne) ? 1 : 0;
			}
		}

		private double getSim(CategoricalFeature f1, CategoricalFeature f2) throws QueryException {
			if (f1 == null || f2 == null)
				return 0;
			
			OperationRegistry reg = OperationRegistryFactory.getInstance();
			CompiledOperation simOp = reg.createCompiledOperation( simMeasureName );
			
			Object[] argsArray = { f1, f2 };
			
			Object res = exec.invokeOperation(simOp, argsArray, ctx);
			if ( !(res instanceof Double) )
				throw new QueryException("Exception in operator " + getName() +
						": similarity measure operator must return double result");
			return ((Double)res);
		}
		
		public double getResult() {
			return (num == 0) ? 1 : sum / num;
		}
	}
	
	
	private class UnalignedMpvAC extends AlignmentCallbackAdapter {
		private final OperationManager<CompiledOperation> exec;
		private final Context ctx;
		
		private Counter ego1MPV = new Counter();
		private Counter ego2MPV = new Counter();
		private int eg1 = 0, eg2 = 0;
		
		private UnalignedMpvAC() throws QueryException {
			exec = (OperationManager<CompiledOperation>) getContext().getVariable(OperationConstants.VAR_OPERATION_MANAGER);
			if (exec == null)
				throw new QueryException("Exception in operator " + getName() + ": cannot find OperationManager in Context");
			ctx = getContext();
		}
		
		@Override
		public void startAlignVertex(InvenioVertex v1, InvenioVertex v2) throws QueryException {
			if ( v1 != null ) {
				eg1++;
				getSim( v1, ego1MPV );
			}
			if ( v2 != null ) {
				eg2++;
				getSim( v2, ego2MPV );
			}
		}
		
		private void getSim(InvenioVertex v, Counter cnt) throws QueryException {
			
			ComparableFeatureContainer c = OperatorUtils.getFeatureContainer(v);
			
			if (c == null)
				return;
			
			// single Attribute similarity
			if ( simAttr != null ) {
				CategoricalFeature f = OperatorUtils.getAsCategoricalFeature( v, simAttr, true );
				
				OperationRegistry reg = OperationRegistryFactory.getInstance();
				CompiledOperation simOp = reg.createCompiledOperation( MPVOperation.OP_NAME );
				
				Object[] argsArray = { f };
				
				Object res = exec.invokeOperation(simOp, argsArray, ctx);
				if ( !(res instanceof List || ((List)res).isEmpty() || !(((List)res).get(0) instanceof String) ) )
					throw new QueryException("Exception in operator " + getName() +
							": similarity measure operator must return List of Strings result");
				
				cnt.increment( (String)((List)res).get(0) );
			}
				
		}

		public double getResult() {
			Set<Entry<String, Integer>> m1 = ego1MPV.getIterator();
			Map<String, Integer> map2 = ego2MPV.getMap();
			
			int sum = 0;
			for ( Entry<String, Integer> e : m1 ) {
				int c1 = e.getValue();
				int c2 = ( map2.containsKey( e.getKey() ) ) ? map2.get( e.getKey() ) : 0;
				
				sum += Math.min(c1, c2);
			}
			
			return (double)sum / (double) Math.max(eg1, eg2);
		}
	}
	
	private static class Counter {
		private final Map<String, Integer> count = new HashMap<String, Integer>();
		
		public void clear() {
			count.clear();
		}
		
		public void increment(String key) {
			if ( count.containsKey(key) )
				count.put(key, count.get(key) + 1);
			else
				count.put(key, 1);
		}
		
		Set<Entry<String, Integer>> getIterator() {
			return count.entrySet();
		}
		
		Map<String, Integer> getMap() {
			return count;
		}
	}
	
	
	private class UnalignedEgoSemanticAC extends AlignmentCallbackAdapter {
		private final OperationManager<CompiledOperation> exec;
		private final Context ctx;
		private final UnalignedSimilarityPriorityQueue<Object> queue;
		
		private final int DEFAULT_CAPACITY = 15;
		
		private UnalignedEgoSemanticAC() throws QueryException {
			exec = (OperationManager<CompiledOperation>) getContext().getVariable(OperationConstants.VAR_OPERATION_MANAGER);
			if (exec == null)
				throw new QueryException("Exception in operator " + getName() + ": cannot find OperationManager in Context");
			ctx = getContext();
			
			queue = new UnalignedSimilarityPriorityQueue<Object>(DEFAULT_CAPACITY, DEFAULT_CAPACITY);
		}
		
		private UnalignedEgoSemanticAC(int capacity1, int capacity2) throws QueryException {
			exec = (OperationManager<CompiledOperation>) getContext().getVariable(OperationConstants.VAR_OPERATION_MANAGER);
			if (exec == null)
				throw new QueryException("Exception in operator " + getName() + ": cannot find OperationManager in Context");
			ctx = getContext();
			
			queue = new UnalignedSimilarityPriorityQueue<Object>(capacity1, capacity2);
		}
		
		@Override
		public void startAlignVertex(InvenioVertex v1, InvenioVertex v2) throws QueryException {
			// should not happen
			if (v1 == null || v2 == null)
				return;
			
			double sim = getSim( v1, v2 );
			queue.offer(v1.getKey(), v2.getKey(), sim);
		}
		
		// TODO: almost same as in EgoSemanticAC - refactor to a superclass or transfer to DefaultElementSimilarity
		private double getSim(InvenioVertex v1, InvenioVertex v2) throws QueryException {
			if (v1 == null || v2 == null)
				return 0;
			
			// single Attribute similarity
			if ( simAttr != null ) {
				CategoricalFeature f1 = OperatorUtils.getAsCategoricalFeature( v1, simAttr, true );
				CategoricalFeature f2 = OperatorUtils.getAsCategoricalFeature( v1, simAttr, true );
				
				return getSim( f1, f2 );
			}
			else {
				throw new QueryException( "Exception in operator " + getName() + 
					": only single attribute ego-network similarity is supported");
			}
		}

		private double getSim(CategoricalFeature f1, CategoricalFeature f2) throws QueryException {
			if (f1 == null || f2 == null)
				return 0;
			
			OperationRegistry reg = OperationRegistryFactory.getInstance();
			CompiledOperation simOp = reg.createCompiledOperation( simMeasureName );
			
			Object[] argsArray = { f1, f2 };
			
			Object res = exec.invokeOperation(simOp, argsArray, ctx);
			if ( !(res instanceof Double) )
				throw new QueryException("Exception in operator " + getName() +
						": similarity measure operator must return double result");
			return ((Double)res);
		}
		
		public double getResult() {
			double sum = 0;
			int total = 0;
			
			Triple<Object, Object, Double> t = null;
			while ( (t=queue.poll()) != null ) {
				total++;
				sum += t.third;
			}
			
			return (total == 0) ? 1 : sum / total;
		}
	}
	
	/**
	 * At this point no need to implement the complete java.util.Queue<E> interface
	 * @author ddimitrov
	 *
	 * @param <E>
	 */
	private static class UnalignedSimilarityPriorityQueue<E>  {
		private PriorityQueue<Triple<E, E, Double>> queue;
		private Set<E> used1, used2;
		
		private UnalignedSimilarityPriorityQueue(int initialCapacity1, int initialCapacity2) {
			int initCapacity = initialCapacity1 * initialCapacity2;
			if (initCapacity == 0)
				initCapacity = Math.max(initialCapacity1, initialCapacity2);
			queue = new PriorityQueue<Triple<E,E,Double>>(initCapacity, new UnalignedSimComparator());
			used1 = new HashSet<E>(initialCapacity1);
			used2 = new HashSet<E>(initialCapacity2);
		}
		
		private boolean offer(E e1, E e2, double sim) {
			if (e1 == null || e2 == null)
				throw new NullPointerException("Similarity cannot be assigned to a null element");
			
			Triple<E, E, Double> t = new Triple<E, E, Double>(e1, e2, sim);
			return queue.offer(t);	// returns true
		}
		
		private Triple<E, E, Double> poll() {
			while (true) {
				Triple<E, E, Double> t = queue.poll();
				if ( t == null)
					return null;

				if ( used1.contains( t.first ) || used2.contains( t.second ) )
					continue;
				
				used1.add( t.first );
				used2.add( t.second );
				return t;
			}
		}
		
		private static class UnalignedSimComparator implements Comparator<Triple<? extends Object, ? extends Object, Double>> {

			@Override
			public int compare(
					Triple<? extends Object, ? extends Object, Double> o1,
					Triple<? extends Object, ? extends Object, Double> o2) {
				
				if (o1.third < o2.third)
					return -1;
				else if (o1.third == o2.third)
					return 0;
				else
					return 1;
			}
			
		}
	}
}
