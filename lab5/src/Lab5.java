import java.util.*;

public class Lab5<A, B, C, D> {

   static Collection<Integer> makeSet()
   {
      Collection<Integer> set = new HashSet<Integer>(){{
             add(499);add(201);add(104);add(570);add(1999); add(200);add(270); add(567);add(311);}};
      return set;
   }

   public static void main(String[] args)
   {
		Lab5 lab = new Lab5();

		Collection<Terrier> coll2 = List.of(new Terrier(11), new Terrier(88), new Terrier(12));
		Collection<Dog> coll1 = List.of(new Dog(5), new Dog(8), new Dog(2), new Terrier(1));

		System.out.print("Concatenated iterable: [" );
		Iterable<Animal> itr = (Iterable<Animal>)lab.concat(coll1, coll2);
		for (Animal x : itr) System.out.print(x +", ");
		System.out.println("]");

		Map<Dog, String> test = new HashMap<>();
		test.put(new Dog(2), "poodle");
		test.put(new Dog(1), "labrador");
		test.put(new Terrier(3), "irish");
		test.put(new Dog(1), "beagle");
		test.put(new Terrier(3), "dandie");
		System.out.print("Original Map: " );
		System.out.println(test);
		Map<String, Dog> test1 = lab.reverseMap(test);
		System.out.print("Inverted Map: " );
		System.out.println(test1);

		Collection<Integer> coll = makeSet();
		Predicate<Integer> pred = i -> (i % 2 == 0);
		System.out.println("Original collection: " + coll);
		System.out.println("Count evens: " + lab.countIf(coll, pred));
		lab.remove(coll, pred);
		System.out.println("Remove evens: " + coll);
		coll = makeSet();
		System.out.print("Retain evens: [");
		for (Integer x : lab.collect(coll, pred)) System.out.print(x +", ");
		System.out.println("]");

		pred = n -> (n > 270);        // Tests if an Integer is bigger than 270.
		coll = makeSet();
		System.out.println("Original collection: " + coll);
		System.out.println("Count bigger than 270: " + lab.countIf(coll, pred));
		lab.remove(coll,pred);
		System.out.println("Remove bigger than 270: " + coll);
		coll = makeSet();
		System.out.print("Collect bigger than 270: [" );
		for (Integer x : lab.collect(coll, pred)) System.out.print(x +", ");
		System.out.println("]");

		coll1 = List.of(new Dog(5), new Dog(8), new Dog(2), new Terrier(1));
		System.out.println("Original collection: " + coll1);
		Predicate<Dog> pred1 = Dog::methodDog;
		System.out.println("Count evens: " + lab.countIf(coll1, pred1));
		Predicate<Animal> pred2 = Animal::methodAnimal;
		System.out.println("Count evens: " + lab.countIf(coll1, pred2));

   }

   private Iterable<?> concat(Iterable<?> a, Iterable<?> b) {
		
		
	ArrayList<Object> newList = new ArrayList<Object>();
	for (Object i : a) {
		newList.add(i);
	}
	
	
   return newList;
}
   
   
   private Map<B, A> reverseMap(Map<A, B> m){
	   
	   Map<B, A> newMap = (Map<B, A>) new HashMap<Object, Object>();
   for(Map.Entry<A, B> entry : m.entrySet()) {
	   newMap.put(entry.getValue(), entry.getKey());
   }
   
   return newMap;
}
   
   
   private Iterable<?> collect(Iterable<?> it, Predicate<?> pred){
	   ArrayList<?> newitIterable = (ArrayList<?>)new ArrayList<Object>();
	   for (? i : it) {
			if(pred.test(i)) {
				newitIterable.add(i);
			}
		}
	   
	   return newitIterable;
   }
   
}

interface Predicate<T>
{
	public boolean test(T obj);
}

 class Animal
{
	protected Integer value;
	public Animal(Integer value) { this.value = value; }
	public boolean methodAnimal() { return value%2 == 0; }
	public String toString() { return "Animal:" + value; }
}
 class Dog extends Animal
{
	public Dog(Integer value) { super(value); }
	public boolean methodDog() { return value%2 == 0; }
	public String toString() { return "Dog:" + value;  }
}
 class Terrier extends Dog
{
	public Terrier(Integer value) { super(value); }
	public boolean methodTerrier() { return value%2 == 0; }
	public String toString() { return "Terrier:" + value;  }
}

/* Expected output:

Concatenated iterable: [Dog:5, Dog:8, Dog:2, Terrier:1, Terrier:11, Terrier:88, Terrier:12, ]
Original Map: {Terrier:3=dandie, Dog:1=beagle, Dog:2=poodle, Dog:1=labrador, Terrier:3=irish}
Inverted Map: {dandie=Terrier:3, labrador=Dog:1, poodle=Dog:2, beagle=Dog:1, irish=Terrier:3}
Original collection: [499, 567, 311, 104, 200, 201, 570, 270, 1999]
Count evens: 4
Remove evens: [499, 567, 311, 201, 1999]
Retain evens: [104, 200, 570, 270, ]
Original collection: [499, 567, 311, 104, 200, 201, 570, 270, 1999]
Count bigger than 270: 5
Remove bigger than 270: [104, 200, 201, 270]
Collect bigger than 270: [499, 567, 311, 570, 1999, ]
Original collection: [Dog:5, Dog:8, Dog:2, Terrier:1]
Count evens: 2
Count evens: 2

*/
