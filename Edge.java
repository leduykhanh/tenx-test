
public class Edge {
	Vertex from;
	Vertex to;
	float weight;
	
	public Edge(Vertex from, Vertex to, float weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return from.toString() + " -- " + weight + " --> " + to.toString();
	}
}
