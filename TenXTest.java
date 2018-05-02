import java.util.*;

class TenXTest {

	public static void main(String[] args) {
		TenXTest test = new TenXTest();
		test.readData();
		test.printGraph();
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
				//EXCHANGE_RATE_REQUEST KRAKEN BTC GDAX USD 
				String source_exchange = items[1];
				String source_currency = items[2];
				String destination_exchange = items[3];
				String destination_currency = items[4];
				Vertex u = new Vertex(source_exchange, source_currency);
				Vertex v = new Vertex(destination_exchange, destination_currency);
				System.out.println("BEST_RATES_BEGIN");
				System.out.println(next.size());
				bestRates();
				path(u, v);
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
		
		for (int k = 0; k < vertices.size(); k++)

			for (int i = 0; i < vertices.size(); i++)

				for (int j = 0; k < vertices.size(); j++) {

					if (rate.get(getKey(i ,j)) < rate.get(getKey(i, k)) * rate.get(getKey(k, j))) {
						rate.put(getKey(i ,j), rate.get(getKey(i, k)) * rate.get(getKey(k, j)));
						next.put(getKey(i ,j), next.get(getKey(i, k)));
					}
				}
	}
	
	void path(Vertex u, Vertex v) {
		if (next.get(new Key(u, v)) == null) return;
		while (!u.equals(v)) {
			u = next.get(new Key(u, v));
			System.out.println(u);
		}
	}
	
	private Key getKey(int i, int j) {
		Object vArray[] = vertices.toArray();
		return new Key((Vertex)vArray[i], (Vertex)vArray[j]);
	}
	
}
