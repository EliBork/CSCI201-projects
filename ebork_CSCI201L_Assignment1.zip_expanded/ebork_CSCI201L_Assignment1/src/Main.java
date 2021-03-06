
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonSyntaxException;

public class Main {
	/**
	 * Use user-input to create a WristCuff object.
	 */
	public static WristCuff getWristCuff() throws IOException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String fileName = scanner.nextLine();
		WristCuff wCuff = new WristCuff(fileName);
		return wCuff;
	}
	
	/**
	 * Use user-input to create a list of supported Chiral Frequencies
	 */
	public static List<Integer> getChiralFrequencies() throws IOException {		
		System.out.println("Please enter Chiral Frequencies: ");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		List<Integer> freqs = new ArrayList<Integer>();
		String[] i = s.split("[ ,]+");
		for(String str : i) {
			freqs.add(Integer.parseInt(str));
		}
		return freqs;	
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to Bridges Link.\n");
		System.out.println("Please provide timefall shelter data source: ");
		WristCuff wCuff = null;
		
		while(true) {
			try {
				 wCuff = getWristCuff();
				System.out.println("\n=== Data Received ===\n");
				break;
			} catch (FileNotFoundException e) {
				System.out.println("\nFile not found. Please re-enter data source: ");
			}catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (JsonSyntaxException e) {
				System.out.println("\nSyntax error or mismatch in data type in provided json file. Please fix errors and re-enter file name: ");
			} catch (Exception e){
				System.out.println("\nFile not found or data typos exist. Please re-enter data source + fix any errors: ");
			}
		}
		
		while(true) {
			try {
				List<Integer> freqs = getChiralFrequencies();
				Shelter s = wCuff.findShelter(freqs);
				if(s != null) {
					System.out.println("=== Compatible Shelter Found ===\n" + "Shelter information:\n");
					System.out.println(s.toString());
					System.out.println("=== Commencing Chiral Jump. Be safe, Sam. ===\n");
				} else {
					System.out.println("=== No Compatible Shelters Found. You are Doomed. === \n");
				}
				
				wCuff.save();
				break;
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (NumberFormatException e) {
				System.out.println("\nError in Chiral Frequencies. Ensure they are integers separated by a comma. Re-enter frequencies: ");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		System.exit(0);
	}

}
