package GUI;


import Graph.Node;
import Graph.Position;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Reads in a previously serialised graph. All nodes in the
 * graph are stored into the nodes class variable.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class GraphLoader {
	
	private static Node[] nodes = null;
	
	public GraphLoader() {
		deserialiseNodes();
	}
	
	private void deserialiseNodes() {
		try {
			FileInputStream fileIn;
			ObjectInputStream in;

			System.out.println(System.getProperty("user.dir"));
			fileIn = new FileInputStream("/home/p/DataStruct/Assigment/Assigment01/src/Resources/simple_graph.ser");
			in = new ObjectInputStream(fileIn);
			nodes = (Node[]) in.readObject();
			in.close();
			fileIn.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Serialised graph file not found");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("An I/O error has occurred");
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			System.out.println("Error trying to load a non-existent class");
			e.printStackTrace();
		}
	}
	
	public static Node[] getNodes() {
		return nodes;
	}
}
