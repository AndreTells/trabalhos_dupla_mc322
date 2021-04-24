package mc322.lab05;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Tabuleiro implements Cloneable{
	Espaco espacos[][];
	int num_pecas_brancas;
	int num_pecas_pretas;
	char cor_atual;
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
					espacos[i][j] = new Peca(i,j,tabuleiro_string[i][j]);
				}
				else {
					espacos[i][j] = new Vazio(i,j);
				}
			}
		}

		num_pecas_brancas = 12;
		num_pecas_pretas = 12;
		cor_atual = 'b';
	}

	void iniciaTabuleiroTeste() {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				espacos[i][j] = new Vazio(i,j);
			}
		}
		Dama d1 = new Dama(0,0,'b');
		Dama d2 = new Dama(0,0,'p');
		Dama d3 = new Dama(0,0,'p');
		Dama d4 = new Dama(0,0,'p');
		
		d1.copiaPosicao(espacos[0][0]);
		espacos[0][0] = d1;
		
		d2.copiaPosicao(espacos[1][1]);
		espacos[1][1] = d2;
		
		d3.copiaPosicao(espacos[3][3]);
		espacos[3][3] = d3;
		
		d4.copiaPosicao(espacos[5][3]);
		espacos[5][3] = d4;
	}
	
	public String criaString() {
		String tabuleiro_string = "";
		for(int i=0;i<espacos.length;i++) {
			for(int j =0;j<espacos[0].length; j++) {
				tabuleiro_string+=espacos[i][j].icone;
			}
			tabuleiro_string+='\n';
		}
		return tabuleiro_string;
	}

	public void imprimeTabuleiro() {
		for(int i=0;i<espacos.length;i++) {
			System.out.print(8-i);
			for(int j =0;j<espacos[0].length; j++) {
				System.out.print(" "+espacos[i][j].icone);
			}
			System.out.print("\n");
		}
		System.out.println("  a b c d e f g h");
	}

	private boolean ehCor(Espaco peca,char cor) {
		return ((peca.icone == 'P' || peca.icone == 'p') ? 'p':'b') == cor;
	}
	
	
	public boolean movePeca(Movimento movimento) {
		//checa se esta dentro do tabuleiro
		if(!movimento.ehDentroDoTabuleiro()) {
			System.out.println("movimento ilegal(movimento fora do tabuleiro)");
			return false;
		}
		
		//checa se posicao inicial contem uma peca
		if(this.espacos[movimento.xi][movimento.yi].icone != '-') {
			//checa se a peca eh do jogador atual
			if(!this.ehCor(this.espacos[movimento.xi][movimento.yi],this.cor_atual)) {
				System.out.println("movimento ilegal(essa peca nao eh do jogador atual)");
				return false;
			}
			
			int num_inicial_pecas_inimigas = (this.cor_atual == 'p'? this.num_pecas_brancas:this.num_pecas_pretas);
			Espaco peca = this.espacos[movimento.xi][movimento.yi];
			if(!peca.move(this, movimento.xf, movimento.yf)) {
				return false;
			}
			int num_final_pecas_inimigas = (this.cor_atual == 'p'? this.num_pecas_brancas:this.num_pecas_pretas);
			
			
			//passsa o movimento para o próximo jogador se a peca movida nao puder comer mais nenhuma peca
			if(num_inicial_pecas_inimigas == num_final_pecas_inimigas || !peca.podeComerMais()) {
				this.cor_atual = this.cor_atual == 'p' ? 'b':'p';
			}
			
			return true;
		}
		
		else {
			System.out.println("movimento ilegal(nao ha peca no espaco)");
			return false;
		}
		
	}
}
