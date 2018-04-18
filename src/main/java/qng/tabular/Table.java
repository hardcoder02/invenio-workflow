package qng.tabular;

import java.util.Iterator;
import java.util.List;

public interface Table {
	Schema getSchema();
	int getSize();
	Iterator<? extends Tuple> getIterator();
	List<? extends Tuple> getTuples();
}
