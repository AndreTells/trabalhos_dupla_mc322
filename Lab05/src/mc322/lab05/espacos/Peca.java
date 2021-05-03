package mc322.lab05.espacos;

import mc322.lab05.Tabuleiro;

public class Peca extends Espaco{
	
	Peca(int x, int y){
		super(x,y);
	}
	
	public char pegaCor() {
		return this.icone =='p'||this.icone =='P' ? 'p':'b';
	}
	
	public boolean move(Tabuleiro tabuleiro,int xf, int yf) {
		if(!tabuleiro.espacoEhVazio(xf,yf)) {
			System.out.println(xf+" "+yf);
			return false;
		}
		
		if(xf-this.x == 0) {
			System.out.println("here2");
			return false;
		}
		
		int coeficiente_angular = (yf-this.y)/(xf-this.x);
		if( coeficiente_angular != 1 &&  coeficiente_angular != -1) {
			System.out.println("here3");
			return false;
		}
			
		return true;
	}

}