import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class VertexTest {

	@Test
	void test() {
		Vertex v1 = new Vertex("A", "USD");
		Vertex v2 = new Vertex("A", "USD");
		Vertex v3 = new Vertex("B", "USD");
		
		assertEquals(v1, v2);
		assertNotEquals(v1, v3);
	}

}
