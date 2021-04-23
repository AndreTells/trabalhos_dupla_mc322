package mc322.lab05;

import java.util.Arrays;
import java.util.Queue;

public class Tabuleiro {
	Espaco espacos[][];
	char cor_atual;
	private Queue<Movimento> movimentos_obrigatorios_brancas[];
	private Queue<Movimento> movimentos_obrigatorios_pretas[];

	Tabuleiro(){
		espacos = new Espaco[8][8];
		movimentos_obrigatorios_brancas = new Queue [12];
		movimentos_obrigatorios_pretas = new Queue [12];
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
		
		d1.copiaPosicao(espacos[0][0]);
		espacos[0][0] = d1;
		
		d2.copiaPosicao(espacos[1][1]);
		espacos[1][1] = d2;
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
	
	private void buscaMovimentosObrigatorios() {
		int index_pecas = 0;
		int num_pecas_comidas[] = new int[12];
		//(cor_atual=='p'? movimentos_obrigatorios_pretas : movimentos_obrigatorios_brancas)
		Queue movimentos_candidatos []= new Queue[12];
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(espacos[i][j].icone != '-') {
					if(this.ehCor(espacos[i][j], cor_atual)) {
						movimentos_candidatos[index_pecas] = null;
						num_pecas_comidas[index_pecas] = espacos[i][j].buscaMovimentosObrigatorios(movimentos_candidatos[index_pecas]);
						index_pecas++;
					}
				}
			}
		}
		
		Arrays.sort(num_pecas_comidas);
		int max = num_pecas_comidas[num_pecas_comidas.length-1];
		
		int index_resultado = 0;
		Queue resultado [] = (cor_atual=='p'? movimentos_obrigatorios_pretas : movimentos_obrigatorios_brancas);
		for(int i  = 0;i<num_pecas_comidas.length;i++) {
			if(num_pecas_comidas[i] == max) {
				resultado[index_resultado] = movimentos_candidatos[i];
				index_resultado++;	
			}
		}
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
			
			Queue queue_atual [] = (cor_atual=='p'? movimentos_obrigatorios_pretas : movimentos_obrigatorios_brancas);
			if(queue_atual == null) {
				this.buscaMovimentosObrigatorios();
			}
			
			if(queue_atual != null) {
				//checar se movimento atual esta na lista
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
