import java.util.ArrayList;

public class Process {

	// Tempo de chegada do processo no processador
	int arrivalTime = 0;
	
	// Tempo que restante para executar
	int executionTime = 0;
	
	// Momentos de chamada de I/O
	ArrayList<Integer> ioTimes;
	
	public Process(int arrivalTime, int executionTime, ArrayList<Integer> ioTimes) {
		this.arrivalTime = arrivalTime;
		this.executionTime = executionTime;
		this.ioTimes = ioTimes;
	}
	
	@Override
	public String toString() {
		return "Arrival Time: " + this.arrivalTime + "\nExecution Time: " + this.executionTime + "\nI/O Times: " + this.ioTimes;
	}
	
}
