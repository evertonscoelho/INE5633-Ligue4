package model;

import Excecao.ExceptionJogadaInvalida;

public class Tabuleiro {

	private int[][] posicoes;
	private int linhas = Constantes.LINHAS;
	private int colunas = Constantes.COLUNAS;
	private int posicoesOcupados;
	private int seqJog = 0, seqPc = 0, seqZero = 0, valor = 0;
	private boolean ehFim;

	public Tabuleiro() {
		this.posicoes = new int[linhas][colunas];
		posicoesOcupados = 0;
	}

	public Tabuleiro(int[][] posicoes, int poscioesocupadas) {
		this.posicoes = posicoes;
		posicoesOcupados = poscioesocupadas;
	}

	public void geraJogada(int coluna, boolean jogador) throws ExceptionJogadaInvalida {
		if (jogador) {
			posicoes[getLinhaJogar(coluna)][coluna] = 1;
		} else {
			posicoes[getLinhaJogar(coluna)][coluna] = 2;
		}
		posicoesOcupados++;
	}

	public void geraJogada(int linha, int coluna, boolean jogador) {
		if (jogador) {
			posicoes[linha][coluna] = 1;
		} else {
			posicoes[linha][coluna] = 2;
		}
		posicoesOcupados++;
	}

	private int getLinhaJogar(int coluna) throws ExceptionJogadaInvalida {
		for (int i = 0; i < linhas; i++) {
			if (posicoes[i][coluna] == 0) {
				return i;
			}
		}
		throw new ExceptionJogadaInvalida();
	}

	public int getLinhaJogarPC(int coluna) {
		for (int i = 0; i < linhas; i++) {
			if (posicoes[i][coluna] == 0) {
				return i;
			}
		}
		return -1;
	}

	public boolean ehFinal() {
		return ehFim;
	}

	public int[][] getPosicoes() {
		return posicoes;
	}

	public int getPosicoesOcupados() {
		return posicoesOcupados;
	}

	public Tabuleiro CopiaTudo(Tabuleiro tabuleiro) {
		int[][] posicaoAux = tabuleiro.getPosicoes();
		for (int linha = 0; linha < Constantes.LINHAS; linha++) {
			for (int coluna = 0; coluna < Constantes.COLUNAS; coluna++) {
				posicoes[linha][coluna] = posicaoAux[linha][coluna];
			}
		}

		Tabuleiro tabuleiroNovo = new Tabuleiro(posicoes, this.posicoesOcupados);
		this.posicoes = posicaoAux;
		return tabuleiroNovo;
	}

	public int calculaHeuristica() {
		zeraValores();
		calculaLinhasIda();
		calculaLinhasVolta();

		zeraValores();
		for (int j = 0; j < colunas; j++) {
			for (int i = 0; i < linhas; i++) {
				if (posicoes[i][j] == 0) {
					seqZero++;
					if (seqJog > 0)
						pecaJogadorColuna();
					else if (seqPc > 0)
						pecaPcColuna();
				} else if (posicoes[i][j] == 1) {
					pecaJogadorColuna();
				} else if (posicoes[i][j] == 2) {
					pecaPcColuna();
				}
			}
			acabouPercorrerColuna();
			zeraValores();
		}

		return valor;
	}

	private void pecaJogadorColuna() {
		seqJog++;
		if (seqPc > 0) {
			seqZero = 0;
			seqPc = 0;
		}
	}

	private void pecaPcColuna() {
		seqPc++;
		if (seqJog > 0) {
			seqJog = 0;
			seqZero = 0;
		}
	}

	private void acabouPercorrerColuna() {
		if (seqJog >= 4) {
			valor -= Constantes.VALOR_QUADRA;
			ehFim = true;
		} else if (seqJog == 3 && seqZero > 0) {
			valor -= Constantes.VALOR_TRIPLA;
		} else if (seqJog == 2 && seqZero > 1) {
			valor -= Constantes.VALOR_DUPLA;
		}
		if (seqPc >= 4) {
			valor += Constantes.VALOR_QUADRA;
			ehFim = true;
		} else if (seqPc == 3 && seqZero > 0) {
			valor += Constantes.VALOR_TRIPLA;
		} else if (seqPc == 2 && seqZero > 1) {
			valor += Constantes.VALOR_DUPLA;
		}
	}

	private void calculaLinhasIda() {
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j > colunas; j++) {
				if (posicoes[i][j] == 0) {
					seqZero++;
					if (seqJog > 0)
						pecaJogadorLinha();
					else if (seqPc > 0)
						pecaPcLinha();
				} else if (posicoes[i][j] == 1) {
					pecaJogadorLinha();
				} else if (posicoes[i][j] == 2) {
					pecaPcLinha();
				}
			}
		}
	}

	private void calculaLinhasVolta() {
		for (int i = 0; i < linhas; i++) {
			for (int j = 0; j < colunas; j++) {
				if (posicoes[i][j] == 0) {
					seqZero++;
					if (seqJog > 0)
						pecaJogadorLinha();
					else if (seqPc > 0)
						pecaPcLinha();

				} else if (posicoes[i][j] == 1) {
					pecaJogadorLinha();
				} else if (posicoes[i][j] == 2) {
					pecaPcLinha();
				}
			}
		}
	}

	private void pecaJogadorLinha() {
		seqJog++;
		if (seqPc > 0) {
			seqZero = 0;
			seqPc = 0;
		}

		if (seqJog >= 4) {
			valor -= Constantes.VALOR_QUADRA;
			ehFim = true;
			zeraValores();
		} else if (seqJog == 3 && seqZero > 0) {
			valor -= Constantes.VALOR_TRIPLA;
			zeraValores();
		} else if (seqJog == 2 && seqZero > 1) {
			valor -= Constantes.VALOR_DUPLA;
			zeraValores();
		}
	}

	private void pecaPcLinha() {
		seqPc++;
		if (seqJog > 0) {
			seqZero = 0;
			seqJog = 0;
		}

		if (seqPc >= 4) {
			valor += Constantes.VALOR_QUADRA;
			ehFim = true;
			zeraValores();
		} else if (seqPc == 3 && seqZero > 0) {
			valor += Constantes.VALOR_TRIPLA;
			zeraValores();
		} else if (seqPc == 2 && seqZero > 1) {
			valor += Constantes.VALOR_DUPLA;
			zeraValores();
		}
	}

	private void zeraValores() {
		seqJog = 0;
		seqPc = 0;
		seqZero = 0;
	}

	public int calculaUtilidade() {
		return valor +(posicoesOcupados * Constantes.VALOR_CASA_VAZIA);
	}

}
