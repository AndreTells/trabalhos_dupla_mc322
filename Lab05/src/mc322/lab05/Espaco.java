package mc322.lab05;

import java.util.LinkedList;
import java.util.Queue;

public class Espaco {
	int x;
	int y;
	char icone;
	
	void copiaPosicao(Espaco local) {
		this.x = local.x;
		this.y = local.y;
	}
	
	boolean move(Tabuleiro tabuleiro,int xf, int yf) {
		return false;
	}

	int buscaMovimentosObrigatorios(LinkedList<Queue<Movimento>> lista_movimentos,Tabuleiro tabuleiro) {
		return 0;
	}

}
