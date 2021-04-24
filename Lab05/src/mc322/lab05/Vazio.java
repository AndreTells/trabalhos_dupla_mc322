package mc322.lab05;

public class Vazio extends Espaco implements Cloneable{
	Vazio(int x,int y){
		this.x = x;
		this.y = y;
		this.icone = '-';
	}
	 protected Object clone() throws CloneNotSupportedException {
		 return super.clone();
	}
}
