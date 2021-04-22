package mc322.lab05;

public class AppDama {
	public static void main(String Args[]) {
		Tabuleiro t = new Tabuleiro();
		t.iniciaTabuleiroTeste();
		t.imprimeTabuleiro();
		System.out.println("\n-----------------------------\n");
		t.movePeca(7, 0, 4, 3);
		t.imprimeTabuleiro();
		System.out.println("yay");
	}
}
