import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader {
	
	static public Processor readFile(String path) {
		int timeSlice = 0;
		ArrayList<Process> processes = new ArrayList<Process>();
		
		try {
			Scanner scanner = new Scanner(new File(path));
			String line = "";
			int index = 0;
			while(scanner.hasNextLine()) {
				
				line = scanner.nextLine();
				String[] stringValues = line.split(" ");
				for (int i = 0; i < stringValues.length; i++) {
					
					int value = Integer.parseInt(stringValues[i]);
					
					switch(index) {
					case 1:
						timeSlice = 
					}
					
				}
				
				
				
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return new Processor(timeSlice, processes);
	}
	
}
