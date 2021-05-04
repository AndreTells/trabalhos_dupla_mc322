package mc322.lab05;

public class AppDama {
	static String[] executaJogo(String caminho_arquivo){
		CSVHandling csv = new CSVHandling();
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
			
			estados_tabuleiro[i+1] = tabuleiro.criaString();
		}
		
		return estados_tabuleiro;
	}

	static String[] formataParaArquivo(String tabuleiro_s) {
		String resultado []= new String[64];
		
		for(int j=0;j<8;j++) {
			for(int i=7;i>=0;i--) {
				
				if(tabuleiro_s.charAt(i*9 + j) != '\n') {
					
					char horizontal = (char)(j+'a');
					char vertical = (char)('8'-i);
					char icone_na_posicao = tabuleiro_s.charAt(i*9 + j) =='-' ? '_':tabuleiro_s.charAt(i*9 + j);
					
					resultado[j*8 + (7-i)] = ""+horizontal+vertical+icone_na_posicao;
					System.out.println(resultado[j*8 + (7	-i)]);
				}
			}
		}
		return resultado;
	}
	
	public static void main(String Args[]) {
		String[] tabuleiros = executaJogo(Args[0]);
		String[] tabuleiro_final_formatado = formataParaArquivo(tabuleiros[tabuleiros.length-1]);
		CSVHandling csv = new CSVHandling();
		csv.setDataExport(Args[1]);
		csv.exportState(tabuleiro_final_formatado);
	}

}
