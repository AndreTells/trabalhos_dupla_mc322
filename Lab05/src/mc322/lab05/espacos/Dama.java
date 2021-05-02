package mc322.lab05.espacos;

import mc322.lab05.Tabuleiro;

public class Dama extends Peca {
	public Dama(int x, int y, char cor){
		super(x,y);
		this.icone = cor == 'p' ? 'P':'B';
	}
	
	public boolean move(Tabuleiro tabuleiro,int xf, int yf) {
		if(!super.move(tabuleiro,xf,yf)) {
			return false;
		}
		
		int coeficiente_angular = (yf-this.y)/(xf-this.x);
		int x_peca_comida = -1;
		int y_peca_comida = -1;
		
		int x_referencia = coeficiente_angular == 1 ? Math.min(this.x,xf) : Math.max(this.x,xf);
		int y_referencia = x_referencia == this.x ? this.y:yf;
		
		for(int i = x_referencia+coeficiente_angular; i !=  (x_referencia == this.x ? xf:this.x); i+=coeficiente_angular) {
			
			if(!tabuleiro.espacoEhVazio(i, coeficiente_angular*(i-x_referencia)+y_referencia)) {
				Peca peca = tabuleiro.pegaPeca(i,coeficiente_angular*(i-x_referencia)+y_referencia);
				
				if(x_peca_comida == -1 && peca.pegaCor() !=  this.pegaCor() ) {
					x_peca_comida = i;
					y_peca_comida = coeficiente_angular*(i-x_referencia)+y_referencia;
				}
				else if(x_peca_comida != -1){
					return false;
				}
				else {
					return false;
				}
			}
		}
		
		//checa se uma peca foi comida 
		if(x_peca_comida !=-1) {
			//pede para o tabuleiro remover peca comida
			tabuleiro.removePeca(x_peca_comida, y_peca_comida);					
		}
		
		return true;
			
	}

}
