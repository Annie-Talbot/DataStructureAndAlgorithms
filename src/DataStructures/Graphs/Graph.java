package DataStructures.Graphs;

import DataStructures.PriorityQueue;
import DataStructures.Queue;
import DataStructures.Stack;
import DataStructures.Trees.TreeIsEmptyException;

/**
 * Graph object for a homogeneous graph data structure implemented using an
 * adjacency list.
 * 
 * @author Annie Talbot
 *
 */
public class Graph<T> {
	
	/**
	 * The list of vertices in the graph
	 */
	private Vertex<T>[] vertices;
	
	/**
	 * The weight of all the edges in the graph
	 */
	private int totalWeight;
	
	/**
	 * @return the combined weight of all the edges in the graph
	 */
	public int getTotalWeight() {
		return totalWeight;
	}
	
	/**
	 * The number of vertices in the graph
	 */
	private int noVertices;
	
	/**
	 * @return the number of vertices in the graph
	 */
	public int getNoVertices() {
		return noVertices;
	}
	
	/**
	 * Constructor for an instance of a graph.
	 * @param noVertices the number of vertices the graph will have
	 */
	@SuppressWarnings("unchecked")
	public Graph(int noVertices) {
		this.noVertices = noVertices;
		vertices = (Vertex<T>[]) new Vertex[noVertices];
		for (int i = 0; i < noVertices; i ++) {
			vertices[i] = new Vertex<T>(i);
		}
	}
	
	/**
	 * Operation for adding an edge to the graph with no weight
	 * @param startVertex the start vertex of the edge
	 * @param endVertex the end vertex of the edge
	 * @param directed true = directed edge so can only travel from start to end 
	 * vertex (not vice versa), false = can travel either direction
	 * @throws EdgeAlreadyExistsException the edge requested to add is already in the graph
	 */
	public void addEdge(int startVertex, int endVertex, boolean directed) 
			throws EdgeAlreadyExistsException {
		try {
			if (!vertices[startVertex].isConnectedTo(endVertex) && 
					!vertices[endVertex].isConnectedTo(startVertex)) {
				Edge edge = new Edge(startVertex, endVertex, directed);
				vertices[startVertex].addEdge(edge, endVertex);
				if (!directed) {
					vertices[endVertex].addEdge(edge, startVertex);
				}
			} else {
				throw new EdgeAlreadyExistsException("This edge already exists within the graph");
			}
		} catch (IndexOutOfBoundsException e) {
		}
	}
	
	/**
	 * Operation for adding an edge to the graph with a weight assigned to it.
	 * @param startVertex the start vertex of the edge
	 * @param endVertex the end vertex of the edge
	 * @param weight the cost of traversing from one vertex to the other
	 * @param directed true = directed edge so can only travel from start to end 
	 * vertex (not vice versa), false = can travel either direction
	 * @throws EdgeAlreadyExistsException the edge requested to add is already in the graph
	 */
	public void addEdge(int startVertex, int endVertex, int weight, boolean directed) 
			throws EdgeAlreadyExistsException {
		try {
			if (!vertices[startVertex].isConnectedTo(endVertex) && 
					!vertices[endVertex].isConnectedTo(startVertex)) {
				Edge edge = new Edge(startVertex, endVertex, weight, directed);
				vertices[startVertex].addEdge(edge, endVertex);
				if (!directed) {
					vertices[endVertex].addEdge(edge, startVertex);
				}
			} else {
				throw new EdgeAlreadyExistsException("This edge already exists within the graph");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}
	
	public void addEdge(Edge edge) throws EdgeAlreadyExistsException {
		try {
			if (!vertices[edge.getEndVertex()].isConnectedTo(edge.getStartVertex()) && 
					!vertices[edge.getStartVertex()].isConnectedTo(edge.getEndVertex())) {
				vertices[edge.getStartVertex()].addEdge(edge, edge.getEndVertex());
				if (!edge.isDirected()) {
					vertices[edge.getEndVertex()].addEdge(edge, edge.getStartVertex());
				}
			} else {
				throw new EdgeAlreadyExistsException("This edge already exists within the graph");
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		
	}
	
	/**
	 * Breadth First Sort. Returns the vertex ID's in a linked list resembling the order
	 * that the graph would be traversed in a breadth first sort.
	 * @param startVertexId the ID of the vertex to start the sort on
	 */
	public Queue<Integer> BFS(int startVertexId) {
		//Create exception here!
		Queue<Integer> route = new Queue<Integer>();
		Queue<Vertex<T>> seenNodes = new Queue<Vertex<T>>();
		vertices[startVertexId].setStatus(Status.SEEN);
		seenNodes.enqueue(vertices[startVertexId]);
		
		while (!seenNodes.isEmpty()) {
			Vertex<T> currVertex = seenNodes.dequeue();			
			if (!currVertex.isVisited()) {
				for (Object[] e : currVertex.getEdges()) {
					Vertex<T> v = vertices[(int) e[0]];
					if (!v.isSeen()) {
						v.setStatus(Status.SEEN);
						seenNodes.enqueue(v);
					}
				}
				route.enqueue(currVertex.getId());
				currVertex.setStatus(Status.VISITED);
			}
		}
		return route;
	}
	
	/**
	 * Depth First Sort. Returns the vertex ID's in a linked list resembling the order
	 * that the graph would be traversed in a depth first sort.
	 * @param startVertexId the ID of the vertex to start the sort on
	 */
	public Queue<Integer> DFS(int startVertexId) {
		//Create exception here!
		Queue<Integer> route = new Queue<Integer>();
		Stack<Vertex<T>> seenNodes = new Stack<Vertex<T>>();
		vertices[startVertexId].setStatus(Status.SEEN);
		seenNodes.push(vertices[startVertexId]);
		
		while (!seenNodes.isEmpty()) {
			Vertex<T> currVertex = seenNodes.pop();		
			if (!currVertex.isVisited()) {
				for (Object[] e : currVertex.getEdges()) {
					Vertex<T> v = vertices[(int) e[0]];
					if (!v.isVisited()) {
						v.setStatus(Status.SEEN);
						seenNodes.push(v);
					}
				}
				route.enqueue(currVertex.getId());
				currVertex.setStatus(Status.VISITED);
			}
		}
		return route;
	}
	
	public Graph<T> prims(int startVertexId) {
		// Holds edges that have been seen, using edge weights as priority
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
		// The minimum spanning tree formed by prims algorithm
		Graph<T> MST = new Graph<T>(getNoVertices());
		
		Edge currEdge = new Edge(null, startVertexId, 0, false);
		currEdge.setStatus(Status.SEEN);
		queue.push(currEdge, 0);
		
		while (!queue.isEmpty()) {
			currEdge = queue.pop();
			Vertex<T> u = vertices[currEdge.getEndVertex()];
			if (!u.isVisited()) {
				for (Object[] x: u.getEdges()) {
					Edge e = (Edge) x[1];
					if (!e.isSeen()) {
						e.setStatus(Status.SEEN);
						queue.push(new Edge(u.getId(), e.getOppositeVertex(u.getId()), e.getWeight(), false), e.getWeight());
					}
				}
				currEdge.setStatus(Status.VISITED);
				u.setStatus(Status.VISITED);
				
				try {
					MST.addEdge(currEdge);
				} catch (EdgeAlreadyExistsException e) {
					e.printStackTrace();
				}
			}
			
		}
		return MST;
	}
	
	public Graph<T> dijkstra(int startVertexId) {
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
		Graph<T> SPT = new Graph<T>(getNoVertices());
		
		Edge currEdge = new Edge(-1, startVertexId, 0, false);
		queue.push(currEdge, 0);
		
		while (!queue.isEmpty()) {
			currEdge = queue.pop();
			Vertex<T> u = vertices[currEdge.getEndVertex()];
			
			for (Object[] x: u.getEdges()) {
				Edge e = (Edge) x[1];
				if (!e.isSeen()) {
					e.setStatus(Status.SEEN);
					int cost = currEdge.getWeight() + e.getWeight();
					queue.push(new Edge(u.getId(), e.getOppositeVertex(u.getId()), 
							cost, false), cost);
				}
			}
			
			currEdge.setStatus(Status.VISITED);
			u.setStatus(Status.VISITED);
			try {
				SPT.addEdge(currEdge);
			} catch (EdgeAlreadyExistsException e) {
				e.printStackTrace();
			}
			
		}
		return SPT;
	}
	
	/**
	 * Returns all values in the list (in the order they are stored in) as a string.
	 * 
	 * @return string A string displaying the contents of the list
	 */
	@Override
	public String toString() {
		String graph = "Graph Adjacenty List: \n";
		for (int i = 0; i < vertices.length; i++) {
			graph += vertices[i].toString(true) + " \n";
		}
		return graph;
	}

	/**
	 * Resets the status of all vertices and edges in the graph to 'not seen'
	 */
	public void resetStatus() {
		for( Vertex<T> v : vertices) {
			v.setStatus(Status.NOT_SEEN);
			for (Object[] e : v.getEdges()) {
				((Edge) e[1]).setStatus(Status.NOT_SEEN);
			}
		}
	}


}
