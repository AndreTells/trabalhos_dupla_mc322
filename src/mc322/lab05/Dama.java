package mc322.lab05;

public class Dama extends Espaco{
	Dama(int x, int y, char cor){
		this.x = x;
		this.y = y;
		this.icone = cor == 'p' ? 'P':'B';
	}
	
	boolean move(Tabuleiro tabuleiro,int xf, int yf) {
		
		if(tabuleiro.espacos[xf][yf].icone != '-') {
			return false;
		}
		
		int coeficiente_angular = (yf-this.y)/(xf-this.x);
		if( coeficiente_angular == 1 || coeficiente_angular == -1){
			Espaco peca_comida = null;
			
			for(int i = this.x+coeficiente_angular; i != xf; i+=coeficiente_angular) {
				
				if(tabuleiro.espacos[i][coeficiente_angular*(i-this.x)+this.y].icone != '-') {
					if(peca_comida == null && (tabuleiro.espacos[i][coeficiente_angular*(i-this.x)+this.y].icone == (this.icone == 'B' ? 'p':'b') || tabuleiro.espacos[i][coeficiente_angular*(i-this.x)+this.y].icone == (this.icone == 'B' ? 'P':'B'))) {
						peca_comida = tabuleiro.espacos[i][coeficiente_angular*(i-this.x)+this.y];
					}
					else {
						return false;
					}
				}
			}
			
			Vazio transferencia = new Vazio(0,0);
			transferencia.copiaPosicao(this);
			this.copiaPosicao(tabuleiro.espacos[xf][yf]);
			
			tabuleiro.espacos[xf][yf] = this;
			tabuleiro.espacos[transferencia.x][transferencia.y] = transferencia;
			
			if(peca_comida !=null) {
				Vazio espaco_peca_comida = new Vazio(0,0);
				espaco_peca_comida.copiaPosicao(peca_comida);
				tabuleiro.espacos[peca_comida.x][peca_comida.y] = espaco_peca_comida;
			}
			return true;
			
		}
		return false;
	}
}
