/** Node of a binary tree **/
public class Node {
	
	public Object element;
	
	public Node leftChild;
	public Node rightChild;
	
	public Node (Object object) {
		this (object, null, null);
	}
	
	public Node (Object object, Node l, Node r) {
		element = object;
		leftChild = l;
		rightChild = r;
	}
	
	public String toString() {
		return element.toString();
	}
}
