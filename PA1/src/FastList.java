public class FastList<AnyType extends IntegerComparable>
{
	//each node has between one and eight next references
	public static final int MAX_LEVEL = 7;

 /**
	*  Returns the string contents of the list
	*  The method traverses the level 0 references
	*/
	public String toString()
	{
		return null;
	}

/**
	*  Returns the string contents of the list at a given level
	*  The method traverses nodes at given level
	*/
	public String toString(int level)
	{
		return null;
	}


 /**
	*  Returns true if the given value is stored in the list, otherwise false.
	*  The search begins at the topmost reference of the header
	*/
	public AnyType contains(int toSearch)
	{
		return null;
	}

 /**
	*  Returns true if the given value is stored in the list, otherwise false.
	*  The search begins at the topmost reference of the header
	*/
	public AnyType contains(AnyType toSearch)
	{
		return null
	}

 /**
	*  Inserts a given value into the list at random level
	*  In order to insert a new node into the list we must maintain an array
	*  of nodes whose next references must be updated.
	*/
	public void insert(AnyType toInsert)
	{
		
	}

 /**
	*  Inserts a given value into the list at a given level
	*  The level must not exceed MAX_LEVEL.
	*/
	public void insert(AnyType toInsert, int level)
	{

	}

 /**
	*  Deletes a node that contains the given value.
	*  In order to delete a node we must maintain an array
	*  of nodes whose next references must be updated.
	*/
	public void delete(AnyType toDelete)
	{
	
	}
	
}


