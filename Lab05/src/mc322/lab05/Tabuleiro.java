package mc322.lab05;

import java.util.Queue;

public class Tabuleiro {
	Espaco espacos[][];
	char cor_atual;
	Queue<Movimento> movimentos_obrigatorios_brancas;
	Queue<Movimento> movimentos_obrigatorios_pretas;

	Tabuleiro(){
		espacos = new Espaco[8][8];
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
		
		d1.copiaPosicao(espacos[7][4]);
		espacos[7][4] = d1;
		
		d2.copiaPosicao(espacos[5][2]);
		espacos[5][2] = d2;
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
	
	public void movePeca(Movimento movimento) {
		//checa se esta dentro do tabuleiro
		if(movimento.xi>8 || movimento.xi<0 || movimento.yi>8 || movimento.yi<0 || movimento.xf>8 || movimento.xf<0 || movimento.yf>8 || movimento.yf<0) {
			System.out.println("movimento ilegal(movimento fora do tabuleiro)");
		}
		
		//checa se posicao inicial contem uma peca
		if(this.espacos[movimento.xi][movimento.yi].icone != '-') {
			//checa se a peca eh do jogador atual
			if(!this.ehCor(this.espacos[movimento.xi][movimento.yi],this.cor_atual)) {
				System.out.println("movimento ilegal(essa peca nao eh do jogador atual)");
				return;
			}
			
			
			Espaco peca = this.espacos[movimento.xi][movimento.yi];
			if(!peca.move(this, movimento.xf, movimento.yf)) {
				return;
			}
			
			//passsa o movimento para o próximo jogador
			this.cor_atual = this.cor_atual == 'p' ? 'b':'p';
		}
		
		else {
			System.out.println("movimento ilegal(nao ha peca no espaco)");
		}
		
	}
	
}
