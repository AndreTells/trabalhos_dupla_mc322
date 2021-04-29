package mc322.lab05;

public class Dama extends Espaco {
	Dama(int x, int y, char cor){
		this.x = x;
		this.y = y;
		this.icone = cor == 'p' ? 'P':'B';
	}
	
	boolean move(Tabuleiro tabuleiro,int xf, int yf) {
		
		if(!tabuleiro.pegaPeca(xf,yf).ehVazio()) {
			return false;
		}
		if(xf-this.x == 0) {
			return false;
		}
		
		int coeficiente_angular = (yf-this.y)/(xf-this.x);
		if( coeficiente_angular == 1 || coeficiente_angular == -1){
			int x_peca_comida = -1;
			int y_peca_comida = -1;
			
			int x_referencia = coeficiente_angular == 1 ? Math.min(this.x,xf) : Math.max(this.x,xf);
			int y_referencia = x_referencia == this.x ? this.y:yf;
			
			for(int i = x_referencia+coeficiente_angular; i !=  (x_referencia == this.x ? xf:this.x); i+=coeficiente_angular) {
				Espaco espaco = tabuleiro.pegaPeca(i,coeficiente_angular*(i-x_referencia)+y_referencia);
				if(!espaco.ehVazio()) {
					if(x_peca_comida == -1 && espaco.pegaCor() !=  this.pegaCor() ) {
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
		
		return false;
	}

}
