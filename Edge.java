
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
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return from.toString().equals(edge.from.toString()) && to.toString().equals(edge.to.toString());
    }
	
	@Override
    public int hashCode() {
		return from.hashCode() * 31 + to.hashCode();
	}
	
}
