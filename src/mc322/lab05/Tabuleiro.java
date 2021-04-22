package mc322.lab05;

public class Tabuleiro {
	Espaco espacos[][];
	Tabuleiro(){
		espacos = new Espaco[8][8];
	}
	void iniciaTabuleiroTeste() {
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				espacos[i][j] = new Vazio(i,j);
			}
		}
		Dama d1 = new Dama(0,0,'p');
		Dama d2 = new Dama(0,0,'b');
		d1.copiaPosicao(espacos[7][0]);
		espacos[7][0] = d1;
		
		d2.copiaPosicao(espacos[5][2]);
		espacos[5][2] = d2;
	}
	
	public String criaString() {
		String tabuleiro_string = "";
		for(int i=0;i<espacos.length;i++) {
			for(int j =0;j<espacos[0].length; j++) {
				tabuleiro_string+=espacos[i][j].icone;
			}
			tabuleiro_string+='\n';
		}
		return tabuleiro_string;
	}

	public void imprimeTabuleiro() {
		for(int i=0;i<espacos.length;i++) {
			System.out.print(8-i);
			for(int j =0;j<espacos[0].length; j++) {
				System.out.print(" "+espacos[i][j].icone);
			}
			System.out.print("\n");
		}
		System.out.println("a b c d e f g h");
	}

	public void movePeca(int xi,int yi,int xf,int yf) {
		Dama d = (Dama) espacos[xi][yi];
		d.move(this, xf, yf);
	}
	
}
