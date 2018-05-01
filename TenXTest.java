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
	Map<Vertex, Vertex> rate = new HashMap<Vertex, Vertex>();

	void readData() {
		while (sc.hasNext()) {
			String line = sc.nextLine();
			
			//Exchange Rate Requests
			if(line.contains("EXCHANGE_RATE_REQUEST"))
				System.out.println(line);
			
			else { // Price updates
				String items[] = line.split(" ");
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
		
	}
}
