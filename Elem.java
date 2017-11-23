public class Elem<T> {
	
	/**
	 * Attribute
	 */
	private int key;
	private String data;
	/**
	 * Getter Setter
	 */
	public void setKey(int k) {
		this.key = k;
	}
	public int getKey() {
		return this.key;
	}
	/**
	 * Konstruktor
	 */
	public Elem(int key, T data) {
		this.key = key;
		this.data = (String) data;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.data == null) ? 0 : this.data.hashCode());
		result = prime * result +  this.key;
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		Elem<T> other = (Elem) obj;
		if (this.data == null) {
			if (other.data != null)
				return false;
		} else if (!this.data.equals(other.data))
			return false;
		
		return true;
	}
}
