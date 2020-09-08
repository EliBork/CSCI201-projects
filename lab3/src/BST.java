/** **************************************************************************
 *                     The  generic Binary Search Tree class.
 *
 *****************************************************************************/

import java.util.*;
import java.io.*;
import java.net.*;

public class BST <AnyType extends Comparable<AnyType>> implements Cloneable, Iterable<AnyType>
{
	private Node<AnyType> root;

/*****************************************************
*            INSERT
******************************************************/
	public void insert(AnyType data)
	   {
		if(root == null) {
			root = new Node<AnyType>(data);
			return;
		}
	      insertHelper(root, data);
	   }
	   private Node<AnyType> insertHelper(Node<AnyType> currNode, AnyType data)
	   {
	      if (currNode == null) {
	    	  currNode = new Node<AnyType>(data);
	         return currNode;
	      }
	      
	      if (currNode.data.compareTo(data) > 0) { //move left and assign child
	    	  currNode.left = insertHelper(currNode.left, data); 
	      } else {
	    	  currNode.right = insertHelper(currNode.right, data); //move right and assign child
 
	      }

	      return currNode;
	   }


/*****************************************************
*            SEARCH
******************************************************/
   public boolean search(AnyType toSearch)
   {
      return searchHelper(toSearch, root);
   }
   
   private boolean searchHelper(AnyType toSearch, Node<AnyType> node) {
	   if(node == null) {
		   return false;
	   }
	   if(node.data.compareTo(toSearch) < 0) { //move right
		   return searchHelper(toSearch, node.right);
		} else if(node.data.compareTo(toSearch) > 0){ //move left
			 return searchHelper(toSearch, node.left);
		} else { //found it
			return true;
		}
   }


/*****************************************************
*           CLONE
******************************************************/

	public BST<AnyType> clone()
	{
		BST<AnyType> newBst = new BST<AnyType>();
		newBst.root = new Node<AnyType>(root.data);
		cloneHelper(newBst.root, root);
		return newBst; // Todo: Implement. See lecture 6.
	}
	
	private void cloneHelper(Node<AnyType> assignChildren, Node<AnyType> hasChildren){
		if(hasChildren == null) {
			return;
		}
		
		if(hasChildren.left != null) {
			assignChildren.left = new Node<AnyType>(hasChildren.left.data);
			cloneHelper(assignChildren.left, hasChildren.left);
		}
		
		if(hasChildren.right != null) {
			assignChildren.right = new Node<AnyType>(hasChildren.right.data);
			cloneHelper(assignChildren.right, hasChildren.right);
		}
		
	}
	
	

/*****************************************************
*            TREE ITERATOR
******************************************************/

   public Iterator<AnyType> iterator()
   {
      return new TreeIterator();
   }
   
   private class TreeIterator implements Iterator<AnyType>{
	   Queue<Node<AnyType>> q = new LinkedList<Node<AnyType>>();
	   
	    public TreeIterator() {
		   fillQ(root);
	   }
	    
	    private  void fillQ(Node<AnyType> n) {
			if(n != null) {
				if(n.left != null) {
					fillQ(n.left);
				} 
				
				if(n.right != null) {
					q.add(n);
					fillQ(n.right);
				} else {
					q.add(n);
				}
			}
		}
	    
	    public void remove() { throw new UnsupportedOperationException(); }
	    

	    public AnyType next() {
	    	if(q.isEmpty()) {
	    		return null;
	    	}
			return q.poll().data;
		}
	    public boolean hasNext()
	      {
	         return !q.isEmpty();
	      }
   }

/*****************************************************
*            HEIGHT
******************************************************/

	// Returns the height of the tree
	public int height()
	{
		 return height(root);
	}
	private int height(Node<AnyType> p)
	{
		return (p == null)?-1: 1+Math.max(height(p.left), height(p.right));
	}

/*****************************************************
*            Node class
******************************************************/

	private class Node<AnyType>
	{
		private AnyType data;
		private Node<AnyType> left, right;

		/**
		*  Constructor builds the Node<AnyType> with the supplied parameter
		*/
		public Node(AnyType data)
		{
			left = right = null;
			this.data = data;
		}

		/**
		*  Constructor builds the Node<AnyType> with the supplied parameter
		*/
		public Node(AnyType data, Node<AnyType> l, Node<AnyType> r)
		{
			left = l; right = r;
			this.data = data;
		}

		/**
		*  Returns string representation of the object
		*/
		public String toString()
		{
			return data.toString();
		}
	}

	public static void main (String[] args )  throws IOException
	{
		BST<Integer> tree = new BST<Integer>();
		int[] ar = {6,5,1,4,3,2,9, 8};
		for (int x : ar) tree.insert(Integer.valueOf(x));
		BST<Integer> twin = (BST<Integer>) tree.clone();
		tree.insert(new Integer(201));
		for (Integer x : tree) System.out.print(x +",");
		System.out.println();
		for (Integer x : twin) System.out.print(x +",");
		System.out.println();
		System.out.println("the tree height is " + tree.height());
		URL url = new URL("https://viterbi-web.usc.edu/~adamchik/dictionary.txt");
		Scanner sc = new Scanner( url.openStream() );
		BST<String> dict = new BST<String>();
		while( sc.hasNext () ) dict.insert(sc.next());
		System.out.println(dict.search("integer"));
		System.out.println(dict.search("Integer"));
		int count = 0;
		for (@SuppressWarnings("unused") String str : dict) count++;
		System.out.println("the dictionary size is " + count);
		System.out.println("the dictionary height is " + dict.height());
	}
}
