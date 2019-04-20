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
	
}
