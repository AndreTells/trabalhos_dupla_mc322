package mc322.lab05;

import mc322.lab05.espacos.Dama;
import mc322.lab05.espacos.Espaco;
import mc322.lab05.espacos.Peao;
import mc322.lab05.espacos.Peca;
import mc322.lab05.espacos.Vazio;

public class Tabuleiro implements Cloneable{
	private Espaco espacos[][];
	
	Tabuleiro(){
		espacos = new Espaco[8][8];
		char tabuleiro_string[][]= new char[8][8];
		tabuleiro_string[0] ="-p-p-p-p".toCharArray();
		tabuleiro_string[1] ="p-p-p-p-".toCharArray();
		tabuleiro_string[2] ="-p-p-p-p".toCharArray();
		tabuleiro_string[3] ="--------".toCharArray();
		tabuleiro_string[4] ="--------".toCharArray();
		tabuleiro_string[5] ="b-b-b-b-".toCharArray();
		tabuleiro_string[6] ="-b-b-b-b".toCharArray();
		tabuleiro_string[7] ="b-b-b-b-".toCharArray();
		
		for(int i=0;i<espacos.length;i++) {
			for(int j =0;j<espacos[0].length; j++) {
				if(tabuleiro_string[i][j] != '-') {
					espacos[i][j] = new Peao(i,j,tabuleiro_string[i][j]);
				}
				else {
					espacos[i][j] = new Vazio(i,j);
				}
			}
		}
	}

	public String criaString() {
		String tabuleiro_string = "";
		for(int i=0;i<espacos.length;i++) {
			for(int j =0;j<espacos[0].length; j++) {
				tabuleiro_string+=espacos[i][j].pegaIcone();
			}
			tabuleiro_string+='\n';
		}
		return tabuleiro_string;
	}

	public void imprimeTabuleiro() {
		for(int i=0;i<espacos.length;i++) {
			System.out.print(8-i);
			for(int j =0;j<espacos[0].length; j++) {
				System.out.print(" "+espacos[i][j].pegaIcone());
			}
			System.out.print("\n");
		}
		System.out.println("  a b c d e f g h");
	}

	public void removePeca(int x, int y) {
		Vazio espaco_peca_comida = new Vazio(x,y);
		espacos[x][y] = espaco_peca_comida;
	}

	public Peca pegaPeca(int x, int y) {
		return (Peca)espacos[x][y];
	}
	
	public boolean espacoEhVazio(int x,int y) {
		return espacos[x][y].ehVazio();
	}
	
	public void promove_peca(Peca peca,int x,int y) {
		
		this.espacos[x][y] = new Dama(x,y,peca.pegaCor());
	}
	
	public boolean solicitaMovimento(Movimento movimento) {
		//checa se esta dentro do tabuleiro
		if(!movimento.ehDentroDoTabuleiro()) {
			return false;
		}
		
		int inicio[] = movimento.pegaPosicaoInicial();
		int destino[] = movimento.pegaPosicaoFinal();
		
		//checa se posicao inicial contem uma peca
		if(this.espacos[inicio[0]][inicio[1]].pegaIcone() != '-') {
			
			Peca peca = this.pegaPeca(inicio[0],inicio[1]);
			
			if(!peca.move(this, destino[0], destino[1])) {
				return false;
			}
			
			Vazio transferencia = new Vazio(0,0);
			transferencia.copiaPosicao(peca);
			peca.copiaPosicao(this.espacos[destino[0]][destino[1]]);
			
			this.espacos[destino[0]][destino[1]] = peca;
			this.espacos[inicio[0]][inicio[1]] = transferencia;
			
			if(peca instanceof Peao && (peca.pegaCor() == 'b'?destino[0]==0 :destino[0]==7)) {
				
				promove_peca(peca,destino[0],destino[1]);
			}
			return true;
		}
		
		else {
			return false;
		}
		
	}
}
