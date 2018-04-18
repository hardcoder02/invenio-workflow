package invenio.op.parser;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.BaseTree;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.CommonTreeNodeStream;

import qng.core.logical.LogicalExpression;


public class TestGraphQueryParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testQueryParser();
		testQueryTree();
	}
	
	public static void testQueryParser() {
		GraphQueryLexer lex = new GraphQueryLexer(new ANTLRStringStream("{name=\"value\", another_name=\"another_value\"} SELECT func() as loc FROM (SELECT * FROM TYPE NODE AS n1 TAB READER \"test1\" \"test2\" JOIN SELECT * FROM TYPE NODE AS n2 TAB READER \"test3\" \"test4\" ON a()==b() ) WHERE eq(1,2) SPLIT BY gender;"));
       	CommonTokenStream tokens = new CommonTokenStream(lex);

        GraphQueryParser parser = new GraphQueryParser(tokens);

        try {
        	GraphQueryParser.query_return parserResult =
            	parser.query(); // start rule method
            CommonTree tree = (CommonTree) parserResult.getTree();
            outputNode(tree);
            System.out.println(tree);
            System.out.println(tree.toStringTree());
        } catch (RecognitionException e)  {
            e.printStackTrace();
        }
	}
	
	public static void testQueryTree() {
		GraphQueryLexer lex = new GraphQueryLexer(new ANTLRStringStream("{name=\"value\", another_name=\"another_value\"} SELECT func() as loc FROM (SELECT * FROM TYPE NODE AS n1 TAB READER \"test1\" \"test2\" JOIN SELECT * FROM TYPE NODE AS n2 TAB READER \"test3\" \"test4\" ON a()==b() ) WHERE eq(1,2) SPLIT BY gender;"));
       	CommonTokenStream tokens = new CommonTokenStream(lex);

        GraphQueryParser parser = new GraphQueryParser(tokens);

        try {
        	GraphQueryParser.query_return parserResult =
            	parser.query(); // start rule method
            CommonTree tree = (CommonTree) parserResult.getTree();
            
            GraphQueryTree finalTree = new GraphQueryTree(new CommonTreeNodeStream(tree));
            LogicalExpression exp = finalTree.query();
            System.out.println("Result is: " + exp );

        } catch (RecognitionException e)  {
            e.printStackTrace();
        }
	}

	public static void outputNode(BaseTree tree) {
		System.out.println("text: " + tree.getText() + " begin");
		
		for (int i = 0; i < tree.getChildCount(); i++)
			outputNode ( (BaseTree) tree.getChild(i));
		
		System.out.println("text: " + tree.getText() + " end");
	}
}
