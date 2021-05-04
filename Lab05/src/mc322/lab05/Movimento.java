package mc322.lab05;

public class Movimento {
	private int xi;
	private int yi;
	private int xf;
	private int yf;
	
	Movimento(String movimento) {
		
		int[] posicao_inicial = tranduzPosicao(movimento.substring(0, 2));
		int[] posicao_final = tranduzPosicao(movimento.substring(3));
		
		
		this.xi = posicao_inicial[0];
		this.yi = posicao_inicial[1];
		this.xf = posicao_final[0];
		this.yf = posicao_final[1];
		
	}

	private static int[] tranduzPosicao(String pos_s) {
		int [] pos = new int[2];
		pos[0] = 7 - ( ((int) pos_s.charAt(1)) - ((int)'1') );
		pos[1] = ( ((int) pos_s.charAt(0)) - ((int)'a') );
		return pos;
	}

	int [] pegaPosicaoInicial() {
		int inicio[] = new int[2];
		inicio[0] = xi;
		inicio[1] = yi;
		return inicio;
	}

	int [] pegaPosicaoFinal() {
		int destino[] = new int[2];
		destino[0] = xf;
		destino[1] = yf;
		return destino;
	}
	
	boolean ehDentroDoTabuleiro(){
		return !(xi>=8 || xi<0 || yi>=8 || yi<0 || xf>=8 || xf<0 || yf>=8 || yf<0);
	}

}
