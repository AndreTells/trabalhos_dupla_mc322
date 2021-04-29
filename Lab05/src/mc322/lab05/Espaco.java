package mc322.lab05;

public class Espaco {
	protected int x;
	protected int y;
	protected char icone;
	
	void copiaPosicao(Espaco local) {
		this.x = local.x;
		this.y = local.y;
	}
	
	char pegaIcone() {
		return this.icone;
	}
	
	char pegaCor() {
		if(this.icone == '-') {
			return ' ';
		}
		return this.icone =='p'||this.icone =='P' ? 'p':'b';
	}
	
	boolean ehVazio() {
		return this.icone =='-' ? true:false;
	}
	boolean move(Tabuleiro tabuleiro,int xf, int yf) {
		return false;
	}
	
}
