//A Binary Search Tree with recursive methods.
class BST {
	private Node root;

	//Can be created with no parmaeters
	public BST() {
		root = null;
	}

	//Or with a root node
	public BST(int newRoot) {
		insert(newRoot);
	}

	//Recursive insert
	private Node insert(Node node, Node parent, int newData) {
		if (node == null) {
			return new Node(newData, parent);
		}
		else if (newData > node.getData()) {
			node.setRight(insert(node.getRight(), node, newData));
		}
		else if (newData < node.getData()) {
			node.setLeft(insert(node.getLeft(), node, newData));
		}
		else if (newData == node.getData()) {
			System.out.print(newData); //console log actvity
			System.out.println(" already exists, ignore.");
		}
		return node;
	}

	//Public wrapper for insert
	//Accepts an int and recursively inserts it in the appropriate location and returns if it did.
	public boolean insert(int newData) {
		if (findNode(root, newData) == null) { //if it already exists, ignore and return false. Really just doing this so it can return a bool.
			root = insert(root, null, newData);
			return true;
		}
		else {
			System.out.print(newData); //console log actvity
			System.out.println(" already exists, ignore.");
			return false;
		}
	}

	//Recursive preorder
	private void preorder(Node node) {
		if (node != null) {
			System.out.print(node.getData());
			System.out.print(' ');
			preorder(node.getLeft());
			preorder(node.getRight());
		}
	}

	//Public wrapper for preorder
	//Prints the data in preorder format
	public void preorder() {
		preorder(root);
	}

	//Recursive inorder
	private void inorder(Node node) {
		if (empty())
			System.out.print("The tree is empty.");
		if (node != null) {
			inorder(node.getLeft());
			System.out.print(node.getData());
			System.out.print(' ');
			inorder(node.getRight());
		}
	}

	//Public wrapper for inorder
	//Prints the data in order
	public void inorder() {
		inorder(root);
	}

	//Recursive postorder
	private void postorder(Node node) {
		if (node != null) {
			postorder(node.getLeft());
			postorder(node.getRight());
			System.out.print(node.getData());
			System.out.print(' ');
		}
	}

	//Public wrapper for postorder
	//Prints the data in post order format
	public void postorder() {
		postorder(root);
	}

	//Recursive get left most
	//Gets the left most node from the node entered
	private Node getLeftMost(Node node) {
		if (node.getLeft() != null)
			return getLeftMost(node.getLeft());
		return node;
	}

	//Recursive get right most
	//Gets the right most node from the node entered
	private Node getRightMost(Node node) {
		if (node.getRight() != null)
			return getRightMost(node.getRight());
		return node;
	}

	//Recursive delete
	private boolean delete(Node node, int data) {
		if (node == null) {
			System.out.print(data);
			System.out.println(" does not exist!");
			return false;
		}
		else if (data < node.getData()) {
			return delete(node.getLeft(), data);
		}
		else if (data > node.getData()) {
			return delete(node.getRight(), data);
		}
		else {
			if (node.getRight() == null && node.getLeft() == null) { //leaf
				if (node.getParent() == null) //is root with no children
					root = null;
				else if (node.getParent().getLeft() == node)
					node.getParent().setLeft(null);
				else
					node.getParent().setRight(null);
				return true;
			}
			else if (node.getLeft() == null) { //no right
				if (node.getParent().getLeft() == node)
					node.getParent().setLeft(node.getRight());
				else
					node.getParent().setRight(node.getRight());
				return true;
			}
			else { //theres a left
				Node temp = getRightMost(node.getLeft());
				node.setData(temp.getData());
				return delete(temp, temp.getData());
			}
		}
	}

	//Public wrapper for delete
	//Deletes the node with the inputed value and returns whether succesful or not.
	public boolean delete(int data) {
		return delete(root, data);
	}

	//Returns if the tree is empty or not.
	public boolean empty() {
		return root == null;
	}

	//Recursive find node
	//Takes in the data to find and the node to start from, returns the node or null if it is not found
	private Node findNode(Node node, int data) {
		if (node != null) {
			if (data < node.getData()) {
				if (node.getLeft() != null)
					return findNode(node.getLeft(), data);
				else
					return null;
			}
			else if (data > node.getData()) {
				if (node.getRight() != null)
					return findNode(node.getRight(), data);
				else
					return null;
			}
		}
		return node;
	}

	//Returns the data in the node that comes before the node with the inputed data or the inputed value if it is not found
	public int predecessor(int data) {
		Node node = findNode(root, data);
		if (node == null) {
			System.out.print(data);
			System.out.println(" does not exist!");
			return data;
		}
		else {
			if (node.getLeft() == null) { //even if the node doesn't have a left child, the parent could be the predecssor
				if (node.getParent() == null || node.getParent().getData() > node.getData()) { //it could only be the parent if the parent is less than the node
					System.out.print(data);
					System.out.println(" does not have a predecessor.");
					return node.getData();
				}
				else
					return node.getParent().getData();
			}
			else //if it does have a left child, the predecessor is its rightmost node
				return getRightMost(node.getLeft()).getData();
		}
	}

	//Returns the data in the node that comes after the node with the inputed data or the inputed value if it is not found
	public int successor(int data) {
		Node node = findNode(root, data);
		if (node == null) {
			System.out.print(data);
			System.out.println(" does not exist!");
			return data;
		}
		else {
			if (node.getRight() == null) { //even if the node doesn't have a right child, the parent could be the successor
				if (node.getParent() == null || node.getParent().getData() < node.getData()) { //it could only be the parent if the parent is greater than the node
					System.out.print(data);
					System.out.println(" does not have a successor.");
					return node.getData();
				}
				else
					return node.getParent().getData();
			}
			else //if it does have a right child, the predecessor is its leftmost node
				return getLeftMost(node.getRight()).getData();
		}
	}

}