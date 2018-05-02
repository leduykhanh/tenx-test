
public class Key {
	Vertex u;
	Vertex v;
	
	public Key(Vertex u, Vertex v) {
		this.u = u;
		this.v = v;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        return u.toString().equals(key.u.toString()) && v.toString().equals(key.v.toString());
    }
}
