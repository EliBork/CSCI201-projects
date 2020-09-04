import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

public class WristCuff {	
	String path;
	FastList<Shelter> fList; 
	
	/**
	 * Loads shelters specified in path (JSON file)
	 */
	public WristCuff(String path) throws IOException {
		this.path = path;
	
		Scanner infile = new Scanner(new File(path));
		fList = new FastList<Shelter>();
		Gson gson = new Gson();
		Shelter[] shelters = gson.fromJson(infile.toString(), Shelter[].class);
		for(Shelter s : shelters){
			if(s.getTimefall() != null && s.getChiralFrequency() != null && s.getGuid() != null &&
					s.getName() != null && s.getPhone() != null && s.getAddress() != null) {
				//this shelter has the correct data
			} else {
				String message = "Shelter missing in provided json file. Shelter information:\n" + s.toString() +  "\nPlease fix all missing data and re-enter file name: ";
				throw new IOException(message);	
			}
		}
		
		while(true) {
			try {
				for(Shelter s : shelters) {
					fList.insert(s);
				}
				break;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.exit(-1);
			}
		}
		
		
	}
	
	/**
	 * Finds an available shelter that matches one of the provided Chiral Frequencies
	 */
	public Shelter findShelter(List<Integer> chiralFrequencies) {
		Shelter s;
		for(int i = 0; i < chiralFrequencies.size(); i++) {
			s = fList.contains(chiralFrequencies.get(i));
			if(s != null) {
				return s;
				//TODO: output statements for finding something 
			}
		}
		
		return null;
	}
	
		
	/**
	 * Saves the updated list of shelters to disk
	 */
	public void save() throws FileNotFoundException, IOException {
		ArrayList<Shelter> s = fList.ReturnAll(); //get updated list
		Gson gson = new Gson();
		PrintWriter outfilePrintWriter = new PrintWriter(new BufferedWriter(new FileWriter(path)));
		gson.toJson(s, outfilePrintWriter);
	}
}
