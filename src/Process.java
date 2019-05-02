/* 	Autores: Mateus Reckziegel e Fabricio Pujol
*	Data: 09/04/19
*	
*	Classe responsável por representar a abstração de um processo 
*	junto com todos atributos necessário para executar no
*	processador
*/

import java.util.ArrayList;

public class Process {

	// Identificador do processo
	private int id;
	
	// Tempo de chegada do processo no processador
	private int arrivalTime = 0;

	// Tempo total para a execução desse processo
	private int executionTime = 0;
	
	// Tempo que demora uma operação de I/O
	private int ioDuration;
	
	// Tempo restante para terminar a execução
	int remainingExecutionTime;
	
	// Tempo restante da fatia atual de tempo
	int remainingSliceTime = 0;
	
	// Tempo restante para iniciar a próxima operação de I/O
	int timeToNextIO = -1;
	
	// Tempo restante para a operação de I/O atual
	int remainingIOTime = 0;
	
	// Momentos de chamada de operações I/O
	ArrayList<Integer> ioTimes;
	
	public Process(int id, int arrivalTime, int executionTime, ArrayList<Integer> ioTimes, int ioDuration) {
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.executionTime = executionTime;
		this.remainingExecutionTime = this.executionTime;
		this.ioTimes = ioTimes;
		this.ioDuration = ioDuration;
		this.prepareNextIO();
	}

	// Prepara a próxima chamada de I/O
	public void prepareNextIO() {
		if (!ioTimes.isEmpty()) {
			this.timeToNextIO = ioTimes.get(0);
			ioTimes.remove(0);
			this.remainingIOTime = this.ioDuration;
		} else {
			// Caso não hajam mais chamadas de I/O pela frente, o tempo restante para a
			// próxima chamada passa a ser -1
			this.timeToNextIO = -1;
		}
	}
	
	// Indica se haverá outra chamamda de I/O no futuro
	public boolean hasUpcomingIO() {
		return !(this.timeToNextIO == -1);
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
