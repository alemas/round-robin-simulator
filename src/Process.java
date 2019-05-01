import java.util.ArrayList;

public class Process {

	// Identificador do processo
	private int id;
	
	// Tempo de chegada do processo no processador
	private int arrivalTime = 0;

	// Tempo total para a execução desse processo
	private int executionTime = 0;
	
	// Tempo restante para terminar a execução
	int remainingExecutionTime;
	
	// Tempo restando da fatia atual de tempo
	int remainingSliceTime = 0;
	
	// Momentos de chamada de I/O
	ArrayList<Integer> ioTimes;
	
	public Process(int id, int arrivalTime, int executionTime, ArrayList<Integer> ioTimes) {
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.executionTime = executionTime;
		this.remainingExecutionTime = this.executionTime;
		this.ioTimes = ioTimes;
	}
	
	public int getId() {
		return id;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public int getExecutionTime() {
		return executionTime;
	}
	
	@Override
	public String toString() {
		return "Process Id: " + this.id + "Arrival Time: " + this.arrivalTime + "\nExecution Time: " + this.executionTime + "\nI/O Times: " + this.ioTimes;
	}
	
}
