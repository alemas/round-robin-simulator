

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
	public void run() {

		// Ordena os processos por ordem de chegada
		Comparator<Process> ascendingOrder = (Process p1, Process p2) -> Integer.valueOf(p1.getArrivalTime())
				.compareTo(p2.getArrivalTime());
		Collections.sort(this.processes, ascendingOrder);

		// Marca o tempo atual da execução (começa em 1)
		int currentTime = 1;

		// Flag para indicar a troca de contexto no momento atual
		boolean contextChange = false;

		// Processos prontos para executar
		ArrayList<Process> readyQueue = new ArrayList<Process>();

		// Processos bloqueados por I/O
		ArrayList<Process> blockedQueue = new ArrayList<Process>();

		// Processos que terminaram a execução
		ArrayList<Process> doneQueue = new ArrayList<Process>();

		// Gráfico mostrando os processos executados
		String resultGraph = "";

		this.currentProcess = null;

		// Enquanto todos processos não estiverem em Done
		while (doneQueue.size() != processes.size()) {
			
			// Adiciona os processos que chegaram no momento atual à fila Ready
			readyQueue.addAll(this.getUpcomingProcesses(currentTime));
			
			// Caso exista um processo rodando
			if (this.currentProcess != null) {
				
				// Faz a execuçáo de 1 unidade de tempo do processo
				this.currentProcess.remainingSliceTime--;
				this.currentProcess.remainingExecutionTime--;
				if (this.currentProcess.hasUpcomingIO()) { this.currentProcess.timeToNextIO--; }

				// Se o tempo de execução do processo termina, ele vai para a fila Done
				if (this.currentProcess.remainingExecutionTime == 0) {
					doneQueue.add(this.currentProcess);
					this.currentProcess = null;
				}
				
				// Se o tempo para o próximo I/O chega a zero, o processo passa para a fila Blocked
				if (this.currentProcess != null && this.currentProcess.timeToNextIO == 0) {
					blockedQueue.add(this.currentProcess);
					this.currentProcess = null;
				}

				// Se a fatia de tempo do processo termina, ele volta para a fila Ready
				if (this.currentProcess != null && this.currentProcess.remainingSliceTime == 0) {
					readyQueue.add(this.currentProcess);
					this.currentProcess = null;
				}
			}
			
			// Para cada processo que está em I/O, diminui uma unidade da operação
				for (int i = 0; i < blockedQueue.size(); i++) {
					Process p = blockedQueue.get(i);
					p.remainingIOTime--;
							
					// Se o tempo da operação de I/O terminou, o processo volta para a fila Ready e
					// já deixa preparada a próxima chamada de I/O (caso houver) 
					if (p.remainingIOTime == 0) {
					blockedQueue.remove(i);
					i--;
					p.prepareNextIO();
					readyQueue.add(p);
				}
			}

			// Caso não haja um processo rodando e existam processos na fila Ready ou Blocked, uma
			// troca de contexto é iniciada junto com uma posterior execução
			if (this.currentProcess == null && (!readyQueue.isEmpty()) || !blockedQueue.isEmpty()) {
				if (contextChange) {
					if (!readyQueue.isEmpty()) {
						this.setCurrentProcess(readyQueue.get(0));
						readyQueue.remove(0);
					}
					contextChange = false;
				} else {
					contextChange = true;
				}
			}

			// Escreve no gráfico de resultado o que aconteceu nesse ciclo
			if (contextChange) {
				resultGraph += "C";
			} else {
				if (this.currentProcess == null) {
					resultGraph += "-";
				} else {
					resultGraph += this.currentProcess.getId();
				}
			}
			
			currentTime++;
		}
		
		System.out.println(resultGraph);
	}

	// Método que verifica se existem processos chegando no tempo especificado
	private ArrayList<Process> getUpcomingProcesses(int time) {

		ArrayList<Process> upcomingProcesses = new ArrayList<Process>();

		for (Process p : this.processes) {
			if (p.getArrivalTime() > time) {
				return upcomingProcesses;
			}
			if (p.getArrivalTime() == time) {
				upcomingProcesses.add(p);
			}
		}
		return upcomingProcesses;
	}

	// Método que troca o processo atual executando
	private void setCurrentProcess(Process process) {

		// Atualiza a fatia de tempo do processo caso tenha chegado a zero
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
