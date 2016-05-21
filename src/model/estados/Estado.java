package model.estados;

import java.awt.List;
import java.util.LinkedList;

import model.constantes.Constantes;
import model.constantes.EnumTipoEstado;

public class Estado {
	private EstadoSituacao[] estado;
	private TabuleiroEstado tabuleiroEstado;

	public Estado(EnumTipoEstado enumTipoEstado, TabuleiroEstado tabuleiroEstado) {
		this.tabuleiroEstado = tabuleiroEstado;
		switch (enumTipoEstado) {
		case LINHA:
			this.estado = new EstadoSituacao[Constantes.LINHAS];
			break;
		case COLUNA:
			this.estado = new EstadoSituacao[Constantes.COLUNAS];
			break;
		case DIAGONAL:
			this.estado = new EstadoSituacao[Constantes.DIAGONAIS];
			break;
		}

		this.init();
	}

	public Estado copiaEstado(EnumTipoEstado enumTipoEstado) {
		Estado aux = new Estado(enumTipoEstado, this.tabuleiroEstado);
		for (int i = 0; i < this.estado.length; i++) {
			aux.setEstadoSituacao(i,
					new EstadoSituacao(this.estado[i].getDupla_jogador(), this.estado[i].getDupla_pc(),
							this.estado[i].getTripla_jogador(), this.estado[i].getTripla_pc(),
							this.estado[i].getQuadra_jogador(), this.estado[i].getQuadra_pc()));
		}
		return aux;
	}

	private void setEstadoSituacao(int i, EstadoSituacao estado) {
		this.estado[i] = estado;
	}

	public void atualiza(int linhaJogar, int coluna, int[][] posicoes, EnumTipoEstado enumTipoEstado) {
		EstadoHelper estadoHelper = new EstadoHelper();
		switch (enumTipoEstado) {
		case LINHA:
			this.estado = estadoHelper.atualizaEstado(posicoes[linhaJogar], this.estado, this, linhaJogar);
			break;
		case COLUNA:
			int[] posicoesColuna = new int[Constantes.COLUNAS];
			for (int i = 0; i < Constantes.LINHAS; i++) {
				posicoesColuna[i] = posicoes[i][coluna];
			}
			this.estado = estadoHelper.atualizaEstado(posicoesColuna, this.estado, this, coluna);
			break;
		case DIAGONAL:
			LinkedList<DiagonaisDTO> diagonais = getDiagonais(posicoes, linhaJogar, coluna);
			for (DiagonaisDTO diagonaisDTO : diagonais) {
				this.estado = estadoHelper.atualizaEstado(diagonaisDTO.getPosicoes(), this.estado, this,
						diagonaisDTO.getId());
			}
			break;

		default:
			break;
		}
	}

	private LinkedList<DiagonaisDTO> getDiagonais(int[][] posicoes, int linhaJogar, int coluna) {
		LinkedList<DiagonaisDTO> retorno = new LinkedList<DiagonaisDTO>();

		// direita pra esquerda diagonais
		int[] posicoesDiagonal;
		DiagonaisDTO diagonal;
		if (linhaJogar - coluna == -1) {
			posicoesDiagonal = new int[5];
			posicoesDiagonal[0] = posicoes[0][1];
			posicoesDiagonal[1] = posicoes[1][2];
			posicoesDiagonal[2] = posicoes[3][4];
			posicoesDiagonal[3] = posicoes[4][5];
			posicoesDiagonal[4] = posicoes[5][6];
			diagonal = new DiagonaisDTO(posicoesDiagonal, 0);
			retorno.add(diagonal);
		} else if (linhaJogar - coluna == -2) {
			posicoesDiagonal = new int[5];
			posicoesDiagonal[0] = posicoes[0][2];
			posicoesDiagonal[1] = posicoes[1][3];
			posicoesDiagonal[2] = posicoes[2][4];
			posicoesDiagonal[3] = posicoes[3][5];
			posicoesDiagonal[4] = posicoes[4][6];
			diagonal = new DiagonaisDTO(posicoesDiagonal, 1);
			retorno.add(diagonal);
		} else if (linhaJogar - coluna == -3) {
			posicoesDiagonal = new int[4];
			posicoesDiagonal[0] = posicoes[0][3];
			posicoesDiagonal[1] = posicoes[1][4];
			posicoesDiagonal[2] = posicoes[2][5];
			posicoesDiagonal[3] = posicoes[3][6];
			diagonal = new DiagonaisDTO(posicoesDiagonal, 2);
			retorno.add(diagonal);
		} else if (linhaJogar - coluna == 0) {
			posicoesDiagonal = new int[6];
			posicoesDiagonal[0] = posicoes[0][0];
			posicoesDiagonal[1] = posicoes[1][1];
			posicoesDiagonal[2] = posicoes[2][2];
			posicoesDiagonal[3] = posicoes[3][3];
			posicoesDiagonal[4] = posicoes[4][4];
			posicoesDiagonal[5] = posicoes[5][5];
			diagonal = new DiagonaisDTO(posicoesDiagonal, 3);
			retorno.add(diagonal);
		} else if (linhaJogar - coluna == 1) {
			posicoesDiagonal = new int[5];
			posicoesDiagonal[0] = posicoes[1][0];
			posicoesDiagonal[1] = posicoes[2][1];
			posicoesDiagonal[2] = posicoes[3][2];
			posicoesDiagonal[3] = posicoes[4][3];
			posicoesDiagonal[3] = posicoes[5][4];
			diagonal = new DiagonaisDTO(posicoesDiagonal, 4);
			retorno.add(diagonal);
		} else if (linhaJogar - coluna == 2) {
			posicoesDiagonal = new int[4];
			posicoesDiagonal[0] = posicoes[2][0];
			posicoesDiagonal[1] = posicoes[3][1];
			posicoesDiagonal[2] = posicoes[4][2];
			posicoesDiagonal[3] = posicoes[5][3];
			diagonal = new DiagonaisDTO(posicoesDiagonal, 5);
			retorno.add(diagonal);
		}

		// esquerda pra direita diagonais
		if (linhaJogar + coluna == 3) {
			posicoesDiagonal = new int[4];
			posicoesDiagonal[0] = posicoes[3][0];
			posicoesDiagonal[1] = posicoes[2][1];
			posicoesDiagonal[2] = posicoes[1][2];
			posicoesDiagonal[3] = posicoes[0][3];
			diagonal = new DiagonaisDTO(posicoesDiagonal, 6);
			retorno.add(diagonal);
		} else if (linhaJogar + coluna == 4) {
			posicoesDiagonal = new int[5];
			posicoesDiagonal[0] = posicoes[4][0];
			posicoesDiagonal[1] = posicoes[3][1];
			posicoesDiagonal[2] = posicoes[2][2];
			posicoesDiagonal[3] = posicoes[1][3];
			posicoesDiagonal[4] = posicoes[0][4];
			diagonal = new DiagonaisDTO(posicoesDiagonal, 7);
			retorno.add(diagonal);
		} else if (linhaJogar + coluna == 5) {
			posicoesDiagonal = new int[6];
			posicoesDiagonal[0] = posicoes[5][0];
			posicoesDiagonal[1] = posicoes[4][1];
			posicoesDiagonal[2] = posicoes[3][2];
			posicoesDiagonal[3] = posicoes[2][3];
			posicoesDiagonal[4] = posicoes[1][4];
			posicoesDiagonal[4] = posicoes[0][5];
			diagonal = new DiagonaisDTO(posicoesDiagonal, 8);
			retorno.add(diagonal);
		} else if (linhaJogar + coluna == 6) {
			posicoesDiagonal = new int[6];
			posicoesDiagonal[0] = posicoes[5][1];
			posicoesDiagonal[1] = posicoes[4][2];
			posicoesDiagonal[2] = posicoes[3][3];
			posicoesDiagonal[3] = posicoes[2][4];
			posicoesDiagonal[4] = posicoes[1][5];
			posicoesDiagonal[4] = posicoes[0][6];
			diagonal = new DiagonaisDTO(posicoesDiagonal, 9);
			retorno.add(diagonal);
		} else if (linhaJogar + coluna == 7) {
			posicoesDiagonal = new int[5];
			posicoesDiagonal[0] = posicoes[5][2];
			posicoesDiagonal[1] = posicoes[4][3];
			posicoesDiagonal[2] = posicoes[3][4];
			posicoesDiagonal[3] = posicoes[2][5];
			posicoesDiagonal[4] = posicoes[1][6];
			diagonal = new DiagonaisDTO(posicoesDiagonal, 10);
			retorno.add(diagonal);
		} else if (linhaJogar + coluna == 8) {
			posicoesDiagonal = new int[4];
			posicoesDiagonal[0] = posicoes[5][3];
			posicoesDiagonal[1] = posicoes[4][4];
			posicoesDiagonal[2] = posicoes[3][5];
			posicoesDiagonal[3] = posicoes[2][6];
			diagonal = new DiagonaisDTO(posicoesDiagonal, 11);
			retorno.add(diagonal);
		}

		return retorno;

	}

	public int getDifDupla() {
		int dif = 0;
		for (EstadoSituacao estado : this.estado) {
			dif += estado.getDupla_pc() - estado.getDupla_jogador();
		}
		return dif;
	}

	public int getDifTripla() {
		int dif = 0;
		for (EstadoSituacao estado : this.estado) {
			dif += estado.getTripla_pc() - estado.getTripla_jogador();
		}
		return dif;
	}

	public int getDifQuadra() {
		int dif = 0;
		for (EstadoSituacao estado : this.estado) {
			dif += estado.getQuadra_pc() - estado.getQuadra_jogador();
		}
		return dif;
	}

	public EstadoSituacao[] init() {
		for (int i = 0; i < this.estado.length; i++) {
			this.estado[i] = new EstadoSituacao();
		}
		return this.estado;
	}

	public void setFim() {
		this.tabuleiroEstado.setFim();

	}

	public void setTabuleiroEstado(TabuleiroEstado tabuleiroEstado) {
		this.tabuleiroEstado = tabuleiroEstado;

	}

}
