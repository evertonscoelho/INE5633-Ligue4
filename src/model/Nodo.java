package model;

import java.util.LinkedList;
import java.util.List;

import model.constantes.Constantes;

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
			valor += (((Constantes.LINHAS * Constantes.COLUNAS) - tabuleiro.getPosicoesOcupados()) * Constantes.VALOR_CASA_VAZIA);
		}else{
			valor -= (((Constantes.LINHAS * Constantes.COLUNAS) - tabuleiro.getPosicoesOcupados()) * Constantes.VALOR_CASA_VAZIA);
		}
		return valor;
	}

	private int calculaHeuristica() {
		int difDupla = tabuleiro.getDifDupla();
		int difTripla = tabuleiro.getDifTripla();
		int difQuadra = tabuleiro.getDifQuadra();
		return (difDupla * Constantes.VALOR_DUPLA) + (difTripla * Constantes.VALOR_TRIPLA) + (difQuadra * Constantes.VALOR_QUADRA);
	}
	
	public double getValor() {
		double valor;
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
