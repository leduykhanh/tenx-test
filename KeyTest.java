import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KeyTest {

	@Test
	void test() {
		Key key1 = new Key(new Vertex("A", "USD"), new Vertex("B", "BTC"));
		Key key2 = new Key(new Vertex("A", "USD"), new Vertex("B", "BTC"));
		Key key3 = new Key(new Vertex("A1", "USD"), new Vertex("B", "BTC"));
		assertEquals(key1, key2);
		assertNotEquals(key1, key3);
	}

}
