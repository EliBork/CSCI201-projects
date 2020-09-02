import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
		
		
	}
	
	/**
	 * Finds an available shelter that matches one of the provided Chiral Frequencies
	 */
	public Shelter findShelter(List<Integer> chiralFrequencies) {
		return null;
	}
	
		
	/**
	 * Saves the updated list of shelters to disk
	 */
	public void save() throws FileNotFoundException {
		
	}
}
