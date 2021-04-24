package mc322.lab05;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Tabuleiro implements Cloneable{
	Espaco espacos[][];
	char cor_atual;
	private LinkedList<Queue<Movimento>> movimentos_obrigatorios_brancas;
	private LinkedList<Queue<Movimento>> movimentos_obrigatorios_pretas;

	Tabuleiro(){
		espacos = new Espaco[8][8];
		movimentos_obrigatorios_brancas = new LinkedList<>();
		movimentos_obrigatorios_pretas = new LinkedList<>();
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
	
	private void buscaMovimentosObrigatorios() {
		LinkedList<Queue<Movimento>> resultado = (cor_atual=='p'? movimentos_obrigatorios_pretas : movimentos_obrigatorios_brancas);
		
		int max_pecas_comidas = 0;
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				if(espacos[i][j].icone != '-') {
					if(this.ehCor(espacos[i][j], cor_atual)) {
						max_pecas_comidas = espacos[i][j].buscaMovimentosObrigatorios(resultado,this,max_pecas_comidas);
					}
				}
			}
		}
		System.out.println("numero de pecas comidas: "+max_pecas_comidas);
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
			
			LinkedList<Queue<Movimento>> movimentos_possiveis  = (cor_atual=='p'? movimentos_obrigatorios_pretas : movimentos_obrigatorios_brancas);
			if(movimentos_possiveis.size() == 0) {
				this.buscaMovimentosObrigatorios();
			}
			 
			if(movimentos_possiveis.size() != 0) {
				//checar se movimento atual esta na lista
				boolean movimento_atual_eh_valido = false;
				for(Queue<Movimento> seq_movimentos : movimentos_possiveis) {
					if(seq_movimentos.peek().ehIgual(movimento)) {
						movimento_atual_eh_valido = true;
						seq_movimentos.poll();
						
						if(seq_movimentos.peek() == null) {
							movimentos_possiveis.remove(seq_movimentos);
						}	
					}
					else {
						movimentos_possiveis.remove(seq_movimentos);
					}
				}
				
				if(!movimento_atual_eh_valido) {
					System.out.println("movimento ilegal(ese movimento nao eh um dos movimentos obrigatorios)");
					return false;
				}
				
			}
			
			Espaco peca = this.espacos[movimento.xi][movimento.yi];
			if(!peca.move(this, movimento.xf, movimento.yf)) {
				return false;
			}
			
			//passsa o movimento para o próximo jogador
			if(movimentos_possiveis.size() == 0 ) {
				this.cor_atual = this.cor_atual == 'p' ? 'b':'p';
			}
			return true;
		}
		
		else {
			System.out.println("movimento ilegal(nao ha peca no espaco)");
			return false;
		}
		
	}

	public Tabuleiro clone() {
		
		return null;
	}
}
