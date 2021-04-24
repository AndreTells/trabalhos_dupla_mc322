package mc322.lab05;

public class Movimento {
	int xi;
	int yi;
	int xf;
	int yf;
	
	Movimento(String movimento) {
		
		int[] posicao_inicial = tranduzPosicao(movimento.substring(0, 2));
		int[] posicao_final = tranduzPosicao(movimento.substring(3));
		
		
		this.xi = posicao_inicial[0];
		this.yi = posicao_inicial[1];
		this.xf = posicao_final[0];
		this.yf = posicao_final[1];
	}
	Movimento(int xi,int yi, int xf, int yf){
		this.xi = xi;
		this.yi = yi;
		this.xf = xf;
		this.yf = yf;
	}

	private static int[] tranduzPosicao(String pos_s) {
		int [] pos = new int[2];
		pos[0] = 7 - ( ((int) pos_s.charAt(1)) - ((int)'1') );
		pos[1] = ( ((int) pos_s.charAt(0)) - ((int)'a') );
		return pos;
	}
	
	boolean ehIgual(Movimento movimento) {
		return (this.xi == movimento.xi && this.yi == movimento.yi && this.xf == movimento.xf && this.yf == movimento.yf);
	}

	boolean ehDentroDoTabuleiro(){
		return !(xi>=8 || xi<0 || yi>=8 || yi<0 || xf>=8 || xf<0 || yf>=8 || yf<0);
	}
	
	//0 --> nao ha peca comida
	//1 --> ha peca comida
	//2 --> movimento ilegal
	int haPecaComida(Tabuleiro tabuleiro) {
		int coeficiente_angular = (yf-yi)/(xf-xi);
		int x_referencia = coeficiente_angular == 1 ? Math.min(xi,xf) : Math.max(xi,xf);
		int y_referencia = x_referencia == xi ? yi:yf;
		int resultado = 0;
		
		for(int i = x_referencia+coeficiente_angular; i !=  (x_referencia == xi ? xf:xi); i+=coeficiente_angular) {
			if(tabuleiro.espacos[i][coeficiente_angular*(i-x_referencia)+y_referencia].icone != '-') {
				if(resultado == 0 && (tabuleiro.espacos[i][coeficiente_angular*(i-x_referencia)+y_referencia].icone == (tabuleiro.espacos[xi][yi].icone == 'B' ? 'p':'b') ||
					tabuleiro.espacos[i][coeficiente_angular*(i-x_referencia)+y_referencia].icone == (tabuleiro.espacos[xi][yi].icone == 'B' ? 'P':'B'))) {
					resultado = 1;
				}
				else if(resultado != 0){
					resultado = 2;
					break;
				}
				else {
					resultado = 2;
					break;
				}
			}
		}
			
		return resultado;
	}

}
