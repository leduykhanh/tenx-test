
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
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex vetex = (Vertex) o;
        return vetex.getCurrency().equals(currency) && vetex.getExchange().equals(exchange);
    }
	
	@Override
    public int hashCode() {
		return exchange.hashCode() * 31 + currency.hashCode();
	}
}
