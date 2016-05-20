package model.estados;

import model.constantes.EnumTipoEstado;

public class TabuleiroEstado {

	private Estado estadoLinha;
	private Estado estadoColuna;
	private Estado estadoDiagonal;
	private boolean ehFim;

	public TabuleiroEstado() {
		this.estadoLinha = new Estado();
		this.estadoColuna = new Estado();
		this.estadoDiagonal = new Estado();
		this.ehFim = false;
	}

	public TabuleiroEstado(Estado estadoLinha, Estado estadoColuna, Estado estadoDiagonal) {
		this.estadoLinha = estadoLinha;
		this.estadoColuna = estadoColuna;
		this.estadoDiagonal = estadoDiagonal;
	}

	public TabuleiroEstado copiaEstado() {
		return new TabuleiroEstado(this.estadoLinha.copiaEstado(), this.estadoColuna.copiaEstado(), this.estadoDiagonal.copiaEstado());
	}

	public void atualizaEstado(int linhaJogar, int coluna, int[][] posicoes) {
		this.estadoLinha.atualiza(linhaJogar, coluna, posicoes, EnumTipoEstado.LINHA);
		this.estadoColuna.atualiza(linhaJogar, coluna, posicoes, EnumTipoEstado.COLUNA);
		this.estadoDiagonal.atualiza(linhaJogar, coluna, posicoes, EnumTipoEstado.DIAGONAL);
	}

	public boolean ehFim() {
		return this.ehFim;
	}

	public void setFim(boolean ehFim) {
		this.ehFim = ehFim;
	}

	public int getDifDupla() {
		return this.estadoLinha.getDifDupla() + this.estadoColuna.getDifDupla() + this.estadoDiagonal.getDifDupla();
	}

	public int getDifTripla() {
		return this.estadoLinha.getDifTripla() + this.estadoColuna.getDifTripla() + this.estadoDiagonal.getDifTripla();
	}

	public int getDifQuadra() {
		return this.estadoLinha.getDifQuadra() + this.estadoColuna.getDifQuadra() + this.estadoDiagonal.getDifQuadra();
	}

}
