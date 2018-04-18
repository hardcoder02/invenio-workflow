package invenio.op.operation.impl;

import invenio.op.operation.aggregate.impl.AverageOperation;
import invenio.op.operation.aggregate.impl.CountOperation;
import invenio.op.operation.aggregate.impl.GroupByOperation;
import invenio.op.operation.aggregate.impl.HavingOperation;
import invenio.op.operation.aggregate.impl.MaxOperation;
import invenio.op.operation.aggregate.impl.MergeOperation;
import invenio.op.operation.aggregate.impl.MinOperation;
import invenio.op.operation.aggregate.impl.SplitOperation;
import invenio.op.operation.aggregate.impl.SumOperation;
import invenio.op.operation.directed.AdjacentVerticesUndirectedOperation;
import invenio.op.operation.directed.ChildrenOperation;
import invenio.op.operation.directed.CountFeatureOperation;
import invenio.op.operation.directed.EgoNetUndirectedOperation;
import invenio.op.operation.directed.HasChildrenOperation;
import invenio.op.operation.directed.HasParentsOperation;
import invenio.op.operation.directed.IsEdgeDirectedOperation;
import invenio.op.operation.directed.ParentsOperation;
import invenio.op.operation.directed.RootsOperation;
import invenio.op.operation.directed.SiblingsOperation;
import invenio.op.operation.directed.SwitchCountNegativeOperation;
import invenio.op.operation.directed.SwitchCountOperation;
import invenio.op.operation.directed.SwitchCountPositiveOperation;
import invenio.op.operation.directed.TreeDepthOperation;
import invenio.op.operation.directed.RootsOperation;
import invenio.op.operation.directed.TreeOperation;
import invenio.op.operation.directed.TreeSizeOperation;
import invenio.op.operation.sim.ego.impl.EgoStructuralComparisonCountSimilarity;
import invenio.op.operation.sim.ego.impl.EgoStructuralProbTopologicalSimilarity;
import invenio.op.operation.sim.ego.impl.EgoStructuralTopologySimilarity;
import invenio.op.operation.sim.ego.impl.SemanticSimilarity;
import invenio.op.operation.sim.impl.ADRatioAttribSimilarity;
import invenio.op.operation.sim.impl.DefaultAttribSimilarity;
import invenio.op.operation.sim.impl.DefaultElementSimilarity;
import invenio.op.operation.sim.impl.DefaultSimilarity;
import invenio.op.operation.sim.impl.HistogramIntersectionAttribSimilarity;
import invenio.op.operation.sim.impl.KLDivergenceAttribSimilarity;
import invenio.op.operation.sim.impl.MFDistanceAttribSimilarity;
import invenio.op.operation.sim.impl.MPVAttributeSimilarity;
import invenio.op.operation.sim.impl.ShannonEntropyRatioAttribSimilarity;
import qng.core.compiler.OperationRegistry;
import qng.core.query.DuplicateException;

// TODO: automatic discovery
public class OperationRegistryFactory {
	private static OperationRegistry reg;
	
	public static OperationRegistry getInstance() throws DuplicateException {
		if (reg == null)
			setupRegistry();
		
		return reg;
	}

	private static void setupRegistry() throws DuplicateException {
		reg = new OperationRegistry();
		
		// Arithmetic
		reg.registerCompiledOperation(PlusOperation.OP_NAME, PlusOperation.class);
		reg.registerCompiledOperation(MinusOperation.OP_NAME, MinusOperation.class);
		reg.registerCompiledOperation(MultiplyOperation.OP_NAME, MultiplyOperation.class);
		reg.registerCompiledOperation(DivideOperation.OP_NAME, DivideOperation.class);
		reg.registerCompiledOperation(ToDoubleOperation.OP_NAME, ToDoubleOperation.class);
		reg.registerCompiledOperation(InOperation.OP_NAME, InOperation.class);
		reg.registerCompiledOperation(FirstOperation.OP_NAME, FirstOperation.class);
		reg.registerCompiledOperation(IsEmptyOperation.OP_NAME, IsEmptyOperation.class);
		reg.registerCompiledOperation(SizeOperation.OP_NAME, SizeOperation.class);
		reg.registerCompiledOperation(SetIntersectOperation.OP_NAME, SetIntersectOperation.class);
		reg.registerCompiledOperation(NotOperation.OP_NAME, NotOperation.class);
		reg.registerCompiledOperation(OrOperation.OP_NAME, OrOperation.class);
		reg.registerCompiledOperation(AndOperation.OP_NAME, AndOperation.class);
		reg.registerCompiledOperation(GreaterThanOperation.OP_NAME, GreaterThanOperation.class);
		reg.registerCompiledOperation(LessThanOperation.OP_NAME, LessThanOperation.class);
		reg.registerCompiledOperation(GreaterOrEqualOperation.OP_NAME, GreaterOrEqualOperation.class);
		reg.registerCompiledOperation(LessOrEqualOperation.OP_NAME, LessOrEqualOperation.class);
		reg.registerCompiledOperation(EqualsOperation.OP_NAME, EqualsOperation.class);
		reg.registerCompiledOperation(AbsoluteValueOperation.OP_NAME, AbsoluteValueOperation.class);
		reg.registerCompiledOperation(NVLOperation.OP_NAME, NVLOperation.class);
		
		// Operations
		reg.registerCompiledOperation(TabReaderOperation.OP_NAME, TabReaderOperation.class);
		reg.registerCompiledOperation(FromOperation.OP_NAME, FromOperation.class);
		reg.registerCompiledOperation(WhereOperation.OP_NAME, WhereOperation.class);
		reg.registerCompiledOperation(AlignOperation.OP_NAME, AlignOperation.class);
		reg.registerCompiledOperation(CombineOperation.OP_NAME, CombineOperation.class);
		reg.registerCompiledOperation(OrderByOperation.OP_NAME, OrderByOperation.class);
		reg.registerCompiledOperation(SelectOperation.OP_NAME, SelectOperation.class);
		reg.registerCompiledOperation(JoinOperation.OP_NAME, JoinOperation.class);
		
		// Aggregate
		reg.registerCompiledOperation(GroupByOperation.OP_NAME, GroupByOperation.class);
		reg.registerCompiledOperation(HavingOperation.OP_NAME, HavingOperation.class);
		reg.registerCompiledOperation(MergeOperation.OP_NAME, MergeOperation.class);
		reg.registerCompiledOperation(SplitOperation.OP_NAME, SplitOperation.class);
		reg.registerCompiledOperation(CountOperation.OP_NAME, CountOperation.class);
		reg.registerCompiledOperation(AverageOperation.OP_NAME, AverageOperation.class);
		reg.registerCompiledOperation(MinOperation.OP_NAME, MinOperation.class);
		reg.registerCompiledOperation(MaxOperation.OP_NAME, MaxOperation.class);
		reg.registerCompiledOperation(SumOperation.OP_NAME, SumOperation.class);
		
		// Attribute
		reg.registerCompiledOperation(MPVOperation.OP_NAME, MPVOperation.class);
		reg.registerCompiledOperation(LPVOperation.OP_NAME, LPVOperation.class);
		reg.registerCompiledOperation(MaxValueCertaintyOperation.OP_NAME, MaxValueCertaintyOperation.class);
		reg.registerCompiledOperation(MinValueCertaintyOperation.OP_NAME, MinValueCertaintyOperation.class);
		reg.registerCompiledOperation(AvgValueCertaintyOperation.OP_NAME, AvgValueCertaintyOperation.class);
		reg.registerCompiledOperation(ValueCertaintyRangeOperation.OP_NAME, ValueCertaintyRangeOperation.class);
		
		// Element
		reg.registerCompiledOperation(ConfOperation.OP_NAME, ConfOperation.class);
		reg.registerCompiledOperation(BinOperation.OP_NAME, BinOperation.class);
		reg.registerCompiledOperation(IsEdgeOperation.OP_NAME, IsEdgeOperation.class);
		reg.registerCompiledOperation(IsVertexOperation.OP_NAME, IsVertexOperation.class);
		reg.registerCompiledOperation(IncidentElementsOperation.OP_NAME, IncidentElementsOperation.class);
		reg.registerCompiledOperation(DegreeOperation.OP_NAME, DegreeOperation.class);
		reg.registerCompiledOperation(IsIncidentOperation.OP_NAME, IsIncidentOperation.class);
		reg.registerCompiledOperation(AdjacentVerticesOperation.OP_NAME, AdjacentVerticesOperation.class);
		reg.registerCompiledOperation(AdjacentIdsOperation.OP_NAME, AdjacentIdsOperation.class);		
		reg.registerCompiledOperation(IsAdjacentOperation.OP_NAME, IsAdjacentOperation.class);
		reg.registerCompiledOperation(IsAdjacentByIdOperation.OP_NAME, IsAdjacentByIdOperation.class);
		reg.registerCompiledOperation(ExtractIdOperation.OP_NAME, ExtractIdOperation.class);
		reg.registerCompiledOperation(EgoNetOperation.OP_NAME, EgoNetOperation.class);
		
		
		// Similarity
		reg.registerCompiledOperation(DefaultAttribSimilarity.OP_NAME, DefaultAttribSimilarity.class);
		reg.registerCompiledOperation(ShannonEntropyRatioAttribSimilarity.OP_NAME, ShannonEntropyRatioAttribSimilarity.class);
		reg.registerCompiledOperation(ADRatioAttribSimilarity.OP_NAME, ADRatioAttribSimilarity.class);
		reg.registerCompiledOperation(MFDistanceAttribSimilarity.OP_NAME, MFDistanceAttribSimilarity.class);
		reg.registerCompiledOperation(HistogramIntersectionAttribSimilarity.OP_NAME, HistogramIntersectionAttribSimilarity.class);
		reg.registerCompiledOperation(KLDivergenceAttribSimilarity.OP_NAME, KLDivergenceAttribSimilarity.class);
		reg.registerCompiledOperation(DefaultElementSimilarity.OP_NAME, DefaultElementSimilarity.class);
		reg.registerCompiledOperation(DefaultSimilarity.OP_NAME, DefaultSimilarity.class);
		
		// Ego Network Similarity
		reg.registerCompiledOperation(EgoStructuralTopologySimilarity.OP_NAME, EgoStructuralTopologySimilarity.class);
		reg.registerCompiledOperation(EgoStructuralProbTopologicalSimilarity.OP_NAME, EgoStructuralProbTopologicalSimilarity.class);
		reg.registerCompiledOperation(EgoStructuralComparisonCountSimilarity.OP_NAME, EgoStructuralComparisonCountSimilarity.class);
		reg.registerCompiledOperation(SemanticSimilarity.OP_NAME, SemanticSimilarity.class);
		reg.registerCompiledOperation(MPVAttributeSimilarity.OP_NAME, MPVAttributeSimilarity.class);
		
		// Graph
		reg.registerCompiledOperation(ToGraphOperation.OP_NAME, ToGraphOperation.class);
		reg.registerCompiledOperation(ToElementsOperation.OP_NAME, ToElementsOperation.class);
		reg.registerCompiledOperation(UnionOperation.OP_NAME, UnionOperation.class);
		reg.registerCompiledOperation(DifferenceOperation.OP_NAME, DifferenceOperation.class);
		reg.registerCompiledOperation(IntersectionOperation.OP_NAME, IntersectionOperation.class);
		reg.registerCompiledOperation(BidirectionalDifferenceOperation.OP_NAME, BidirectionalDifferenceOperation.class);
		reg.registerCompiledOperation(NumVerticesOperation.OP_NAME, NumVerticesOperation.class);
		reg.registerCompiledOperation(NumEdgesOperation.OP_NAME, NumEdgesOperation.class);
		
		// Directed
		reg.registerCompiledOperation(RootsOperation.OP_NAME, RootsOperation.class);
		reg.registerCompiledOperation(HasParentsOperation.OP_NAME, HasParentsOperation.class);
		reg.registerCompiledOperation(HasChildrenOperation.OP_NAME, HasChildrenOperation.class);
		reg.registerCompiledOperation(ChildrenOperation.OP_NAME, ChildrenOperation.class);
		reg.registerCompiledOperation(ParentsOperation.OP_NAME, ParentsOperation.class);
		reg.registerCompiledOperation(SiblingsOperation.OP_NAME, SiblingsOperation.class);
		reg.registerCompiledOperation(TreeDepthOperation.OP_NAME, TreeDepthOperation.class);
		reg.registerCompiledOperation(TreeSizeOperation.OP_NAME, TreeSizeOperation.class);
		reg.registerCompiledOperation(TreeOperation.OP_NAME, TreeOperation.class);
		reg.registerCompiledOperation(SwitchCountOperation.OP_NAME, SwitchCountOperation.class);
		reg.registerCompiledOperation(SwitchCountPositiveOperation.OP_NAME, SwitchCountPositiveOperation.class);
		reg.registerCompiledOperation(SwitchCountNegativeOperation.OP_NAME, SwitchCountNegativeOperation.class);
		reg.registerCompiledOperation(CountFeatureOperation.OP_NAME, CountFeatureOperation.class);
		reg.registerCompiledOperation(IsEdgeDirectedOperation.OP_NAME, IsEdgeDirectedOperation.class);
		reg.registerCompiledOperation(AdjacentVerticesUndirectedOperation.OP_NAME, AdjacentVerticesUndirectedOperation.class);
		reg.registerCompiledOperation(EgoNetUndirectedOperation.OP_NAME, EgoNetUndirectedOperation.class);
		
		// Special
		reg.registerCompiledOperation(PathOperation.OP_NAME, PathOperation.class);
	}
}
