package invenio.wf.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import invenio.data.InvenioGraph;

public class InvenioGraphSerializer {
	public static void serialize(InvenioGraph graph, String fileName) throws IOException {
		long startTime = System.currentTimeMillis();
		System.out.println("Starting serialization of graph: " + graph.getName());
		FileOutputStream fileOut =
				new FileOutputStream(fileName);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(graph);
		out.close();
		fileOut.close();
		
		long duration = System.currentTimeMillis() - startTime;
		System.out.println("Finished serializing graph: " + graph.getName() + " in (msec): " + duration);
	}
	
	public static InvenioGraph deserialize(String fileName) throws IOException, ClassNotFoundException {
		long startTime = System.currentTimeMillis();
		System.out.println("Starting deserialization of graph in file: " + fileName);
		FileInputStream fileIn =
				new FileInputStream(fileName);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		InvenioGraph result = (InvenioGraph) in.readObject();
		in.close();
		fileIn.close();
		
		long duration = System.currentTimeMillis() - startTime;
		System.out.println("Finished deserializing graph: " + result.getName() + " in (msec): " + duration);
		return result;
	}
}
