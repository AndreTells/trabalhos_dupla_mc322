package mc322.lab05.espacos;

public class Espaco {
	protected int x;
	protected int y;
	protected char icone;
	
	Espaco(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void copiaPosicao(Espaco local) {
		this.x = local.x;
		this.y = local.y;
	}
	
	public char pegaIcone() {
		return this.icone;
	}
	
	public boolean ehVazio() {
		return this.icone =='-';
	}
	
	public int pegaDistancia(Espaco espaco) {
		return Math.abs(this.x - espaco.x);
	}
	
}
