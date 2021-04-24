package mc322.lab05;

public class AppDama {
	static String[] executaJogo(String caminho_arquivo){
		CSVReader csv = new CSVReader();
 		csv.setDataSource(caminho_arquivo);
		String movimentos_string [] = csv.requestCommands();
		
		Tabuleiro tabuleiro = new Tabuleiro();
		System.out.println("Tabuleiro inicial:");
		tabuleiro.imprimeTabuleiro();
		System.out.print("\n");
		
		String estados_tabuleiro[] = new String[movimentos_string.length+1];
		estados_tabuleiro[0] = tabuleiro.criaString();
		
		for(int i=0;i<movimentos_string.length;i++) {
			if(!tabuleiro.movePeca(new Movimento(movimentos_string[i]))) {
				estados_tabuleiro[i+1] = "erro";
				break;
			}
			
			System.out.println("Posicao inicial: "+movimentos_string[i].substring(0,2));
			System.out.println("Posicao final: "+movimentos_string[i].substring(3));
		
			tabuleiro.imprimeTabuleiro();
			System.out.print("\n");
		}
		
		return estados_tabuleiro;
	}

	public static void main(String Args[]) {
		Tabuleiro t = new Tabuleiro();
		t.iniciaTabuleiroTeste();
		t.imprimeTabuleiro();
		System.out.println("\n"+t.cor_atual);
		System.out.println("\n-----------------------------\n");
		t.movePeca(new Movimento("a8:c6"));
		t.imprimeTabuleiro();
		System.out.println("\n"+t.cor_atual);
	}

}
