package invenio.op.parser;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import qng.client.QueryException;
import qng.core.logical.LogicalExpression;
import qng.core.parser.AbstractParser;
import qng.core.parser.QueryParser;
import qng.core.query.io.QuerySerializer;

public class XMLToLogicalExpressionParser extends AbstractParser<String, LogicalExpression> implements
		QueryParser<String, LogicalExpression> {

	@Override
	public LogicalExpression parse(String query) throws QueryException {
		try {
			InputStream is = new ByteArrayInputStream(query.getBytes());
	
			return QuerySerializer.loadQuery(is);
        
        } catch (RuntimeException e)  {
            throw new QueryException("Error parsing query [" + query + "]: " + e.getMessage(), e);
        }
	}
}
