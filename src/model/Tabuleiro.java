package model;

import java.util.LinkedList;
import java.util.List;

import Excecao.ExceptionJogadaInvalida;

public class Tabuleiro {

	private int[][] posicoes;
	private int linhas = 6;
	private int colunas = 7;
	private int posicoesOcupados;


	public Tabuleiro(int[][] posicoes, int posicoesOcupados) {
		this.posicoes = posicoes;
		this.posicoesOcupados = posicoesOcupados;
	}

	public Tabuleiro() {
		this.posicoes = new int[linhas][colunas];
		posicoesOcupados = 0;
	}

	public void geraJogada(int coluna, boolean jogador) throws ExceptionJogadaInvalida {
		if(jogador){
			posicoes[getLinhaJogar(coluna)][coluna] = 1;
		}else{
			posicoes[getLinhaJogar(coluna)][coluna] = 2;
		}
		posicoesOcupados++;
	}
	
	public void geraJogada(int linha, int coluna, boolean jogador){
		if(jogador){
			posicoes[linha][coluna] = 1;
		}else{
			posicoes[linha][coluna] = 2;
		}
		posicoesOcupados++;
	}

	private int getLinhaJogar(int coluna) throws ExceptionJogadaInvalida {
		for(int i = 0; i < linhas; i++){
			if(posicoes[i][coluna] == 0){
				return i;
			}
		}
		throw new ExceptionJogadaInvalida();
	}
	
	public int getLinhaJogarPC(int coluna){
		for(int i = 0; i < linhas; i++){
			if(posicoes[i][coluna] == 0){
				return i;
			}
		}
		return -1;
	}

	public boolean ehFinal() {
		// TODO Auto-generated method stub
		return false;
	}


	public int[][] getPosicoes() {
		return posicoes;
	}

	public int getPosicoesOcupados() {
		return posicoesOcupados;
	}

	public int getLinhas() {
		return linhas;
	}

	public int getColunas() {
		return colunas;
	}

	

}
