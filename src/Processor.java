import java.util.ArrayList;

public class Processor {

	// Lista de processos em execução
	private ArrayList<Process> processes;
	
	// Fatia de tempo de execução que cada processo tem por vez
	private int timeSlice;
	
	public Processor(int timeSlice, ArrayList<Process> processes) {
		this.timeSlice = timeSlice;
		this.processes = processes;
	}
	
	@Override
	public String toString() {
		String r = "Processes: " + this.processes.size() + "\nTimeSlice: " + this.timeSlice;
		for (int i = 0; i < this.processes.size(); i++) {
			r += "\n\nProcess " + i + ":\n" + this.processes.get(i).toString();
		}
		return r;
	}
	
}
