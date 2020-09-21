import java.util.Arrays;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 ******************************************************************************
 *                    HOMAnyTypeWORK-2, cs201
 ******************************************************************************
 *                    Amortized Dictionary
 ******************************************************************************
 *
 * Implementation of an Amortized Array-Based Dictionary data structure.
 *
 * This data structure supports duplicates and does *NOT* support storage of
 * null references.
 *
 * Notes:
 * 		-It is *highly* recommended that you begin by reading over all the methods,
 *       all the comments, and all the code that has already been written for you.
 *
 * 		-the specifications provided are to help you understand what the methods
 *       are supposed to accomplish.
 * 		-they are *NOT* descriptions for how you should implement the methods.
 * 		-See the lab documentation & lecture notes for implementation details.
 *
 * 		-Some of the helper methods specify a runtime cost; make sure your
 *       implementation meets that requirement.
 * 		-(Also, obviously, if the lecture notes and/or the lab documentation specifies
 *       a runtime cost for a method, you need to pay attention to that).
 *
 *
 *****************************************************************************/



public class Dictionary<AnyType extends Comparable<AnyType>>  implements DictionaryInterface<AnyType>
{
	/*
	 * Keeps track of the number of elements in the dictionary.
	 * Take a look at the implementation of size()
	 */
	private int size;
	/*
	 * The head reference to the linked list of Nodes.
	 * Take a look at the Node class.
	 */
	private Node head;

	/**
	 * Creates an empty dictionary.
	 */
	public Dictionary()
	{
		size = 0;
		head = null;
	}

	/**
	 * Adds e to the dictionary, thus making contains(e) true.
	 * Increments size so as to ensure size() is correct.
	 */
	public void add(AnyType e)
	{
		if(e == null)
		{
			return;
		}

		size++;
		if(head == null) {
			Comparable<AnyType>[] cArr = new Comparable[1];
			cArr[0] = e;
			head = new Node(0, cArr, null);
			return;
		}
		Node node = head;
		Comparable[] cArr = new Comparable[1];
		cArr[0] = e;
		head = new Node(0, cArr, node);
		mergeDown();
	}

	/**
	 * Removes e from the dictionary.  If contains(e) was formerly false,
	 * it is still false.
	 * Otherwise, decrements size so as to ensure size() is correct.
	 */
	public void remove(AnyType e)
	{
		if(e == null)
		{
			return;
		}

		if(!contains(e)) {
			return;
		}
		
		Node node = head;
		Node prevNode = null;
		int index = 0;
		while(node != null) {
			 index = node.indexOf(e);
			if(index == -1) { //if this array doesnt have e then move on
				prevNode = node;
				node = node.next;
			} else {
				break;
			}
		}
		
		
		//remove node from dictionary
		if(prevNode != null) {
			prevNode.next = node.next;
		} else {
			head = node.next;
		}
		size--;
		
		
		//remove e from the nodes array
		if(node.array.length == 1) { //if the length is 1 then just exit since we cant split it
			return;
		}
		Comparable[] newArr = new Comparable[node.array.length - 1];
		for(int i = 0, j = 0; i < node.array.length; i++) {
			if(i == index) {
				continue;
			}
			newArr[j] = node.array[i];
			j++;
		}
		node.array = newArr;
		
		//get queue from node
		java.util.Queue<Comparable[]> q = splitUp(node.array, node.power);
		for(int i = 0; i < q.size(); i++) { //go through each array in the q, make a node from it, then find that nodes spot
			if(q.isEmpty() == true) { 
				break;
			}
			Node newNode = new Node(i, q.poll(), null);
			PlaceNode(newNode);
		}
		
	}
	
	private void PlaceNode(Node n) { //finds this nodes spot then merges if needed
		Node currNode = head;
		if(currNode == null) {
			head = n;
			return;
		}
		
		Node prevNode = null;
		while(currNode != null) {
			if(currNode.power == n.power) { //if we find one that matches power, just insert in front then mergedown
				n.next = currNode.next;
				currNode.next = n;
				mergeDown();
				return;
			} else if( currNode.power < n.power) { //if its less than the new power just move ahead
				prevNode = currNode;
				currNode = currNode.next;
				continue;
			} else if(currNode.power > n.power) { //if its greater than new power put it right behind
				if(prevNode == null) {
					head = n;
					head.next = currNode;
					return;
				} else {
					prevNode.next = n;
					n.next = currNode;
					return;
				}
			}
		}
		
		//if we get to the end of the dictionary and the new power is still too big, just add it onto the end
		prevNode.next = n;
	}

	/**
	 * Returns true iff the dictionary contains an element equal to e.
	 */
	public boolean contains(AnyType e)
	{
		if(e == null)
		{
			return false;
		}

		Node node = head;
		while(node != null) {
			if(node.contains(e) == true) {
				return true;
			} else {
				node = node.next;
			}
		}
		return false;

		
	}

	/**
	 * Returns the number of elements in the dictionary equal to e.
	 * This is logically equivalent to the number of times remove(e) needs to be performed
	 * in order for contains(e) to be false.
	 */
	public int frequency(AnyType e)
	{
		if(e == null)
		{
			return 0;
		}

		int freq = 0;
		Node node = head;
		while(node != null) {
			freq += node.frequency(e);
			node = node.next;
		}

		return freq;
	}

	/**
	 * Returns the size of the dictionary.
	 */
	public int size()
	{
		return size;
	}

	/**
	 * Combines with the other AAD using the algorithm discussed in lecture.
	 *
	 * Formally, the following need to be true after combining an AAD with another AAD:
	 * 		-the resulting dictionary contains an item iff it was contained in either of the two dictionaries
	 * 		-the resulting frequency of any item is the sum of its frequency in the two dictionaries
	 * 		-the resulting size is the sum of the two sizes
	 */
	public void combine(Dictionary<AnyType> other)
	{
		if(other == null || this == other)
		{
			return;
		}

		size = this.size + other.size;
		java.util.Queue<Node> q = new java.util.LinkedList<Node>();
		Node otherHead = other.head;
		while(otherHead != null) { //put all the nodes of other into a q
			q.add(otherHead);
			otherHead = otherHead.next;	
		}
		
		//find the right spot for each node of other
		for (Node node : q) {
			PlaceNode(node);
		}

	}

	/**
	 * Returns a helpful string representation of the dictionary.
	 */
	public String toString()
	{
		String s = new String("");
		Node node = head;
		while(node != null) {
			s = s.concat(node.power + ": " + node.toString() + "\n");
			node = node.next;
		}
		return s;
	}


	/**
	 * Starting with the smallest array, mergeDown() merges arrays of the same size together until
	 * all the arrays have different size.
	 *
	 * This is very useful for implementing add(e)!!!  See the lecture notes for the theory behind this.
	 */
	private void mergeDown()
	{
		//this assumes powers are consistent with base 2 and we always merge two nodes that have same size arrays
		//also assumes there is only one possible node that is of the same size as another
		mergeDownHelper(head);
	}
	
	private void mergeDownHelper(Node n) {
		if(n == null || n.next == null) {
			return;
		}
	
		if(n.array.length == n.next.array.length) {
			n.array = merge(n.array, n.next.array);
			n.power += 1;
			n.next = n.next.next;
			mergeDownHelper(n);
		} else {
			mergeDownHelper(n.next);
		}
		
		
	}

	/**
	 * Assumes a is sorted.
	 *
	 * contains(a, item) 	= -1, if there is no element of a equal to item
	 * 						= k, otherwise, where a[k] is equal to item
	 *
	 * This is needed for Node's indexOf(e)
	 *
	 * O(log(a.length))
	 */
	@SuppressWarnings("unchecked")
	public static int binarySearch(Comparable[] a, Comparable item)
	{
		int result = Arrays.binarySearch(a, item);
		if(result >= 0) {
			return result; //it was found, returns index
		} else {
			return -1;
		}
	}

	/**
	 * Assumes a is sorted.
	 *
	 * Returns the number of elements of a equal to item.
	 *
	 * This is needed for Node's frequency(e).
	 *
	 * O(log(a.length) + frequency(item))
	 */
	@SuppressWarnings("unchecked")
	public static int frequency(Comparable[] a, Comparable item)
	{
		int index = binarySearch(a, item);
		if(index == -1) {
			return 0;
		}
		int freq = 1;
		
		//parse the left side of the found index
		for(int i = index - 1; i >= 0; i--) {
			if(a[i].compareTo(item) == 0) {
				freq++;
			} else if(a[i].compareTo(item) < 0) { //exit this search if we pass where the item could be
				break;
			}
		}
		
		//parse the right side of the found index
		for(int i = index + 1; i < a.length; i++) {
			if(a[i].compareTo(item) == 0) {
				freq++;
			} else if(a[i].compareTo(item) > 0) { //exit this search if we pass where the item could be
				break;
			}
		}
		
		return freq;
	}

	/**
	 * When a and b are sorted arrays, merge(a,b) returns a sorted array
	 * that has length (a.length+b.length) than contains the elements
	 * of a and the elements of b.
	 *
	 * This is useful for implementing the mergeDown() method.
	 *
	 * O(a.length + b.length)
	 */
	@SuppressWarnings("unchecked")
	public static Comparable[] merge(Comparable[] a, Comparable[] b)
	{
		Comparable[] newArr = new Comparable[a.length + b.length];
		
		int indexA = 0;
		int indexB = 0;
		for(int indexNew = 0; indexNew < newArr.length; indexNew++) {
			if(indexA >= a.length) { //if we go over bounds of a or b just add the next element from the remaining array
				newArr[indexNew] = b[indexB];
				indexB++;
				continue;
			} else if(indexB >= b.length) {
				newArr[indexNew] = a[indexA];
				indexA++;
				continue;
			}
			
			//if both indexes are within bounds, check and see which has the smallest next element and add that
			int compared = a[indexA].compareTo(b[indexB]);
			if(compared == 0) { //if they are the same just insert from a
				newArr[indexNew] = a[indexA];
				indexA++;
			} else if(compared < 0) { //if a is lesser insert from a
				newArr[indexNew] = a[indexA];
				indexA++;
			} else {  //if b is lesser insert from b
				newArr[indexNew] = b[indexB];
				indexB++;
			}
		}
		
		return newArr;
	}

	/**
	 * Returns base^exponent.  This is useful for implementing splitUp(a,k)
	 */
	private static int power(int base, int exponent)
	{
		return (int)(Math.pow(base, exponent));
	}

	/**
	 * Assumes a.length >= 2^k - 1, for the given k.
	 *
	 * Splits the first (2^k -1) elements of a up into k-1 sorted arrays of
	 * length 2^(k-1), 2^(k-2), ..., 2, 1.
	 * Returns a Queue of these arrays (in the above order, i.e. the one with
	 * length 2^(k-1) is at the front).
	 *
	 * This is useful for implementing remove(e) using the algorithm discussed in class.
	 *
	 * O(a.length)
	 */
	@SuppressWarnings("unchecked")
	public static java.util.Queue<Comparable[]> splitUp(Comparable[] a, int k)
	{
		/*
		 * We'll just use a LinkedList as a Queue in this fashion.  Take a look at the
		 * API for the java.util.Queue interface.
		 */

		java.util.Queue<Comparable[]> q = new java.util.LinkedList<Comparable[]>();
		
		
		int startIndex = 0;
		for(int i = 0; i < k; i++) {
			Comparable[] newArr = new Comparable[power(2, i)];
			System.arraycopy(a, startIndex, newArr, 0, power(2, i));
			startIndex += power(2, i);
			q.add(newArr);
		}

		return q;
	}

	/**
	 * Implementation of the underlying array-based data structure.
	 *
	 * AnyTypeach Node:
	 * 			-knows k, its "power"
	 * 			-has myArray, a sorted array of 2^k elements
	 * 			-knows myNext, the next Node in the linked list of Nodes
	 *
	 * You do *NOT* need to change this class.
	 * It is, however, very important that you understand how it works.
	 * You may add additional methods, although you have been provided with sufficient
	 * functionality needed to implement the dictionary.
	 */
	@SuppressWarnings("unchecked")
	private static class Node
	{
		private int power;
		private Comparable[] array;
		private Node next;

		/**
		 * Creates an Node with the specified parameters.
		 */
		public Node(int power, Comparable[] array, Node next)
		{
			this.power = power;
			this.array = array;
			this.next = next;
		}

		/**
		 * Returns 	-1, if there is no element in the array equal to e
		 * 			 k, otherwise, where array[k] is equal to e
		 */
		public int indexOf(Comparable e)
		{
			return Dictionary.binarySearch(array, e);
		}

		/**
		 * Returns	true, if there is an element in the array equal to e
		 * 			false, otherwise
		 */
		public boolean contains(Comparable e)
		{
			return indexOf(e) > -1;
		}

		/**
		 * Returns the number of elements in the array equal to e
		 */
		public int frequency(Comparable e)
		{
			return Dictionary.frequency(array, e);
		}

		/**
		 * Returns a useful representation of this Node.  (Note how this is used by Dictionary's toString()).
		 */
		public String toString()
		{
			String string = new String("[");
			string = string.concat(array[0].toString());
			for(int i = 1; i < array.length; i++) {
				string = string.concat(", " + array[i].toString());
			}
			string = string.concat("]");
			return string;
			
		}
	}

	
	/*
	 * String string = new String("[");
			string = string.concat(array[0]);
			for(int i = 1; i < array.length; i++) {
				string = string.concat(", " + array[i]);
			}
			string = string.concat("]");
			return string;
	 */
}


