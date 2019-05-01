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
			int lineIndex = 0;
			while(scanner.hasNextLine()) {

				line = scanner.nextLine();
				String[] stringValues = line.split(" ");

				switch(lineIndex) {
				case 0: break;
				case 1:
					timeSlice = Integer.parseInt(stringValues[0]);
					break;
				default:

					int arrivalTime = Integer.parseInt(stringValues[0]);
					int executionTime = Integer.parseInt(stringValues[1]);
					ArrayList<Integer> ioTimes = new ArrayList<Integer>();
					
					for (int i = 2; i < stringValues.length; i++) {
						 ioTimes.add(Integer.parseInt(stringValues[i]));
					}
					
					processes.add(new Process(lineIndex-1, arrivalTime, executionTime, ioTimes, 3));
				}
				
				lineIndex++;
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return new Processor(timeSlice, processes);
	}

}
