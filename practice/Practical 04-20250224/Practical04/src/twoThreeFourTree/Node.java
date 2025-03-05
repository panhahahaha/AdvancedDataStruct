package twoThreeFourTree;

public class Node<T extends Comparable<T>> {
	private static final int ORDER = 4; // maximum 4 children

	private Node<T> parent; // the parent node of this node.
	private int numItems = 0; // number of items stored.
	private T[] itemArray = (T[]) new Comparable[ORDER-1]; // list of items stored in this node.
	private Node<T>[] childArray = (Node<T>[]) new Node[ORDER]; // list of children of this node.


	/**
	 * find and return the item stored in this node with given value.
	 * @param value
	 * @return the item found, or null if not found.
	 */
	public T findItem(T value){
		for(T item : itemArray){
			if(item == value){
				return item;
			}
		}
		return null;
	}


	/**
	 * print the node details.
	 */
	public void displayNode(){
		System.out.print("Values: {");
		for(int i = 0; i < numItems; i++){
			System.out.print(itemArray[i]);
			if (i < numItems -1)
				System.out.print(", ");
		}

		System.out.println("}, level: {" + level() + 
				"} , IsLeaf {" + this.isLeaf() + "}");
	}


	/**
	 * get number of items.
	 * @return
	 */
	public int getNumItems(){
		return numItems;
	}


	/**
	 * remove and return the last (right-most) item in this node.
	 * @return
	 */
	public T removeLastItem(){
		T item = itemArray[numItems-1];
		itemArray[numItems-1] = null;
		numItems--;
		return item;
	}


	/**
	 * return the child at index.
	 * @param index
	 * @return
	 */
	public Node<T> getChild(int index){
		return childArray[index];
	}

	/**
	 * set the node at the index on the child list.
	 * @param index
	 * @param node
	 */
	public void setChild(int index, Node<T> node){
		childArray[index] = node;
	}

	/**
	 * get the parent node.
	 * @return
	 */
	public Node<T> getParent(){
		return parent;
	}


	/**
	 * set the parent to node.
	 * @param node
	 */
	public void setParent(Node<T> node){
		parent = node;
	}


	/**
	 * get the level of this node
	 * @return
	 */
	public int level() {
		// lowest high is set to zero
		int level = 0;

		Node<T> current = this;
		// while there is still a parent node
		while (current.parent != null) {
			current = current.parent;
			level++;
		}

		return level;
	}


	/**
	 * return if this node is a leaf node.
	 * @return
	 */
	public boolean isLeaf(){
		return childArray[0] == null;
	}


	/**
	 * return if this node is full (i.e. number of items stored is order-1)
	 * @return
	 */
	public boolean isFull(){
		return numItems == ORDER-1;
	}


	/**
	 * insert the item into this node, presuming it is a leaf node, and it is not full.
	 * @param item
	 * @return the index where the item is inserted. If the item already exists, return -1.
	 */
	public int insertItem(T item){
		//
		// CONTINUOUS ASSESSMENT TASK - Implement This Function
		// PUT YOUR UNISA USERID HERE: 
		//
		
		// go through the items stored to identify where to insert the item.
		// shift the existing items right as needed.
		// no need to update childArray as insert happens only on leaf node.
		// increase the number of items.

		// return the index of the inserted node
		return -1; // You may change/delete this line as needed.
	}


	/**
	 * return the child node to traverse next based on the value.
	 * presume the item is not present in this node.
	 * @param value
	 * @return
	 */
	public Node<T> getNextNode(T value){
		// 
		// CONTINUOUS ASSESSMENT TASK - Implement This Function
		// PUT YOUR UNISA USERID HERE: 
		//
		
		// check the item array to find the child which needs to be traversed next.
		
		// return the next child node to traverse.
		return null; // You may change/delete this line as needed.
	}



}
