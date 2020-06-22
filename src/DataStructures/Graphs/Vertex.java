package DataStructures.Graphs;


/**
 * Vertex object for use in a graph. This class is able to hold an object and an
 * ID describing itself. The edge list of these vertices holds the connecting 
 * vertex's ID (for basic algorithms such as ...........) but also the edge object
 * that connects the vertices (for more complex algorithms such as .............)
 * 
 * @author Annie Talbot
 */
public class Vertex<T> {
	
	/**
	 * The number associated with this vertex and it's position in the graph 
	 * vertex array
	 */
	private int id;
	
	/**
	 * @return the id of this node
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * The value of this vertex
	 */
	private T value;
	
	/**
	 * @return the value that this vertex represents
	 */
	public T getValue() {
		return value;
	}

	/**
	 * Assigns this vertex a value.
	 * @param value the value for this vertex to hold/represent
	 */
	public void setValue(T value) {
		this.value = value;
	}
	
	/**
	 * The status of this vertex during graph traversal i.e. visited, seen, not seen
	 */
	private Status status;
	
	/**
	 * @return true if this vertex has the status visited
	 */
	public boolean isVisited() {
		return status == Status.VISITED;
	}
	
	/**
	 * @return true if this vertex has the status seen
	 */
	public boolean isSeen() {
		return status == Status.SEEN || status == Status.VISITED;
	}

	/**
	 * Assigns this vertex a new status
	 * @param status the new status of this vertex
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	/**
	 * The list of vertices this vertex connects to, and the edge that connects them.
	 * Each node in the list will consist of 2 values - at position 0 is the ID of the
	 * connected node and at position 1 will be the edge object (for use with graph 
	 * traversal algorithms)
	 */
	LinkedList<Object[]> edges = new LinkedList<Object[]>();
	
	/**
	 * Constructor to create an empty vertex.
	 * @param id The number representing this vertex and it's position in the graph 
	 * vertex array
	 */
	public Vertex(Integer id) {
		this.id = id;
		setStatus(Status.NOT_SEEN);
	}
	
	/**
	 * Constructor to create a vertex with an object assigned to it.
	 * @param id The number representing this vertex and it's position in the graph 
	 * vertex array
	 * @param value The value for this vertex to hold
	 */
	public Vertex(Integer id, T value) {
		this.id = id;
		setValue(value);
		setStatus(Status.NOT_SEEN);
	}
	
	/**
	 * Creates a connection between this vertex and another vertex in the graph.
	 * @param edge The Edge object that connects the 2 vertices
	 * @param endVertex The ID of the vertex connected to this one
	 */
	public void addEdge(Edge edge, Integer endVertex) {
		edges.add(new Object[] {endVertex, edge});
	}
	
	public LinkedList<Object[]> getEdges() {
		return edges;
	}
	
	/**
	 * Operation to check whether this vertex connects to another with the ID given
	 * @param vertexId the ID of the vertex being tested for a connection with this
	 * one
	 * @return true = this vertex is connect to the one with the given ID, false = 
	 * it is not
	 */
	public boolean isConnectedTo(int vertexId) {
		for (Object[] edge : edges) {
			if ((int) edge[0] == vertexId) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the details of this vertex including its value (if it has one) and all
	 * connected vertices.
	 * 
	 * @return a string containing the details of all edges connected to this vertex
	 */
	public String toString() {
		String vertex = "Vertex " + id + ": \n";
		if (value != null) {
			vertex += "Value: " + value.toString() + " \n";
		}
		vertex += " \n";
		return vertex;
	}
	
	/**
	 * Returns the details of this vertex including its value (if it has one) and all
	 * connected vertices.
	 * 
	 * @return a string containing the details of all edges connected to this vertex
	 */
	public String toString(boolean withEdges) {

		String vertex = "Vertex " + id + ": \n";
		if (value != null) {
			vertex += "Value: " + value.toString() + " \n";
		}
		if (withEdges) {
			vertex += "Edges: ";
			for (Object[] edge : edges) {
				vertex += id + "--<" + ((Edge) edge[1]).getWeight() + ">--" + edge[0] + " | ";
			}
		}
		vertex += " \n";
		return vertex;
	}
	
}
