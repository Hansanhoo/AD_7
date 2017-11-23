public class Node<T> {

		int key;		
		Elem elem;
		Node leftChild;
		Node rightChild;

		Node(int key) {
			this.key = key;
			this.elem = new Elem(key,""+key);
		}

		public String toString() {
			return "Key : " + this.key;
		}



}