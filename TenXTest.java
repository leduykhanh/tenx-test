import java.util.*;

class TenXTest {

	public static void main(String[] args) {
		TenXTest test = new TenXTest();
		test.readData();
	}

	public static Scanner sc = new Scanner(System.in);
	Set<Edge> graph = new HashSet<Edge>();
	Set<Vertex> vertices = new HashSet<Vertex>();

	Map<Key, Float> rate = new HashMap<Key, Float>();
	Map<Key, Vertex> next = new HashMap<Key, Vertex>();

	void readData() {
		while (sc.hasNext()) {
			String line = sc.nextLine();
			String items[] = line.split(" ");
			
			//Exchange Rate Requests
			if(line.contains("EXCHANGE_RATE_REQUEST")) {
				String source_exchange = items[1];
				String source_currency = items[2];
				String destination_exchange = items[3];
				String destination_currency = items[4];
				Vertex u = new Vertex(source_exchange, source_currency);
				Vertex v = new Vertex(destination_exchange, destination_currency);
				Key uv = new Key(u, v);
				bestRates();
				System.out.println("BEST_RATES_BEGIN " + source_exchange + " " + source_currency 
						+ " " + destination_exchange + " " + destination_currency + " " + rate.get(uv));			
				printPath(path(u, v));
				System.out.println("BEST_RATES_END");

 			}
			
			else { // Price updates
				String exchange = items[1];
				String source_currency = items[2];
				String destination_currency = items[3];
				float forward_factor = Float.parseFloat(items[4]);
				float backward_factor = Float.parseFloat(items[5]);
				Vertex v1 = new Vertex(exchange, source_currency);
				Vertex v2 = new Vertex(exchange, destination_currency);
				Edge e1 = new Edge(v1, v2, forward_factor);
				Edge e2 = new Edge(v2, v1, backward_factor);
				
				for(Vertex v : vertices) {
					if (v.getCurrency().equals(source_currency)) {
						graph.add(new Edge(v1, v, 1));
						graph.add(new Edge(v, v1, 1));
					}
					
					if (v.getCurrency().equals(destination_currency)) {
						graph.add(new Edge(v2, v, 1));
						graph.add(new Edge(v, v2, 1));
					}
				}
				graph.add(e1);
				graph.add(e2);
				vertices.add(v1);
				vertices.add(v2);
			}
		}
	}
	
	void printGraph() {
		for(Edge edge : graph ) {
			System.out.println(edge);
		}
	}
	
	void bestRates() {
		for(Edge edge : graph ) {
			Key uv = new Key(edge.getFrom(), edge.getTo());
			rate.put(uv, edge.getWeight());
			next.put(uv, edge.getTo());
		}
		
		for(Vertex k : vertices)
			for(Vertex i : vertices)
				for(Vertex j : vertices) {
					if (rate.getOrDefault(new Key(i, j), (float)0.0) 
							< rate.getOrDefault(new Key(i, k), (float)0.0) * rate.getOrDefault(new Key(k, j), (float)0.0)) {
						rate.put(new Key(i ,j), rate.get(new Key(i, k)) * rate.get(new Key(k, j)));
						next.put(new Key(i ,j), next.get(new Key(i, k)));
					}
				}
			
	}
	
	List<Vertex> path(Vertex u, Vertex v) {
		List<Vertex> path = new ArrayList<Vertex>(); 
		if (next.get(new Key(u, v)) == null) return path;
		path.add(u);
		while (!u.equals(v)) {
			u = next.get(new Key(u, v));
			path.add(u);
		}
		return path;
	}
	
	void printPath(List<Vertex> path) {
		for (Vertex v : path) {
			System.out.println(v);
		}
	}
	
	Key getKey(int i, int j) {
		Object vArray[] = vertices.toArray();
		return new Key((Vertex)vArray[i], (Vertex)vArray[j]);
	}
	
}
