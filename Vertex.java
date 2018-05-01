
public class Vertex {
	String exchange;
	String currency;
	
	public Vertex (String exchange, String currency) {
		this.exchange = exchange;
		this.currency = currency;
	}
	
	@Override
	public String toString() {
		return "(" + exchange + "," + currency + ")";
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public String getExchange() {
		return exchange;
	}
}
