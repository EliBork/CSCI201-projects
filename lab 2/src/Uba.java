import java.lang.reflect.Array;

/*
 * Lab02.
 *
 *
*  Adding extra helper functions is allowed and would be a good idea.
 * Extra class variables can be added but are not required to solve this lab.
 *
 * It's always a good idea to split your logic into multiple functions since each
 * function should ideally do one thing and do it well.
 *
 * Good luck! Have fun! (If you're not having fun ask CPs for help :-)
*/


public class Uba<AnyType>  implements UbaInterface<AnyType>
{
    private static final int DEFAULT_CAPACITY = 5;

    private AnyType[] items;
    private int size; // the current number of elements in the array
    private int index; //what index of the array the last element is 

  /**
	*  Constructs an empty array of default capacity
	*/
	@SuppressWarnings("unchecked")
	public Uba()
	{
        index = 0;
        size = DEFAULT_CAPACITY;
        items = (AnyType[])new Object[DEFAULT_CAPACITY];
	}


  /**
	*  Adds an item to this collection, at the end.
	*/
	public void add(AnyType x)
	{
		
        if(index < size) {
        	items[index] = x;
        } else {
        	IncreaseSize();
        	items[index] = x;
        }
        index++;
		
	}
	
	@SuppressWarnings("unchecked")
	private void IncreaseSize() { //increases size by factor of 2
		AnyType[] newArr = (AnyType[])new Object[size];
		System.arraycopy(items, 0, newArr, 0, size);
		size *= 2;
		items = (AnyType[])new Object[size];
		for(int i = 0; i < newArr.length; i++) {
			items[i] = newArr[i];
		}
	}

  /**
	*   Removes the last item from the list and returns it
	*/
	@SuppressWarnings("unchecked")
	public AnyType remove()
	{
        index--;
        AnyType obj = items [index];
        AnyType[] newArr = (AnyType[])new Object[size];
    	
        for(int i = 0; i < items.length - 1; i++) {
        	newArr[i] = items[i];
        }
        items = newArr;
        return obj;
	}


  /**
	*  Returns a string representation
	*/
	@Override
	public String toString( )
	{
       String output = new String("[");
       if(items[0] != null) {
    	   for(int i = 0; i < index; i++) {
        	   String s = items[i].toString();
        	   output= output.concat(s);
        	   if(i != index - 1) {
        		  output = output.concat(", "); 
        	   }
           }  
       }
       
       
       output = output.concat(new String("]"));
        return output;
	}

	public static void main(String[] args)
	{
		Uba<Integer> tmp = new Uba<Integer> ();
		System.out.println(tmp);

		for(int i = 0; i < 50; i++) tmp.add(i);
		System.out.println(tmp);

		System.out.println(tmp.remove());
		System.out.println(tmp);

		Uba< String > tmp1 = new Uba<String> ();
		for(int i = 0; i < 6; i++) tmp1.add("uba" + i);
		System.out.println(tmp1);

		System.out.println(tmp1.remove());
		System.out.println(tmp1);

	}

}