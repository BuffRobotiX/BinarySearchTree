//Integer node class for a BST
class Node {
	//Data, childredn, and a parent reference for easy access.
	private int data;
	private Node parent;
	private Node left;
	private Node right;

	public Node() {
		data = 0;
		parent = null;
		left = null;
		right = null;
	}

	public Node(int newData) {
		data = newData;
		parent = null;
		left = null;
		right = null;
	}

	public Node(int newData, Node newParent) {
		data = newData;
		parent = newParent;
		left = null;
		right = null;
	}

	//Just getters and setters
	public void setData(int newData) {
		data = newData;
	}

	public void setParent(Node newParent) {
		parent = newParent;
	}

	public void setLeft(Node newLeft) {
		left = newLeft;
	}

	public void setRight(Node newRight) {
		right = newRight;
	}

	public int getData() {
		return data;
	}

	public Node getParent() {
		return parent;
	}

	public Node getLeft() {
		return left;
	}

	public Node getRight() {
		return right;
	}

}