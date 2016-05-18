package model;

import java.util.LinkedList;
import java.util.List;

public class Nodo {
	
	Tabuleiro tabuleiro;

	public Nodo(Tabuleiro tabuleiroAtual) {
	 this.tabuleiro = tabuleiroAtual;
	}
	
	public List<int[]> geraFilhosPossiveis() {
		List<int[]> retorno = new LinkedList<>();
		addLista(retorno, 3);
		addLista(retorno, 2);
		addLista(retorno, 4);
		addLista(retorno, 1);
		addLista(retorno, 5);
		addLista(retorno, 0);
		addLista(retorno, 6);
		return retorno;
	}
	
	private void addLista(List<int[]> retorno, int coluna) {
		if(tabuleiro.getLinhaJogarPC(coluna) > -1){
			retorno.add(new int[]{tabuleiro.getLinhaJogarPC(coluna), coluna});
		}
	}

	private int calculaUtilidade() {
		int valor = calculaHeuristica();
		if(valor > 0){
			valor += (((Constantes.LINHAS * Constantes.COLUNAS) - tabuleiro.getPosicoesOcupados()) * 100);
		}else{
			valor -= (((Constantes.LINHAS * Constantes.COLUNAS) - tabuleiro.getPosicoesOcupados()) * 100);
		}
		return valor;
	}

	private int calculaHeuristica() {
		int difDupla = tabuleiro.getDifDupla();
		int difTripla = tabuleiro.getDifTripla();
		int difQuadra = tabuleiro.getDifQuadra();
		return (difDupla * Constantes.VALOR_DUPLA) + (difTripla * Constantes.VALOR_TRIPLA) + (difQuadra * Constantes.VALOR_QUADRA);
	}
	
	public int getValor() {
		int valor;
		if (!tabuleiro.ehFinal())
			valor = calculaHeuristica();
		else
			valor = calculaUtilidade();
		return valor;
	}
	
	public Tabuleiro getTabuleiro(){
		return tabuleiro;
	}

	public Nodo geraFilho(int[] filho, boolean jogador) {
		Tabuleiro aux = tabuleiro.copiaTudo();
		aux.geraJogada(filho[0], filho[1], jogador);
		return new Nodo(aux);
	}



}
