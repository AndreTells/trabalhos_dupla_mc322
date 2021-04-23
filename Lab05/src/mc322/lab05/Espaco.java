package mc322.lab05;

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
}
