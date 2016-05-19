package model.estados;

import model.Estado;
import model.constantes.Constantes;

public class TabuleiroEstado {

	private Estado[] linha;
	private Estado[] coluna;
	private Estado[] diagonal;
	private boolean ehFim;
	private int seqJog = 0, seqPc = 0, zeroRestante = 0, seqZero = 0, zeroAntes = 0, zeroDepois = 0;

	public TabuleiroEstado() {
		linha = new Estado[Constantes.LINHAS];
		coluna = new Estado[Constantes.COLUNAS];
		diagonal = new Estado[Constantes.DIAGONAIS];
		init();
		ehFim = false;
	}

	public TabuleiroEstado copiaEstado() {
		TabuleiroEstado aux = new TabuleiroEstado();
		for (int i = 0; i < linha.length; i++) {
			aux.setLinha(i,
					new Estado(linha[i].getDupla_jogador(), linha[i].getDupla_pc(), linha[i].getTripla_jogador(),
							linha[i].getTripla_pc(), linha[i].getQuadra_jogador(), linha[i].getQuadra_pc()));
		}
		for (int i = 0; i < coluna.length; i++) {
			aux.setColuna(i,
					new Estado(coluna[i].getDupla_jogador(), coluna[i].getDupla_pc(), coluna[i].getTripla_jogador(),
							coluna[i].getTripla_pc(), coluna[i].getQuadra_jogador(), coluna[i].getQuadra_pc()));
		}
		for (int i = 0; i < diagonal.length; i++) {
			aux.setDiagonal(i,
					new Estado(diagonal[i].getDupla_jogador(), diagonal[i].getDupla_pc(),
							diagonal[i].getTripla_jogador(), diagonal[i].getTripla_pc(),
							diagonal[i].getQuadra_jogador(), diagonal[i].getQuadra_pc()));
		}
		return aux;
	}

	private void setDiagonal(int i, Estado estado) {
		diagonal[i] = estado;

	}

	private void setColuna(int i, Estado estado) {
		coluna[i] = estado;
	}

	private void setLinha(int i, Estado estado) {
		linha[i] = estado;
	}

	private void init() {
		for (int i = 0; i < linha.length; i++) {
			linha[i] = new Estado();
		}
		for (int i = 0; i < coluna.length; i++) {
			coluna[i] = new Estado();
		}
		for (int i = 0; i < diagonal.length; i++) {
			diagonal[i] = new Estado();
		}
	}

	public void atualizaEstado(int linhaJogar, int coluna, int[][] posicoes) {
		atualizaColuna(coluna, posicoes);
		atualizaLinha(linhaJogar, posicoes);
		// atualizaDiagonal();

	}

	private void atualizaLinha(int linhaJogar, int[][] posicoes) {
		zeraValores();
		for (int i = 0; i < coluna.length; i++) {
			if (posicoes[linhaJogar][i] == 0) {
				seqZero++;
			} else if (posicoes[linhaJogar][i] == 1) {
				jogadorLinha(linhaJogar);
			} else if (posicoes[linhaJogar][i] == 2) {
				pcLinha(linhaJogar);
			}
		}
		acabouPercorrerLinha(linhaJogar);

	}

	private void pcLinha(int linhaJogar) {
		if (seqJog > 0) {
			if (seqJog >= 4 && seqZero ==0) {
				linha[linhaJogar].setQuadra_jogador(1);
				ehFim = true;
			} else if (seqJog == 3 && zeroAntes >= 1) {
				linha[linhaJogar].setTripla_jogador(linha[linhaJogar].getTripla_jogador() + 1);
			} else if (seqJog == 2 && zeroAntes >= 2) {
				linha[linhaJogar].setDupla_jogador(linha[linhaJogar].getDupla_jogador() + 1);
			}
			zeroAntes = 0;
		} else if (seqZero > 0) {
			zeroAntes = seqZero;
			if (seqJog >= 2 || seqPc >= 2) {
				if (seqJog == 3 && seqZero >= 1) {
					linha[linhaJogar].setTripla_jogador(linha[linhaJogar].getTripla_jogador() + 1);
				} else if (seqJog == 2 && seqZero >= 2) {
					linha[linhaJogar].setDupla_jogador(linha[linhaJogar].getDupla_jogador() + 1);
				}else if (seqPc == 3 && seqZero >= 1) {
					linha[linhaJogar].setTripla_pc(linha[linhaJogar].getTripla_pc() + 1);
				} else if (seqPc == 2 && seqZero >= 2) {
					linha[linhaJogar].setDupla_pc(linha[linhaJogar].getDupla_pc() + 1);
				}
			}
		} 
		seqJog = 0;
		seqPc++;
	}

	private void jogadorLinha(int linhaJogar) {
		if (seqPc > 0) {
			if (seqPc >= 4) {
				linha[linhaJogar].setQuadra_pc(1);
				ehFim = true;
			} else if (seqPc == 3 && zeroAntes >= 1) {
				linha[linhaJogar].setTripla_pc(linha[linhaJogar].getTripla_pc() + 1);
			} else if (seqPc == 2 && zeroAntes >= 2) {
				linha[linhaJogar].setDupla_pc(linha[linhaJogar].getDupla_pc() + 1);
			}
			zeroAntes = 0;
		} else if (seqZero > 0) {
			zeroAntes = seqZero;
			if (seqJog >= 2 || seqPc >= 2) {
				if (seqJog == 3 && seqZero >= 1) {
					linha[linhaJogar].setTripla_jogador(linha[linhaJogar].getTripla_jogador() + 1);
				} else if (seqJog == 2 && seqZero >= 2) {
					linha[linhaJogar].setDupla_jogador(linha[linhaJogar].getDupla_jogador() + 1);
				}else if (seqPc == 3 && seqZero >= 1) {
					linha[linhaJogar].setTripla_pc(linha[linhaJogar].getTripla_pc() + 1);
				} else if (seqPc == 2 && seqZero >= 2) {
					linha[linhaJogar].setDupla_pc(linha[linhaJogar].getDupla_pc() + 1);
				}
			}
		}
		seqPc = 0;
		seqJog++;
	}

	private void acabouPercorrerLinha(int linhaJogar) {
		if (seqJog >= 2 || seqPc >= 2) {
			if (seqPc >= 4) {
				linha[linhaJogar].setQuadra_pc(1);
				ehFim = true;
			} else if ((seqPc == 3 && zeroAntes >= 1) || (seqPc == 3 && seqZero >= 1) ) {
				linha[linhaJogar].setTripla_pc(linha[linhaJogar].getTripla_pc() + 1);
			} else if ((seqPc == 2 && zeroAntes >= 2) || (seqPc == 2 && seqZero >= 2)) {
				linha[linhaJogar].setDupla_pc(linha[linhaJogar].getDupla_pc() + 1);
			}
			if (seqJog >= 4) {
				linha[linhaJogar].setQuadra_jogador(1);
				ehFim = true;
			} else if ((seqJog == 3 && zeroAntes >= 1) || (seqJog == 3 && seqZero >= 1) ) {
				linha[linhaJogar].setTripla_jogador(linha[linhaJogar].getTripla_jogador() + 1);
			} else if ((seqJog == 2 && zeroAntes >= 2) || (seqJog == 2 && seqZero >= 2)) {
				linha[linhaJogar].setDupla_jogador(linha[linhaJogar].getDupla_jogador() + 1);
			}
			
		}

	}

	private void atualizaColuna(int indiceColuna, int[][] posicoes) {
		zeraValores();
		for (int i = 0; i < linha.length; i++) {
			if (posicoes[i][indiceColuna] == 0) {
				zeroRestante = linha.length - i;
				break;
			} else if (posicoes[i][indiceColuna] == 1) {
				pecaJogadorColuna(indiceColuna);
			} else if (posicoes[i][indiceColuna] == 2) {
				pecaPcColuna(indiceColuna);
			}
		}
		acabouPercorrerColuna(indiceColuna);
	}

	private void zeraValores() {
		seqJog = 0;
		seqPc = 0;
		zeroRestante = 0;
		seqZero = 0;
		zeroAntes = 0;
		zeroDepois = 0;
	}

	private void acabouPercorrerColuna(int indiceColuna) {
		if (seqJog >= 4) {
			this.coluna[indiceColuna].setQuadra_jogador(1);
			ehFim = true;
		} else if (seqJog == 3 && zeroRestante > 0) {
			this.coluna[indiceColuna].setTripla_jogador(1);
		} else if (seqJog == 2 && zeroRestante > 1) {
			this.coluna[indiceColuna].setDupla_jogador(1);
		}
		if (seqPc >= 4) {
			this.coluna[indiceColuna].setQuadra_pc(1);
			ehFim = true;
		} else if (seqPc == 3 && zeroRestante > 0) {
			this.coluna[indiceColuna].setTripla_pc(1);
		} else if (seqPc == 2 && zeroRestante > 1) {
			this.coluna[indiceColuna].setDupla_pc(1);
		}
	}

	private void pecaPcColuna(int indiceColuna) {
		if (seqJog >= 4) {
			this.coluna[indiceColuna].setQuadra_jogador(1);
			ehFim = true;
		} else {
			seqPc++;
			seqJog = 0;
		}
	}

	private void pecaJogadorColuna(int indiceColuna) {
		if (seqPc >= 4) {
			this.coluna[indiceColuna].setQuadra_pc(1);
			ehFim = true;
		} else {
			seqJog++;
			seqPc = 0;
		}
	}

	public boolean ehFim() {
		return ehFim;
	}

	public int getDifDupla() {
		int difDupla = 0;
		for (Estado estado : linha) {
			difDupla += estado.getDupla_pc() - estado.getDupla_jogador();
		}
		for (Estado estado : coluna) {
			difDupla += estado.getDupla_pc() - estado.getDupla_jogador();
		}
		for (Estado estado : diagonal) {
			difDupla += estado.getDupla_pc() - estado.getDupla_jogador();
		}
		return difDupla;
	}

	public int getDifTripla() {
		int difTripla = 0;
		for (Estado estado : linha) {
			difTripla += estado.getTripla_pc() - estado.getTripla_jogador();
		}
		for (Estado estado : coluna) {
			difTripla += estado.getTripla_pc() - estado.getTripla_jogador();
		}
		for (Estado estado : diagonal) {
			difTripla += estado.getTripla_pc() - estado.getTripla_jogador();
		}
		return difTripla;
	}

	public int getDifQuadra() {
		int difQuadra = 0;
		for (Estado estado : linha) {
			difQuadra += estado.getQuadra_pc() - estado.getQuadra_jogador();
		}
		for (Estado estado : coluna) {
			difQuadra += estado.getQuadra_pc() - estado.getQuadra_jogador();
		}
		for (Estado estado : diagonal) {
			difQuadra += estado.getQuadra_pc() - estado.getQuadra_jogador();
		}
		return difQuadra;
	}

}
