/**
 * 
 */
package invenio.operator.io;

import linqs.gia.configurable.BaseConfigurable;
import linqs.gia.configurable.Configurable;
import linqs.gia.graph.Graph;
import linqs.gia.io.IO;
import linqs.gia.util.Dynamic;

/**
 * @author ddimitrov
 *
 */
public class GiaFileReader {
	
	public static Graph readGraph(String configFile) {
		Configurable conf = new BaseConfigurable() {};	// work-around to get the "ioclass" parameter
		conf.setParametersFile(configFile);
		
		// Load IO class
		IO io = (IO)
			Dynamic.forConfigurableName(IO.class, conf.getStringParameter("ioclass"));
		io.copyParameters(conf);

		return io.loadGraph();
	}
}
