package model.estados;

import model.constantes.EnumTipoEstado;

public class TabuleiroEstado {

	private Estado estadoLinha;
	private Estado estadoColuna;
	private Estado estadoDiagonal;
	private boolean ehFim;

	public TabuleiroEstado() {
		this.estadoLinha = new Estado(EnumTipoEstado.LINHA, this);
		this.estadoColuna = new Estado(EnumTipoEstado.COLUNA, this);
		this.estadoDiagonal = new Estado(EnumTipoEstado.DIAGONAL, this);
		this.ehFim = false;
	}

	public TabuleiroEstado(Estado estadoLinha, Estado estadoColuna, Estado estadoDiagonal) {
		this.estadoLinha = estadoLinha;
		this.estadoColuna = estadoColuna;
		this.estadoDiagonal = estadoDiagonal;
	}

	public TabuleiroEstado copiaEstado() {
		TabuleiroEstado tabuleiroEstado = new TabuleiroEstado(this.estadoLinha.copiaEstado(EnumTipoEstado.LINHA), this.estadoColuna.copiaEstado(EnumTipoEstado.COLUNA),
				this.estadoDiagonal.copiaEstado(EnumTipoEstado.DIAGONAL));
		tabuleiroEstado.estadoLinha.setTabuleiroEstado(this);
		tabuleiroEstado.estadoColuna.setTabuleiroEstado(this);
		tabuleiroEstado.estadoDiagonal.setTabuleiroEstado(this);
		return tabuleiroEstado;

	}

	public void atualizaEstado(int linhaJogar, int coluna, int[][] posicoes) {
		this.estadoLinha.atualiza(linhaJogar, coluna, posicoes, EnumTipoEstado.LINHA);
		this.estadoColuna.atualiza(linhaJogar, coluna, posicoes, EnumTipoEstado.COLUNA);
		this.estadoDiagonal.atualiza(linhaJogar, coluna, posicoes, EnumTipoEstado.DIAGONAL);
	}

	public boolean ehFim() {
		return this.ehFim;
	}

	public void setFim() {
		this.ehFim = true;
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
