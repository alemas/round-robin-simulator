/* 	Autores: Mateus Reckziegel e Fabricio Pujol
*	Data: 09/04/19
*	
*	Classe responsável pela execução do algoritmo
*/


public class App {

	public static void main(String[] args) {
		
//		System.out.println(FileReader.readFile("data/1"));
		Processor p = FileReader.readFile("data/1");
		p.run();
		
	}

}
