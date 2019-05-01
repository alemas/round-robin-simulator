import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Processor {

	// Lista de processos
	private ArrayList<Process> processes;
	
	// Fatia de tempo de execução que cada processo tem por vez
	private int timeSlice;
	
	// Tempo que dura uma operação de I/O
	private int ioTime;
	
	// Processo executando atualmente
	Process currentProcess;
	
	public Processor(int timeSlice, ArrayList<Process> processes) {
		this.timeSlice = timeSlice;
		this.processes = processes;
	}
	
	// Método que simula o escalonador Round Robin
	public void run () {
		
		// Ordena os processos por ordem de chegada 
		Comparator<Process> ascendingOrder = (Process p1, Process p2) -> Integer.valueOf(p1.getArrivalTime()).compareTo(p2.getArrivalTime());
		Collections.sort(this.processes, ascendingOrder);
		
		// Marca o tempo atual da execução (começa em 1)
		int currentTime = 1;
		
		// Flag para indicar a troca de contexto
		boolean isChangingContext = false;
		
		// Processos prontos para executar
		ArrayList<Process> readyQueue = new ArrayList<Process>();
		
		// Processos bloqueados por I/O
		ArrayList<Process> blockedQueue = new ArrayList<Process>();
		
		// Processos que terminaram a execução
		ArrayList<Process> doneQueue = new ArrayList<Process>();
		
		this.currentProcess = null;
		
		while (true) {
			
			// Verifica se chegaram novos processos no momento atual
			readyQueue.addAll(this.getUpcomingProcesses(currentTime));
			
			if (currentProcess == null && !readyQueue.isEmpty()) {
				this.setCurrentProcess(readyQueue.get(0));
				readyQueue.remove(0);
			}
			
			
			
		}
		
	}
	
	private void changeContext() {
		
	}
	
	// Método que verifica se existem processos chegando no tempo especificado
	private ArrayList<Process> getUpcomingProcesses(int time) {
		
		ArrayList<Process> upcomingProcesses = new ArrayList<Process>();
		
		for (Process p : this.processes) {
			if (p.getArrivalTime() > time) { return upcomingProcesses; }
			if (p.getArrivalTime() == time) { upcomingProcesses.add(p); }
		}
		return upcomingProcesses;
	}
	
	// Método que troca o processo atual executando
	private void setCurrentProcess(Process process) {

		if (process.remainingSliceTime == 0) {
			process.remainingSliceTime = this.timeSlice;
		}
		this.currentProcess = process;
	}
	
	// Método para facilitar debugging
	@Override
	public String toString() {
		String r = "Processes: " + this.processes.size() + "\nTimeSlice: " + this.timeSlice;
		for (int i = 0; i < this.processes.size(); i++) {
			r += "\n\n" + this.processes.get(i).toString();
		}
		return r;
	}
	
}
