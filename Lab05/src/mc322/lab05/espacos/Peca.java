package mc322.lab05.espacos;

import mc322.lab05.Tabuleiro;

public class Peca extends Espaco{
	
	Peca(int x, int y){
		super(x,y);
	}
	
	char pegaCor() {
		return this.icone =='p'||this.icone =='P' ? 'p':'b';
	}
	
	public boolean move(Tabuleiro tabuleiro,int xf, int yf) {
		if(!tabuleiro.pegaPeca(xf,yf).ehVazio()) {
			return false;
		}
		if(xf-this.x == 0) {
			return false;
		}
		
		int coeficiente_angular = (yf-this.y)/(xf-this.x);
		if( coeficiente_angular != 1 &&  coeficiente_angular != -1) {
			return false;
		}
			
		return true;
	}

}