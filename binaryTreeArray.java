import java.util.Arrays;
import java.util.Random;

public class binaryTreeArray implements I_BinaryTree{

		Node root;
		int i;
		Node[] arr;
		public binaryTreeArray(int size){
			this.i = 0;
			this.arr = new Node[size];
			
			//ohne [0] => (2*) (2*+1) 
			this.arr[0] = new Node(-1);
			
		}

		public void addNode(Node newNode) {
			
			// If there is no root this becomes root 

			if (this.arr[1] == null) {

				System.out.println("Insert Root");
				System.out.println(newNode.toString());
				this.arr[1] = newNode;
				this.root = newNode;
				

			} else {

				// Set root as the Node we will start
				// with as we traverse the tree [1]

				Node focusNode = this.arr[1];
				
				//counter - start at root = 1
				this.i = 1;				

				//parent that we know which index is our parent
				int parent;

				while (true) {

					// root is the top parent so we start here
					
					parent = this.i;

					// Check if the new node should go on
					// the left side of the parent node
					
					System.out.println("Node to insert key: " +newNode.key + "  focusNodeKey: " + focusNode.key);
					
					
					if (newNode.key < focusNode.key) {

						// Switch focus to the left child
						focusNode = this.arr[i*2];
						this.i *= 2;
						// If the left child has no children
						if (focusNode == null) {
							System.out.println("Insert Left");
							// then place the new node on the left of it
							this.arr[parent*2] = newNode;
							return; 

						}

					} else { // If we get here put the node on the right
						
						focusNode = this.arr[i*2+1];
						this.i = (this.i*2)+1;
						
						// If the right child has no children
						if (focusNode == null) {
							System.out.println("Insert Right");
							// then place the new node on the right of it
							this.arr[(parent*2)+1] = newNode;
							return; 

						}

					}

				}
			}

		}

		// All nodes are visited in ascending order
		// Recursion is used to go to one node and
		// then go to its child nodes and so forth
		int index = 1 ;
		public void inOrderTraverseTree(Node node) {

			int index = Arrays.asList(this.arr).indexOf(node);
			if (node != null) {

				// Traverse the left node
				inOrderTraverseTree(this.arr[index * 2]);

				// Visit the currently focused on node				
				System.out.println(this.arr[index].key);				

				// Traverse the right node
				inOrderTraverseTree(this.arr[(index * 2) +1]);

			}

		}

		public void preorderTraverseTree(Node node) {

			int index = Arrays.asList(this.arr).indexOf(node);
			if (this.arr[index] != null) {
				
				System.out.println(this.arr[index].key);
				
				preorderTraverseTree(this.arr[index * 2]);
				preorderTraverseTree(this.arr[(index * 2) +1]);

			}

		}

		public void postOrderTraverseTree(Node node) {
			
			int index = Arrays.asList(this.arr).indexOf(node);
			if (node != null) {				
				preorderTraverseTree(this.arr[index * 2]);
				preorderTraverseTree(this.arr[(index * 2) +1]);
				
				System.out.println(this.arr[index].key);

			}

		}
	

	public static void main(String[] args) {

			binaryTreeArray theTree = new binaryTreeArray(1000);
			Random rnd = new Random();
			for(int i = 0 ; i < 10 ; i++){
				int key = rnd.nextInt(100);
				theTree.addNode(new Node(key));
			}
		

			// Different ways to traverse binary trees
			
			System.out.println("In Order Array :");

			theTree.inOrderTraverseTree(theTree.root);
			
			System.out.println("Pre Order Array :");

			theTree.preorderTraverseTree(theTree.root);
			
			System.out.println("Post Order Array :");

			theTree.postOrderTraverseTree(theTree.root);
	

	
		}
	}


