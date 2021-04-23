package mc322.lab05;

public class Movimento {
	int xi;
	int yi;
	int xf;
	int yf;
	
	Movimento(int xi, int yi, int xf, int yf) {
		this.xi = xi;
		this.yi = yi;
		this.xf = xf;
		this.yf = yf;
	}
	
	boolean ehIgual(Movimento movimento) {
		return (this.xi == movimento.xi && this.yi == movimento.yi && this.xf == movimento.xf && this.yf == movimento.yf);
	}
}
