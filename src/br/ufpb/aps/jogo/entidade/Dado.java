package br.ufpb.aps.jogo.entidade;

import java.util.Random;
/**
 * 
 * Essa Classe funcionar� como um Dado,ela retornar� um valor aleatorio de 1 a 6
 * atraves do metodo lancarDado.
 * 
 * @author jonathas
 *
 */
public class Dado {
	
	private Random random;
	private int valorDoDado;
	private static final int FACES_DO_DADO = 2;// o numero de faces correto � 7
	
	public Dado(){
		this.random = new Random();
	}
	/**
	 * Esse metodo faz a acao de jogar o Dado.
	 * @return retorna o valor que saiu no Dado.
	 */
	public int lancarDado(){
		
		int valorAleatorio = this.random.nextInt(FACES_DO_DADO);
		if(valorAleatorio == 0){
			valorAleatorio = 1;
		}
		valorDoDado = valorAleatorio;
		return valorDoDado;
	}
	
	public int getValorDoDado(){
		return valorDoDado;
	}
	
}
