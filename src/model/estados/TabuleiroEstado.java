package model;

public class TabuleiroEstado {

	private Estado[] linha;
	private Estado[] coluna;
	private Estado[] diagonal;
	private boolean ehFim;
	private int seqJog = 0, seqPc = 0, zeroRestante = 0, seqZero = 0;

	public TabuleiroEstado() {
		linha = new Estado[Constantes.LINHAS];
		coluna = new Estado[Constantes.COLUNAS];
		diagonal = new Estado[Constantes.DIAGONAIS];
		init();
		ehFim = false;
	}
	
	public TabuleiroEstado copiaEstado(){
		TabuleiroEstado aux = new TabuleiroEstado();
		for(int i = 0; i < linha.length; i++){
			aux.setLinha(i, new Estado(linha[i].getDupla_jogador(), linha[i].getDupla_pc(), linha[i].getTripla_jogador(),
					 linha[i].getTripla_pc(), linha[i].getQuadra_jogador(), linha[i].getQuadra_pc()));
		}
		for(int i = 0; i < coluna.length; i++){
			aux.setColuna(i, new Estado(coluna[i].getDupla_jogador(), coluna[i].getDupla_pc(), coluna[i].getTripla_jogador(),
					coluna[i].getTripla_pc(), coluna[i].getQuadra_jogador(), coluna[i].getQuadra_pc()));
		}
		for(int i = 0; i < diagonal.length; i++){
			aux.setDiagonal(i, new Estado(diagonal[i].getDupla_jogador(), diagonal[i].getDupla_pc(), diagonal[i].getTripla_jogador(),
					diagonal[i].getTripla_pc(), diagonal[i].getQuadra_jogador(), diagonal[i].getQuadra_pc()));
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
		for(int i = 0; i < linha.length; i++){
			linha[i] = new Estado();
		}
		for(int i = 0; i < coluna.length; i++){
			coluna[i] = new Estado();
		}
		for(int i = 0; i < diagonal.length; i++){
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
				if (seqJog > 0)
					pecaJogadorLinha(linhaJogar);
				else if (seqPc > 0)
					pecaPcLinha(linhaJogar);

			} else if (posicoes[linhaJogar][i] == 1) {
				pecaJogadorLinha(linhaJogar);
			} else if (posicoes[linhaJogar][i] == 2) {
				pecaPcLinha(linhaJogar);
			}
		}

	}

	private void pecaJogadorLinha(int linhaJogar) {
		seqJog++;
		if (seqPc > 0) {
			seqZero = 0;
			seqPc = 0;
		}

		if (seqJog >= 4) {
			this.linha[linhaJogar].setQuadra_jogador(1);
			ehFim = true;
		} else if (seqJog == 3 && zeroRestante > 0) {
			this.linha[linhaJogar].setTripla_jogador(1);
		} else if (seqJog == 2 && zeroRestante > 1) {
			this.linha[linhaJogar].setDupla_jogador(1);
		}
	}

	private void pecaPcLinha(int linhaJogar) {
		seqPc++;
		if (seqJog > 0) {
			seqZero = 0;
			seqJog = 0;
		}
		seqZero = 0;

		if (seqPc >= 4) {
			this.linha[linhaJogar].setQuadra_pc(1);
			ehFim = true;
		} else if (seqPc == 3 && zeroRestante > 0) {
			this.linha[linhaJogar].setTripla_pc(1);
		} else if (seqPc == 2 && zeroRestante > 1) {
			this.linha[linhaJogar].setDupla_pc(1);
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
	//	return ehFim;
		return false;
	}

	public int getDifDupla() {
		int difDupla = 0;
		for (Estado estado : linha) {
			difDupla += estado.getDupla_pc() -estado.getDupla_jogador();
		}
		for (Estado estado : coluna) {
			difDupla += estado.getDupla_pc() -estado.getDupla_jogador();
		}
		for (Estado estado : diagonal) {
			difDupla += estado.getDupla_pc() -estado.getDupla_jogador();
		}
		return difDupla;
	}

	public int getDifTripla() {
		int difTripla = 0;
		for (Estado estado : linha) {
			difTripla += estado.getTripla_pc() -estado.getTripla_jogador();
		}
		for (Estado estado : coluna) {
			difTripla += estado.getTripla_pc() -estado.getTripla_jogador();
		}
		for (Estado estado : diagonal) {
			difTripla += estado.getTripla_pc() -estado.getTripla_jogador();
		}
		return difTripla;
	}

	public int getDifQuadra() {
		int difQuadra = 0;
		for (Estado estado : linha) {
			difQuadra += estado.getQuadra_pc() -estado.getQuadra_jogador();
		}
		for (Estado estado : coluna) {
			difQuadra += estado.getQuadra_pc() -estado.getQuadra_jogador();
		}
		for (Estado estado : diagonal) {
			difQuadra += estado.getQuadra_pc() -estado.getQuadra_jogador();
		}
		return difQuadra;
	}

}
