package model;

import java.util.LinkedList;
import java.util.List;

import Excecao.ExceptionJogadaInvalida;

public class Tabuleiro {

	private int[][] posicoes;
	private int linhas = 6;
	private int colunas = 7;
	private List<Integer[]> posicoesOcupados;

	public Tabuleiro(int[][] posicoes) {
		this.posicoes = posicoes;
		posicoesOcupados = new LinkedList<Integer[]>();
	}

	public Tabuleiro(int[][] posicoes, List<Integer[]> posicoesOcupados) {
		this.posicoes = posicoes;
		this.posicoesOcupados = posicoesOcupados;
	}

	public Tabuleiro() {
		this.posicoes = new int[linhas][colunas];
		posicoesOcupados = new LinkedList<Integer[]>();
	}

	public void geraJogada(int coluna, boolean jogador) throws ExceptionJogadaInvalida {
		if(jogador){
			posicoes[getLinhaJogar(coluna)][coluna] = 1;
		}else{
			posicoes[getLinhaJogar(coluna)][coluna] = 2;
		}
	}

	private int getLinhaJogar(int coluna) throws ExceptionJogadaInvalida {
		for(int i = 0; i < linhas; i++){
			if(posicoes[i][coluna] == 0){
				return i;
			}
		}
		throw new ExceptionJogadaInvalida();
	}

	public boolean ehFinal() {
		// TODO Auto-generated method stub
		return false;
	}

	public int getValor() {
		int valor;

		if (!ehFinal())
			valor = calculaHeuristica();
		else
			valor = calculaUtilidade();

		return valor;
	}

	private int calculaUtilidade() {
		int valor = calculaHeuristica();
		valor += (((linhas * colunas) - posicoesOcupados.size()) * 100);
		return valor;
	}

	private int calculaHeuristica() {
		// TODO Auto-generated method stub
		return 0;
	}


	public int[][] getPosicoes() {
		return posicoes;
	}

}
