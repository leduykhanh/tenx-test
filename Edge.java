
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
	
	public Vertex getFrom() {
		return this.from;
	}
	
	public Vertex getTo() {
		return this.to;
	}
	
	public float getWeight() {
		return this.weight;
	}
}
