package mc322.lab05;

public class Dama extends Espaco{
	Dama(int x, int y, char cor){
		this.x = x;
		this.y = y;
		this.icone = cor == 'p' ? 'P':'B';
	}
	
	boolean move(Tabuleiro tabuleiro,int xf, int yf) {
		
		if(tabuleiro.espacos[xf][yf].icone != '-') {
			System.out.println("movimento ilegal(posicao final nao esta livre)");
			return false;
		}
		
		int coeficiente_angular = (yf-this.y)/(xf-this.x);
		if( coeficiente_angular == 1 || coeficiente_angular == -1){
			Espaco peca_comida = null;
			
			int x_referencia = Math.max(this.x,xf);
			int y_referencia = x_referencia == this.x ? this.y:yf;
			
			for(int i = x_referencia+coeficiente_angular; i !=  Math.min(this.x,xf); i+=coeficiente_angular) {
				
				if(tabuleiro.espacos[i][coeficiente_angular*(i-x_referencia)+y_referencia].icone != '-') {
					if(peca_comida == null && (tabuleiro.espacos[i][coeficiente_angular*(i-x_referencia)+y_referencia].icone == (this.icone == 'B' ? 'p':'b') || tabuleiro.espacos[i][coeficiente_angular*(i-x_referencia)+y_referencia].icone == (this.icone == 'B' ? 'P':'B'))) {
						peca_comida = tabuleiro.espacos[i][coeficiente_angular*(i-x_referencia)+y_referencia];
					}
					else if(peca_comida != null){
						System.out.println("movimento ilegal(ha mais de uma peca no caminho)");
						return false;
					}
					else {
						System.out.println("movimento ilegal(ha uma peca do mesmo time no caminho)");
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
				//como uma peca foi comida, 
				Vazio espaco_peca_comida = new Vazio(0,0);
				espaco_peca_comida.copiaPosicao(peca_comida);
				tabuleiro.espacos[peca_comida.x][peca_comida.y] = espaco_peca_comida;
				tabuleiro.cor_atual = this.icone == 'P' ? 'b':'p';
			}
			return true;
			
		}
		
		System.out.println("movimento ilegal(nao segue as regras de mover-se apenas pelas diagonais)");
		return false;
	}
}
