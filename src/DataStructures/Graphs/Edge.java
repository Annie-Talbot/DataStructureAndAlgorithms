package DataStructures.Graphs;

/**
 * Edge object for use in a graph structure. This class provides additional functionality for
 * weight, direction and for use in sorting algorithms but only vertices are public.
 * 
 * @author Annie Talbot
 *
 */
public class Edge {
	
	/**
	 * The value used to represent the cost of traversing this edge.
	 */
	private int weight;
	

	/**
	 * @return the weight assigned to this edge
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the value to represent the cost of traversing this edge
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	/**
	 * Represents whether the edge is directed of not
	 */
	private boolean isDirected = false;
	
	/**
	 * The start vertex of the edge (if undirected this could be either vertex).
	 */
	private int sVertex;
	public int getStartVertex() {
		return sVertex;
	}
	/**
	 * The end vertex of the edge(if undirected this could be either vertex).
	 */
	private int eVertex;

	public int getEndVertex() {
		return eVertex;
	}
	public int getOppositeVertex(int vertex) {
		return eVertex==vertex? sVertex: eVertex;
	}
	/**
	 * The status of this edge during graph traversal i.e. visited, seen, not seen
	 */
	private Status status;
	
	/**
	 * @return true if this edge has the status visited
	 */
	public boolean isVisited() {
		return status == Status.VISITED;
	}
	
	/**
	 * @return true if this edge has the status seen
	 */
	public boolean isSeen() {
		return status == Status.SEEN ||  status == Status.VISITED;
	}

	/**
	 * Assigns this edge a new status
	 * @param status the new status of this edge
	 */
	public void setStatus(Status status) {
		this.status = status;
	}
	
	/**
	 * Constructor for an edge with no weight.
	 */
	public Edge(Integer startVertex,Integer endVertex, boolean isDirected) {
		if (startVertex != null) {
			this.sVertex = startVertex;
		} else {
			this.sVertex = -1;
		}
		this.eVertex = endVertex;
		setWeight(0);
		this.isDirected = isDirected;
		setStatus(Status.NOT_SEEN);
	}
	
	/**
	 * Constructor for an edge with a weight
	 */
	public Edge(Integer startVertex,Integer endVertex, Integer weight, boolean isDirected) {
		if (startVertex != null) {
			this.sVertex = startVertex;
		} else {
			this.sVertex = -1;
		}
		this.eVertex = endVertex;
		setWeight(weight);
		this.isDirected = isDirected;
		setStatus(Status.NOT_SEEN);
	}

	/**
	 * Checks if this edge is directed (one-way).
	 * @return True = is directed, False = is NOT directed
	 */
	public boolean isDirected() {
		return isDirected;
	}
	
	@Override
	public String toString() {
		return "(" + sVertex + "," + eVertex + ")";
	}
}
