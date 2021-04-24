package mc322.lab05;

import java.util.LinkedList;
import java.util.Queue;

public class Dama extends Espaco implements Cloneable{
	Dama(int x, int y, char cor){
		this.x = x;
		this.y = y;
		this.icone = cor == 'p' ? 'P':'B';
	}
	
	protected Object clone() throws CloneNotSupportedException {
		 return super.clone();
	}
	
	boolean move(Tabuleiro tabuleiro,int xf, int yf) {
		
		if(tabuleiro.espacos[xf][yf].icone != '-') {
			System.out.println("movimento ilegal(posicao final nao esta livre)");
			return false;
		}
		if(xf-this.x == 0) {
			System.out.println("movimento ilegal(nao segue as regras de mover-se apenas pelas diagonais)");
		}
		
		int coeficiente_angular = (yf-this.y)/(xf-this.x);
		if( coeficiente_angular == 1 || coeficiente_angular == -1){
			Espaco peca_comida = null;
			
			int x_referencia = coeficiente_angular == 1 ? Math.min(this.x,xf) : Math.max(this.x,xf);
			int y_referencia = x_referencia == this.x ? this.y:yf;
			
			for(int i = x_referencia+coeficiente_angular; i !=  (x_referencia == this.x ? xf:this.x); i+=coeficiente_angular) {
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
			}
			return true;
			
		}
		
		System.out.println("movimento ilegal(nao segue as regras de mover-se apenas pelas diagonais)");
		return false;
	}

	
	int buscaMovimentosObrigatorios(LinkedList<Queue<Movimento>> lista_movimentos, Tabuleiro tabuleiro,int max_pecas_comidas) {
		int coeficientes_angulares[] = {1,-1};
		for(int i=0;i<2;i++) {
			Movimento movimento_positivo = new Movimento(this.x,this.y,this.x,this.y);
			movimento_positivo.xf +=1;
			movimento_positivo.yf +=coeficientes_angulares[i];
			
			while(movimento_positivo.ehDentroDoTabuleiro()) {
				
				int ha_peca_comida = movimento_positivo.haPecaComida(tabuleiro);
				
				if(ha_peca_comida == 1) {
					//comeca counter de pecas comidas e lista de movimentos
					int num_pecas_comidas = 1;
					Queue<Movimento> movimento_inicial = new LinkedList<Movimento>();
					movimento_inicial.add(movimento_positivo);
					
					//simula
					LinkedList<Queue<Movimento>> subsequencia_movimentos = new LinkedList<Queue<Movimento>>();
					Tabuleiro tabuleiro_simulado = tabuleiro.clone();
					Dama peca_simulada = (Dama) tabuleiro_simulado.espacos[this.x][this.y];
					peca_simulada.move(tabuleiro_simulado, movimento_positivo.xf, movimento_positivo.yf);
					
					//adiciona pecas comidas nos mlhores caminhos sub sequentes
					num_pecas_comidas+=peca_simulada.buscaMovimentosObrigatorios(subsequencia_movimentos, tabuleiro_simulado, movimento_inicial);
					
					tabuleiro.imprimeTabuleiro();
					if(num_pecas_comidas > max_pecas_comidas) {
						max_pecas_comidas = num_pecas_comidas;
						lista_movimentos = new LinkedList<Queue<Movimento>>();
						lista_movimentos.addAll(subsequencia_movimentos);
					}
					else if(num_pecas_comidas == max_pecas_comidas){
						lista_movimentos.addAll(subsequencia_movimentos);
					}
					
				}
				else if(ha_peca_comida == 2) {
					break;
				}
				
				movimento_positivo.xf +=1;
				movimento_positivo.yf +=coeficientes_angulares[i];
			
				
			}
			
			
			Movimento movimento_negativo = new Movimento(this.x,this.y,this.x,this.y);
			movimento_negativo.xf -=1;
			movimento_negativo.yf -=coeficientes_angulares[i];
			
			while(movimento_negativo.ehDentroDoTabuleiro()) {
				
				int ha_peca_comida = movimento_negativo.haPecaComida(tabuleiro);
				if(ha_peca_comida == 1) {
					//comeca counter de pecas comidas e lista de movimentos
					int num_pecas_comidas = 1;
					Queue<Movimento> movimento_inicial = new LinkedList<Movimento>();
					movimento_inicial.add(movimento_negativo);
					
					//simula
					LinkedList<Queue<Movimento>> subsequencia_movimentos = new LinkedList<Queue<Movimento>>();
					Tabuleiro tabuleiro_simulado = tabuleiro.clone();
					Dama peca_simulada = (Dama) tabuleiro_simulado.espacos[this.x][this.y];
					peca_simulada.move(tabuleiro_simulado, movimento_negativo.xf, movimento_negativo.yf);
					
					//adiciona pecas comidas nos mlhores caminhos sub sequentes
					num_pecas_comidas+=peca_simulada.buscaMovimentosObrigatorios(subsequencia_movimentos, tabuleiro_simulado, movimento_inicial);
					
					tabuleiro.imprimeTabuleiro();
					if(num_pecas_comidas > max_pecas_comidas) {
						max_pecas_comidas = num_pecas_comidas;
						lista_movimentos = new LinkedList<Queue<Movimento>>();
						lista_movimentos.addAll(subsequencia_movimentos);
					}
					else if(num_pecas_comidas == max_pecas_comidas){
						lista_movimentos.addAll(subsequencia_movimentos);
					}
					
				}
				else if(ha_peca_comida == 2) {
					break;
				}
				
				movimento_negativo.xf -=1;
				movimento_negativo.yf -=coeficientes_angulares[i];
			}
		}
		
		return max_pecas_comidas;
	}
	
	int buscaMovimentosObrigatorios(LinkedList<Queue<Movimento>> lista_movimentos, Tabuleiro tabuleiro, Queue<Movimento> movimentos_anteriores) {
		LinkedList<Queue<Movimento>> candidatos_lista = new LinkedList<Queue<Movimento>>();
		int max_pecas_comidas = 0;
		int coeficientes_angulares[] = {1,-1};
		for(int i=0;i<2;i++) {
			Movimento movimento_positivo = new Movimento(this.x,this.y,this.x,this.y);
			movimento_positivo.xf +=1;
			movimento_positivo.yf +=coeficientes_angulares[i];
			while(movimento_positivo.ehDentroDoTabuleiro()) {
				
				int ha_peca_comida = movimento_positivo.haPecaComida(tabuleiro);
				
				if(ha_peca_comida == 1) {
					//comeca counter de pecas comidas e lista de movimentos
					int num_pecas_comidas = 1;
					Queue<Movimento> movimento_inicial = new LinkedList<Movimento>();
					movimento_inicial.addAll(movimentos_anteriores);
					movimento_inicial.add(movimento_positivo);
					
					//simula
					LinkedList<Queue<Movimento>> subsequencia_movimentos = new LinkedList<Queue<Movimento>>();
					Tabuleiro tabuleiro_simulado = tabuleiro.clone();
					Dama peca_simulada = (Dama) tabuleiro_simulado.espacos[this.x][this.y];
					peca_simulada.move(tabuleiro_simulado, movimento_positivo.xf, movimento_positivo.yf);
					
					//adiciona pecas comidas nos mlhores caminhos sub sequentes
					num_pecas_comidas+=peca_simulada.buscaMovimentosObrigatorios(subsequencia_movimentos, tabuleiro_simulado, movimento_inicial);
					
					tabuleiro.imprimeTabuleiro();
					
					if(num_pecas_comidas > max_pecas_comidas) {
						max_pecas_comidas = num_pecas_comidas;
						candidatos_lista = new LinkedList<Queue<Movimento>>();
						candidatos_lista.addAll(subsequencia_movimentos);
					}
					else if(num_pecas_comidas == max_pecas_comidas){
						candidatos_lista.addAll(subsequencia_movimentos);
					}
					
				}
				else if(ha_peca_comida == 2) {
					break;
				}
				
				movimento_positivo.xf +=1;
				movimento_positivo.yf +=coeficientes_angulares[i];
			}
			
			
			Movimento movimento_negativo = new Movimento(this.x,this.y,this.x,this.y);
			movimento_negativo.xf -=1;
			movimento_negativo.yf -=coeficientes_angulares[i];
			
			while(movimento_negativo.ehDentroDoTabuleiro()) {
				
				int ha_peca_comida = movimento_negativo.haPecaComida(tabuleiro);
				if(ha_peca_comida == 1) {
					//comeca counter de pecas comidas e lista de movimentos
					int num_pecas_comidas = 1;
					Queue<Movimento> movimento_inicial = new LinkedList<Movimento>();
					movimento_inicial.addAll(movimentos_anteriores);
					movimento_inicial.add(movimento_negativo);
					
					//simula
					LinkedList<Queue<Movimento>> subsequencia_movimentos = new LinkedList<Queue<Movimento>>();
					Tabuleiro tabuleiro_simulado = tabuleiro.clone();
					Dama peca_simulada = (Dama) tabuleiro_simulado.espacos[this.x][this.y];
					peca_simulada.move(tabuleiro_simulado, movimento_negativo.xf, movimento_negativo.yf);
					
					//adiciona pecas comidas nos mlhores caminhos sub sequentes
					num_pecas_comidas+=peca_simulada.buscaMovimentosObrigatorios(subsequencia_movimentos, tabuleiro_simulado, movimento_inicial);
					
					tabuleiro.imprimeTabuleiro();
					if(num_pecas_comidas > max_pecas_comidas) {
						max_pecas_comidas = num_pecas_comidas;
						candidatos_lista = new LinkedList<Queue<Movimento>>();
						candidatos_lista.addAll(subsequencia_movimentos);
					}
					else if(num_pecas_comidas == max_pecas_comidas){
						candidatos_lista.addAll(subsequencia_movimentos);
					}
					
				}
				else if(ha_peca_comida == 2) {
					break;
				}
				
				movimento_negativo.xf -=1;
				movimento_negativo.yf -=coeficientes_angulares[i];
			}
		}
		
		
		lista_movimentos.addAll(candidatos_lista);
		return max_pecas_comidas;
	}
	
	
}
