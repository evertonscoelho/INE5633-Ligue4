package model;

import Excecao.ExceptionJogadaInvalida;
import model.constantes.Constantes;
import model.estados.TabuleiroEstado;

public class Tabuleiro {

	private int[][] posicoes;
	private int linhas = Constantes.LINHAS;
	private int colunas = Constantes.COLUNAS;
	private int posicoesOcupados;
    private TabuleiroEstado tabuleiroEstado;

	public Tabuleiro() {
		this.posicoes = new int[linhas][colunas];
		tabuleiroEstado = new TabuleiroEstado();
		posicoesOcupados = 0;
	}

	public Tabuleiro(int[][] posicaoAux, int posicoesOcupados, TabuleiroEstado tabuleiroEstado) {
		posicoes = posicaoAux;
		this.posicoesOcupados = posicoesOcupados;
		this.tabuleiroEstado = tabuleiroEstado;
	}

	public void geraJogada(int coluna, boolean jogador) throws ExceptionJogadaInvalida {
		if(jogador){
			posicoes[getLinhaJogar(coluna)][coluna] = 1;
		}else{
			posicoes[getLinhaJogar(coluna)][coluna] = 2;
		}
		tabuleiroEstado.atualizaEstado(getLinhaJogar(coluna),coluna, posicoes);
		posicoesOcupados++;
	}
	
	public void geraJogada(int linha, int coluna, boolean jogador){
		if(jogador){
			posicoes[linha][coluna] = 1;
		}else{
			posicoes[linha][coluna] = 2;
		}
		tabuleiroEstado.atualizaEstado(linha,coluna, posicoes);
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
		return tabuleiroEstado.ehFim();
	}


	public int[][] getPosicoes() {
		return posicoes;
	}

	public int getPosicoesOcupados() {
		return posicoesOcupados;
	}

	public Tabuleiro copiaTudo() {
		int[][] posicaoAux = new int[linhas][colunas];
		for (int linha = 0; linha < Constantes.LINHAS; linha++) {
			for (int coluna = 0; coluna < Constantes.COLUNAS; coluna++) {
				posicaoAux[linha][coluna] = posicoes[linha][coluna];
			}
		}
		Tabuleiro aux = new Tabuleiro(posicaoAux, posicoesOcupados, tabuleiroEstado.copiaEstado());
		return aux;
		
	}

	public int getDifDupla() {
		return tabuleiroEstado.getDifDupla();
	}

	public int getDifTripla() {
		return tabuleiroEstado.getDifTripla();
	}

	public int getDifQuadra() {
		return tabuleiroEstado.getDifQuadra();
	}


	

}
