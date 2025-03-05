package twoThreeFourTree;

import java.util.concurrent.ArrayBlockingQueue;

public class TwoThreeFourTree<T extends Comparable<T>> {

	// the root node
	Node<T> root = new Node<T>();

	/**
	 * find the item with the given value.
	 * @param value
	 * @return
	 */
	public T find(T value){
		Node<T> node = root;
		while(node != null){
			T item = node.findItem(value);
			if(item == null){
				node = node.getNextNode(value);
			}else{
				return item;
			}
		}
		return null;
	}


	/**
	 * insert the item into the tree.
	 * @param item
	 * @return false if item already exists, true if successfully inserted.
	 */
	public boolean insert(T item){
		// 
		// CONTINUOUS ASSESSMENT TASK - Implement This Function
		// PUT YOUR UNISA USERID HERE:
		//

		// starting from the root, traverse the tree down to the leaf node.
		// if a full node is encountered, split before continue traversing.
		// after split, be careful of which node to traverse next.
		// if a leaf node is reached, stop traversing.
		// insert the item to the found leaf node.

		// return true if successfully inserted, false if already exists.
		return false; // You may change/delete this line as needed.
	}


	/**
	 * split the node assuming it is full (i.e. it is a 4-node).
	 * @param node
	 */
	public void split(Node<T> node){
		// split out and create a new right sibling node.
		Node<T> child2 = node.getChild(2);
		Node<T> child3 = node.getChild(3);
		T newRightNodeItem = node.removeLastItem();

		Node<T> newRightNode = new Node<T>();
		newRightNode.insertItem(newRightNodeItem);
		newRightNode.setChild(0, child2);
		newRightNode.setChild(1, child3);
		if(child2 != null)
			child2.setParent(newRightNode);
		if(child3 != null)
			child3.setParent(newRightNode);

		// split out a new parent node which has children of the current node and the new right sibling node.
		T newParentItem = node.removeLastItem();
		if(node == root) {
			// when splitting the root node, grow the tree up by creating a new root node.
			root = new Node<T>();
			root.insertItem(newParentItem);
			root.setChild(0, node);
			root.setChild(1, newRightNode);
			node.setParent(root);
			newRightNode.setParent(root);
		} else {
			// when splitting a non-root node, push the middle item up to existing parent node.
			Node<T> parent = node.getParent();
			int loc = parent.insertItem(newParentItem);
			int i;
			for(i = parent.getNumItems(); i > loc + 1; i--){
				parent.setChild(i, parent.getChild(i-1));
			}
			parent.setChild(i, newRightNode);
			newRightNode.setParent(parent);
		}
	}


	/**
	 * display the nodes by traversing the tree in breadth-first manner. 
	 * @throws InterruptedException
	 */
	public void levelTraverse() {
		if(root == null)
			return;

		// Use a queue to perform breadth-first traversal of the tree.
		ArrayBlockingQueue<Node<T>> q = new ArrayBlockingQueue<Node<T>>(20);
		try {
			q.put(root); // start from the root

			while(!q.isEmpty()){
				Node<T> node = q.poll(); // retrieve the next node in the queue

				node.displayNode(); // print the node details

				// add children nodes to the queue
				if(!node.isLeaf()){
					for(int i = 0; i <= node.getNumItems(); i++){
						q.put(node.getChild(i));
					}
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
