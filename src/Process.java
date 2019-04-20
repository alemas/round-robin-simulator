
public class Process {

	// Tempo de chegada do processo no processador
	int arrivalTime = 0;
	
	// Tempo que restante para executar
	int executionTime = 0;
	
	// Momentos de chamada de I/O
	int[] ioTimes = {};
	
	public Process(int arrivalTime, int executionTime, int[] ioTimes) {
		this.arrivalTime = arrivalTime;
		this.executionTime = executionTime;
		this.ioTimes = ioTimes;
	}
	
	
	
}
