package mc322.lab05.espacos;

import mc322.lab05.Tabuleiro;

public class Peao extends Peca{
	public Peao(int x, int y, char cor){
		super(x,y);
		this.icone = cor;
	}
	
	public boolean move(Tabuleiro tabuleiro,int xf, int yf) {
		if(!super.move(tabuleiro,xf,yf)) {
			return false;
		}
		
		int distancia_movida = Math.abs(this.x-xf);
		if(distancia_movida == 1) {
			return true;
		}
		
		else if(distancia_movida == 2 && !tabuleiro.espacoEhVazio((this.x+xf)/2,(this.y+yf)/2)) {
			tabuleiro.removePeca((this.x+xf)/2, (this.y+yf)/2);
			return true;
		}
		
		return false;	
	}

}
