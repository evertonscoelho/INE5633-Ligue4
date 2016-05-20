package model.estados;

import model.EstadoSituacao;
import model.constantes.Constantes;
import model.constantes.EnumTipoEstado;

public class Estado {
	private EstadoSituacao[] estado;

	public Estado() {
		this.estado = new EstadoSituacao[Constantes.LINHAS];
		this.init();
	}

	public Estado copiaEstado() {
		Estado aux = new Estado();
		for (int i = 0; i < this.estado.length; i++) {
			aux.setEstadoSituacao(i, new EstadoSituacao(this.estado[i].getDupla_jogador(), this.estado[i].getDupla_pc(), this.estado[i].getTripla_jogador(),
					this.estado[i].getTripla_pc(), this.estado[i].getQuadra_jogador(), this.estado[i].getQuadra_pc()));
		}
		return aux;
	}

	private void setEstadoSituacao(int i, EstadoSituacao estado) {
		this.estado[i] = estado;
	}

	public void atualiza(int linhaJogar, int coluna, int[][] posicoes, EnumTipoEstado enumTipoEstado) {
		switch (enumTipoEstado) {
		case LINHA:

			break;
		case COLUNA:

			break;
		case DIAGONAL:

			break;

		default:
			break;
		}
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

}
