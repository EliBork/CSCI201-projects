import java.util.ArrayList;
import java.util.Random;

import javax.swing.text.AsyncBoxView.ChildLocator;
import javax.xml.ws.AsyncHandler;

public class FastList<AnyType extends IntegerComparable>
{
	
	private static class Node<AnyType>
	{
		public AnyType data;
		public Node<AnyType>[] next;
		
		@SuppressWarnings("unchecked")
		public Node(int randLevel, AnyType data)
		{
			next = (Node<AnyType>[])new Node[randLevel + 1];
			this.data = data;
		}
	}
	
	
	Node<AnyType> head;
	public FastList(){
		head = new Node<AnyType>(0, null);
	}
	
	//each node has between one and eight next references
	public static final int MAX_LEVEL = 7;

 /**
	*  Returns the string contents of the list
	*  The method traverses the level 0 references
	*/
	public String toString()
	{
		Node<AnyType> curr = head.next[head.next.length - 1]; //go to level zero
		String s = new String();
		if(curr.next[0] == null) {
			return " ";
		}
		while(curr.next[0] != null) {
			s = s.concat(curr.next[0].toString());
		}
		
		return s;
	}

/**
	*  Returns the string contents of the list at a given level
	*  The method traverses nodes at given level
	*/
	public String toString(int level)
	{
		if(level >= head.next.length) {
			return null;
		}
		Node<AnyType> curr = head.next[head.next.length - 1 - level];
		String s = new String();
		if(curr.next[0] == null) {
			return " ";
		}
		while(curr.next[0] != null) {
			s = s.concat(curr.next[0].toString());
		}
		return s;
	}


 /**
	*  Returns true if the given value is stored in the list, otherwise false.
	*  The search begins at the topmost reference of the header
	*/
	public AnyType contains(int toSearch)
	{	
		Node<AnyType> curr = head;	
		
			while(curr.next[0] != null) { //assumes that head always points to a node if there are nodes in the list
				
				if(curr.next[0].data.compareTo(toSearch) == 0) {
					if(curr.next[0].data instanceof Shelter) {
						Shelter shelter = (Shelter)curr.next[0].data;
						if(shelter.getTimefall() == true) {
							System.out.println("=== Compatible Shelter Found ===\n" + "Shelter information:\n");
							System.out.println(shelter.toString());
							
							delete(curr.next[0].data); //if timefall is active delete that shelter
							System.out.println("\n=== Target shelter Chiral signal unstable, Chiral jump unavailable. ===\n" + 
									"=== Removing target shelter from the list of shelters and saving updated data ===\n\n");
							System.out.println("=== Commencing timefall shelter search ===\n");
							continue;
						}
					}
					System.out.println("Level " + (curr.next.length - 1) + " - Chiral frequency match found @ " + curr.next[0].data.getCompareValue() + "!\n\n");
					return curr.next[0].data;
				} else if(curr.next[0].data.compareTo(toSearch) > 0){ //if we go over the value, go down a level
					if(curr.next.length < 2) { //if we are on the bottom layer return null
	
						return null;
					} else {
						if(curr.data == null) { //if our lower bound is on 0, just manually say 0
							System.out.println("Level " + (curr.next.length - 1) + " - Chiral frequency lower bound found @ " + 0 + "\n");
							curr = curr.next[1];
							continue;
						}else { //lower bound found, move to next level and notify user
							System.out.println("Level " + (curr.next.length - 1) + " - Chiral frequency lower bound found @ " + curr.data.getCompareValue() + "\n");
							curr = curr.next[1];
							continue;
						}
						
					}
				}
				
				curr = curr.next[0];
			}
		return null;
	}

 /**
	*  Returns true if the given value is stored in the list, otherwise false.
	*  The search begins at the topmost reference of the header
	*/
	public AnyType contains(AnyType toSearch)
	{
		Node<AnyType> curr = head;	
		
			while(curr.next[0] != null) {
				
				if(curr.next[0].data.compareTo(toSearch.getCompareValue()) == 0) {
					if(curr.next[0].data.equals(toSearch)) {
						if(curr.next[0].data instanceof Shelter) { //ensure timefall is not active
							Shelter shelter = (Shelter)curr.next[0].data;
							if(shelter.getTimefall() == true) { //if timefall is active
								System.out.println("=== Compatible Shelter Found ===\n" + "Shelter information:\n");
								System.out.println(shelter.toString());
								
								delete(curr.next[0].data); //if timefall is active delete that shelter
								System.out.println("\n=== Target shelter Chiral signal unstable, Chiral jump unavailable. ===\n" + 
										"=== Removing target shelter from the list of shelters and saving updated data ===\n\n");
								System.out.println("=== Commencing timefall shelter search ===\n");
								continue;
							}
						}
						System.out.println("Level " + (curr.next.length - 1) + " - Chiral frequency match found @ " + curr.next[0].data.getCompareValue() + "!\n\n");
						return curr.next[0].data;
					}
				} else if(curr.next[0].data.compareTo(toSearch) > 0){ //if we go over the value, go down a level
					if(curr.next.length < 0) { //if we are on the bottom layer return null
						return null;
					} else {
						if(curr.data == null) { //if our lower bound is on 0, just manually say 0
							System.out.println("Level " + (curr.next.length - 1) + " - Chiral frequency lower bound found @ " + 0 + "\n");
							curr = curr.next[1];
							continue;
						}else {
							System.out.println("Level " + (curr.next.length - 1) + " - Chiral frequency lower bound found @ " + curr.data.getCompareValue() + "\n");
							curr = curr.next[1];
							continue;
						}
					}
				}
				curr = curr.next[0];
			}
	
		return null;
	}
	
	

 /**
	*  Inserts a given value into the list at random level
	*  In order to insert a new node into the list we must maintain an array
	*  of nodes whose next references must be updated.
	*/
	public void insert(AnyType toInsert)
	{
		while(true) {
			try {
				insert(toInsert, GenerateRandLevel());
				break;
			} catch (Exception e){
				System.out.println(e.getMessage());
			}
		}
		
		
		
	}
	
	private int GenerateRandLevel() { //generates level between 0 and max level based off of probability
		//ending on 0 has 1/2 chance, 1 has 1/4 chance, etc
		Random rand = new Random();
		float r = rand.nextFloat();
		if(r < 1.0f/128.0f) {
			return 7;
		} else if(r < 1.0f/64.0f) {
			return 6;
		} else if(r < 1.0f/32.0f) {
			return 5;
		} else if(r < 1.0f/16.0f) {
			return 4;
		} else if(r < 1.0f/8.0f) {
			return 3;
		} else if(r < 1.0f/4.0f) {
			return 2;
		} else if(r < 1.0f/2.0f) {
			return 1;
		} else {
			return 0;
		}
	}

 /**
	*  Inserts a given value into the list at a given level
	*  The level must not exceed MAX_LEVEL.
	*/
	public void insert(AnyType toInsert, int level) throws Exception
	{
		//make sure level is valid
		if(level > MAX_LEVEL) {
			String s = "\nAttempted to insert at a level higher than MAX_LEVEL\n";
			throw new Exception(s);
		}
		//move head up a level if we need to
		int currLevels = head.next.length - 1;
		if(level > currLevels) {
			MoveHeadUp(level, currLevels);
		}
		
		//if this level is empty, put it in this level
		Node<AnyType> newNode = new Node<AnyType>(level, toInsert);
		if(head.next[0] == null) {
			head.next[0] = newNode;
		} else { //otherwise find its spot
			Node<AnyType> curr = head;
			while(curr.next[0] != null) {
				if(curr.next[0].data.compareTo(toInsert.getCompareValue()) >= 0) {
					break;
				}
				curr = curr.next[0];
			}
			newNode.next[0] = curr.next[0];
			curr.next[0] = newNode;
		}
		
		
		if(level > 0) { //insert on lower levels and assign  children
			AssignFirstChild(newNode, toInsert, level - 1); 
			AssignAllChildren(newNode);
			
		}	
	}
	
	//inserts on lower levels and assigns parents/1st child
	private void AssignFirstChild(Node<AnyType> parent, AnyType toInsert, int level) {
		Node<AnyType>levelHead = head.next[head.next.length - level - 1]; //head for this level
		//if this level is empty, put it in this level
		Node<AnyType> newNode = new Node<AnyType>(level, toInsert);
		if(levelHead.next[0] == null) {
			levelHead.next[0] = newNode;
		} else { //otherwise find its spot
			Node<AnyType> curr = levelHead;
			while(curr.next[0] != null) {
				if(curr.next[0].data.compareTo(toInsert.getCompareValue()) >= 0) {
					break;
				}
				curr = curr.next[0];
			}
		newNode.next[0] = curr.next[0];
		curr.next[0] = newNode;
		}
		//assigns parent's first child
		parent.next[1] = newNode;
				
		if(level > 0) { //insert on lower levels
			AssignFirstChild(newNode, toInsert, level - 1); 
		}
	}
	
	private void AssignAllChildren(Node<AnyType> newNode) { //assign ALL children at all levels for node. assumes first child has been set for all nodes	
		
		if(newNode.next.length > 1) { //if we arent on level zero assign children
			while(newNode.next[1] != null) {
				if(newNode.next.length > 1) {
					Node<AnyType> child = newNode.next[1];
					int i = 2;
					if(child.next.length > 1) { //if the child isnt the bottom level
						while(child.next[1] != null && i < newNode.next.length) {
							newNode.next[i] = child.next[1];
							i++;
							if(child.next.length <= 1) { //exit if child is bottom level
								break;
							}
							child = child.next[1];
							if(child.next.length <= 1) { //exit if child is bottom level
								break;
							}
						}
						newNode = newNode.next[1]; //move down to the next child. assign its children
					} else {
						break;
					}
				} else {
					break;
				}
			}
		}
		
	}
	
	private void MoveHeadUp(int targetLevel, int currentLevel) {
		int nextLevel = currentLevel + 1; 
		Node<AnyType> newHead = new Node<AnyType>(nextLevel, null); //create head on next level, assign ALL children to new head
		newHead.next[1] = head;
		if(head.next.length > 1) { //grab head's children if head isnt level 0
			for(int i = 1; i < head.next.length; i++) {
				newHead.next[i + 1] = head.next[i]; //this assumes that the previous head's children have been set
			}
		}
		
		head = newHead;
		currentLevel ++;
		if(targetLevel > currentLevel) {
			MoveHeadUp(targetLevel, currentLevel);
		}
	}

 /**
	*  Deletes a node that contains the given value.
	*  In order to delete a node we must maintain an array
	*  of nodes whose next references must be updated.
	*/
	public void delete(AnyType toDelete)
	{
		//search for it on top level, delete if there. check if top level empty
		//move down to next level and repeat process (look out for duplicates! make sure you're deleting the same index every time)
		
		Node<AnyType> curr = head;	
		Node<AnyType> parent = null;
		
		while(curr.next[0] != null) {	
			if(curr.next[0].data.compareTo(toDelete.getCompareValue()) == 0) {
				if(curr.next[0].data.equals(toDelete)) { //we find the value. delete it then go down a level
					//ensure that value is the child of the parent on the layer above it
					if(parent != null) {
						if(parent.next[1] == curr.next[0]) { 
							Node<AnyType> tempNode = curr.next[0].next[0];
							parent = curr.next[0];
							curr.next[0] = tempNode;
							if(curr.next.length == 1) { //if we are on the bottom layer return null
								return;
							} else {
								curr = curr.next[1]; //go down next layer
								continue;
							}
						}
					} else { //if no parent then just delete and set parent
						Node<AnyType> tempNode = curr.next[0].next[0];
						parent = curr.next[0];
						curr.next[0] = tempNode;
						if(curr.next.length == 1) { //if we are on the bottom layer return null
							return;
						} else {
							curr = curr.next[1]; //go down next layer
							continue;
						}
					}
				}
			} else if(curr.next[0].data.compareTo(toDelete) > 0){ //if we go over the value, go down a level
				if(curr.next.length == 1) { //if we are on the bottom layer return null
					return;
				} else {
					curr = curr.next[1];
					continue;
				}
			}
			curr = curr.next[0];
		}
		
		
		if(head.next[0] == null) { //if top layer  is empty move it down
			while(head.next[0] == null) {
				if(head.next.length == 1) {
					break; //stop fixing head if we are on bottom layer
				}
				
				head = head.next[1];
			}
		}
		
		
	}
	
	public ArrayList<AnyType> ReturnAll(){ //returns a list of all of the nodes on the bottom row
		ArrayList<AnyType> shelterList = new ArrayList<AnyType>();
		
		Node<AnyType> currNode = head.next[head.next.length -1];
		
		if(currNode != null) {
			while(currNode.next[0] != null) {
				shelterList.add(currNode.next[0].data);
				currNode = currNode.next[0];
			}
		}
		
		
		return shelterList;
	}
	
}


