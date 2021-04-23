package mc322.lab05;

public class AppDama {
	public static void main(String Args[]) {
		Tabuleiro t = new Tabuleiro();
		t.iniciaTabuleiroTeste();
		t.imprimeTabuleiro();
		System.out.println("\n"+t.cor_atual);
		System.out.println("\n-----------------------------\n");
		t.movePeca(new Movimento(7, 4, 4, 1));
		t.imprimeTabuleiro();
		System.out.println("\n"+t.cor_atual);
	}
}
