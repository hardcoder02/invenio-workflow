package invenio.op.operation.sim.ego.impl;

import invenio.data.InvenioVertex;

import java.util.HashMap;
import java.util.Map;

import qng.client.QueryException;
import qng.core.query.Context;
import qng.core.query.NotExistsException;

public abstract class AbstractEgoNetAligner implements EgoNetAligner {
	protected Context ctx;
	protected AlignmentCallback ac;
	
	public final static String PSEUDO_ALIGNER_NAME = "pseudoEgoNetAligner";
	public final static String CARTESIAN_ALIGNER_NAME = "cartesianEgoNetAligner";
	
	protected final static Map<String, Class<? extends EgoNetAligner>> aligners = new HashMap<String, Class<? extends EgoNetAligner>>();
	
	{
		aligners.put(PSEUDO_ALIGNER_NAME, PseudoEgoNetAligner.class);
		aligners.put(CARTESIAN_ALIGNER_NAME, CartesianProductEgoNetAligner.class);
	}
	
	public static EgoNetAligner createInstance(String alignerName) throws NotExistsException {
		// TODO: remove
		if (aligners.isEmpty()) {
			aligners.put(PSEUDO_ALIGNER_NAME, PseudoEgoNetAligner.class);
			aligners.put(CARTESIAN_ALIGNER_NAME, CartesianProductEgoNetAligner.class);
		}
		if (alignerName == null)
			return new DefaultEgoNetAligner();
		else if (aligners.containsKey( alignerName )) {
			try {
				Class<? extends EgoNetAligner> clazz = aligners.get( alignerName );
				return clazz.newInstance();
			}
			catch (InstantiationException ex) {
				throw new NotExistsException("Could not create aligner with name " + alignerName, ex);
			} catch (IllegalAccessException ex) {
				throw new NotExistsException("Could not create aligner with name " + alignerName, ex);
			}
		}
		else
			throw new NotExistsException("Cannot find EgoNetAligner with name " + alignerName);
	}
	
	@Override
	public void align(InvenioVertex n1, InvenioVertex n2,
			Context ctx, AlignmentCallback ac) throws QueryException, NullPointerException {
		
		if (ctx == null)
			throw new NullPointerException("Context may not be null");
		if (ac == null)
			throw new NullPointerException("AlignmentCallback may not be null");
		if (n1 == null || n2 == null)
			throw new NullPointerException("Ego-net centers may not be null");
		
		this.ctx = ctx;
		this.ac = ac;
		
		doAlign(n1, n2);
	}
	
	protected abstract void doAlign(InvenioVertex n1, InvenioVertex n2) throws QueryException;
}
