package qng.core.query;

import qng.core.datastructures.BasicTree;

public interface CompiledQuery<O> {
	BasicTree<O> getQueryTree();
}
