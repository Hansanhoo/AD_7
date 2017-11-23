import java.util.Random;

public class BinaryTree implements I_BinaryTree {

		Node root;

		public void addNode(Node newNode) {


			// If there is no root this becomes root

			if (root == null) {

				System.out.println("Insert Root");
				System.out.println(newNode.toString());
				
				root = newNode;

			} else {

				// Set root as the Node we will start
				// with as we traverse the tree

				Node focusNode = root;
				

				// Future parent for our new Node

				Node parent;

				while (true) {

					// root is the top parent so we start here	
					parent = focusNode;

					// Check if the new node should go on
					// the left side of the parent node
					System.out.println("Node to insert key: " +newNode.key + "  focusNodeKey: " + focusNode.key);
					if (newNode.key < focusNode.key) {

						// Switch focus to the left child

						focusNode = focusNode.leftChild;

						// If the left child has no children

						if (focusNode == null) {
							System.out.println("Insert Left");

							// then place the new node on the left of it

							parent.leftChild = newNode;
							return; // All Done

						}

					} else { // If we get here put the node on the right

						focusNode = focusNode.rightChild;

						// If the right child has no children

						if (focusNode == null) {
							System.out.println("Insert Right");

							// then place the new node on the right of it

							parent.rightChild = newNode;
							return; // All Done

						}

					}

				}
			}

		}

		// All nodes are visited in ascending order
		// Recursion is used to go to one node and
		// then go to its child nodes and so forth

		public void inOrderTraverseTree(Node focusNode) {

			if (focusNode != null) {

				// Traverse the left node

				inOrderTraverseTree(focusNode.leftChild);

				// Visit the currently focused on node

				System.out.println(focusNode);

				// Traverse the right node

				inOrderTraverseTree(focusNode.rightChild);

			}

		}

		public void preorderTraverseTree(Node focusNode) {

			if (focusNode != null) {

				System.out.println(focusNode);

				preorderTraverseTree(focusNode.leftChild);
				preorderTraverseTree(focusNode.rightChild);

			}

		}

		public void postOrderTraverseTree(Node focusNode) {

			if (focusNode != null) {

				postOrderTraverseTree(focusNode.leftChild);
				postOrderTraverseTree(focusNode.rightChild);

				System.out.println(focusNode);

			}

		}

		public Node findNode(int key) {

			// Start at the top of the tree

			Node focusNode = root;

			// While we haven't found the Node
			// keep looking

			while (focusNode.key != key) {

				// If we should search to the left

				if (key < focusNode.key) {

					// Shift the focus Node to the left child

					focusNode = focusNode.leftChild;

				} else {

					// Shift the focus Node to the right child

					focusNode = focusNode.rightChild;

				}

				// The node wasn't found

				if (focusNode == null)
					return null;

			}

			return focusNode;

		}
		public boolean remove(Node node) {

			// Start at the top of the tree

			Node focusNode = root;
			Node parent = root;

			// When searching for a Node this will
			// tell us whether to search to the
			// right or left

			boolean isItALeftChild = true;

			// While we haven't found the Node
			// keep looking

			while (focusNode.key != node.key) {

				parent = focusNode;

				// If we should search to the left

				if (node.key < focusNode.key) {

					isItALeftChild = true;

					// Shift the focus Node to the left child

					focusNode = focusNode.leftChild;

				} else {

					// Greater than focus node so go to the right

					isItALeftChild = false;

					// Shift the focus Node to the right child

					focusNode = focusNode.rightChild;

				}

				// The node wasn't found

				if (focusNode == null)
					return false;

			}

			// If Node doesn't have children delete it

			if (focusNode.leftChild == null && focusNode.rightChild == null) {

				// If root delete it

				if (focusNode == root)
					root = null;

				// If it was marked as a left child
				// of the parent delete it in its parent

				else if (isItALeftChild)
					parent.leftChild = null;

				// Vice versa for the right child

				else
					parent.rightChild = null;

			}

			// If no right child

			else if (focusNode.rightChild == null) {

				if (focusNode == root)
					root = focusNode.leftChild;

				// If focus Node was on the left of parent
				// move the focus Nodes left child up to the
				// parent node

				else if (isItALeftChild)
					parent.leftChild = focusNode.leftChild;

				// Vice versa for the right child

				else
					parent.rightChild = focusNode.leftChild;

			}

			// If no left child

			else if (focusNode.leftChild == null) {

				if (focusNode == root)
					root = focusNode.rightChild;

				// If focus Node was on the left of parent
				// move the focus Nodes right child up to the
				// parent node

				else if (isItALeftChild)
					parent.leftChild = focusNode.rightChild;

				// Vice versa for the left child

				else
					parent.rightChild = focusNode.rightChild;

			}

			// Two children so I need to find the deleted nodes
			// replacement

			else {

				Node replacement = getReplacementNode(focusNode);

				// If the focusNode is root replace root
				// with the replacement

				if (focusNode == root)
					root = replacement;

				// If the deleted node was a left child
				// make the replacement the left child

				else if (isItALeftChild)
					parent.leftChild = replacement;

				// Vice versa if it was a right child

				else
					parent.rightChild = replacement;

				replacement.leftChild = focusNode.leftChild;

			}

			return true;

		}

		public Node getReplacementNode(Node replacedNode) {

			Node replacementParent = replacedNode;
			Node replacement = replacedNode;

			Node focusNode = replacedNode.rightChild;

			// While there are no more left children

			while (focusNode != null) {

				replacementParent = replacement;

				replacement = focusNode;

				focusNode = focusNode.leftChild;

			}

			// If the replacement isn't the right child
			// move the replacement into the parents
			// leftChild slot and move the replaced nodes
			// right child into the replacements rightChild

			if (replacement != replacedNode.rightChild) {

				replacementParent.leftChild = replacement.rightChild;
				replacement.rightChild = replacedNode.rightChild;

			}

			return replacement;

		}

	public static void main(String[] args) {

			BinaryTree theTree = new BinaryTree();
			
			Random rnd = new Random();
			
			for(int i = 0 ; i < 100 ; i++){
				int key = rnd.nextInt(100);
				theTree.addNode(new Node(key));
			}		

			
	
			

			// Different ways to traverse binary trees
			System.out.println("In Order :");

			theTree.inOrderTraverseTree(theTree.root);
			
			System.out.println("Pre Order :");

			theTree.preorderTraverseTree(theTree.root);
			
			System.out.println("Post Order :");

			theTree.postOrderTraverseTree(theTree.root);	   

		  

		   //System.out.println("Remove Key 25");

		   //theTree.remove(32);

			// Find the node with key 75

			System.out.println("\nNode with the key 5");

			System.out.println(theTree.findNode(5));

	
	}
}	

