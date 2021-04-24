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

}
