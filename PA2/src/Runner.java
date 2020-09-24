import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Runner {

	public Runner() {
		
	}
	
	private static void PrintMainMenu() {
		System.out.println("What would you like to do with your database of codes? \n");
		System.out.println("     1) Get frequency of a code\n");
		System.out.println("     2) Check if a code was guessed\n");
		System.out.println("     3) Remove a code\n");
		System.out.println("     4) quit\n");
	}
	
	private static void GetFrequency(Dictionary<String> d) {
		System.out.println("\nEnter a code to check its frequency\n");
		Scanner scanner = new Scanner(System.in);
		String inputString = scanner.nextLine();
		Integer i = d.frequency(inputString);
		System.out.println("\n" + inputString + " was guessed by " + i.toString() + " teammates\n");
	}
	
	private static void CheckGuessed(Dictionary<String> d) {
		System.out.println("\nEnter a code to check its frequency\n");
		Scanner scanner = new Scanner(System.in);
		String inputString = scanner.nextLine();
		boolean b = d.contains(inputString);
		
		if(b == true) {
			System.out.println("\n" + inputString + " was guessed by a teammate\n");
		} else {
			System.out.println("\n" + inputString + " was not guessed by a teammate\n");
		}
		
	}
	
	private static void RemoveCode(Dictionary<String> d) {
		System.out.println("\nEnter a code to remove\n");
		Scanner scanner = new Scanner(System.in);
		String inputString = scanner.nextLine();
		while(d.contains(inputString) == true) {
			d.remove(inputString);
		}
		
		System.out.println("\n"+ inputString + " was removed from your database\n");
	}
	
	public static void main(String[] args)
	{
		boolean inputFinished = false;
		Dictionary<String> dict = new Dictionary<String>();
		while(!inputFinished) {
			try {
				System.out.println("Please enter your input file: ");
				Scanner scanner = new Scanner(System.in);
				String fileName = scanner.nextLine();
				BufferedReader bReader = new BufferedReader((new FileReader(fileName)));
				Gson gson = new Gson();
				CodeList[] codeLists = gson.fromJson(bReader, CodeList[].class);
				if(codeLists == null) {
					throw new JsonSyntaxException("");
				}
				
				for (CodeList cl : codeLists) {
					for (int i = 0; i < cl.codes.length; i++) {
						dict.add(cl.codes[i]);
					}
				}
				inputFinished = true;
			} catch (FileNotFoundException e) {
				System.out.println("\nFile not found. ");
			} catch (JsonSyntaxException e) {
				System.out.println("\nSyntax error or mismatch in data type in provided json file. Please fix errors.\n");
			}
		}
		
		while(true) {
			
				PrintMainMenu();
				Scanner scanner = new Scanner(System.in);
				String inputString = scanner.nextLine();
				if(inputString.compareToIgnoreCase(new String("1")) != 0 && inputString.compareToIgnoreCase(new String("2")) != 0 
						&& inputString.compareToIgnoreCase(new String("3")) != 0 && inputString.compareToIgnoreCase(new String("4")) != 0) {
					System.out.println("\nPlease enter a valid menu option.\n");
					continue;
				}
				//&& inputString != "2" && inputString != "3" && inputString != "4"
				if(inputString.compareToIgnoreCase(new String("1")) == 0) {
					GetFrequency(dict);
				} else if (inputString.compareToIgnoreCase(new String("2")) == 0) {
					CheckGuessed(dict);
				} else if (inputString.compareToIgnoreCase(new String("3")) == 0) {
					
					RemoveCode(dict);
					
				} else {
					System.out.println("\nQuitting, have a nice day!\n");
					
					break;
				}
		}	
	}
}
