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
			aux.setEstadoSituacao(i, new EstadoSituacao(this.estado[i].getDupla_jogador(), this.estado[i].getDupla_pc(), this.estado[i].getTripla_jogador(),
					this.estado[i].getTripla_pc(), this.estado[i].getQuadra_jogador(), this.estado[i].getQuadra_pc()));
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
			/*LinkedList<DiagonaisDTO>  diagonais = getDiagonais(posicoes);
			for(DiagonaisDTO diagonaisDTO : diagonais){
				this.estado = estadoHelper.atualizaEstado(diagonaisDTO.getPosicoes(), this.estado, this, diagonaisDTO.getId());	
			}*/
			break;

		default:
			break;
		}
	}

	private LinkedList<DiagonaisDTO>  getDiagonais(int[][] posicoes) {
		LinkedList<DiagonaisDTO> retorno = new LinkedList<DiagonaisDTO>();
		//BOTA LOGICA AQUI PEDRINHO
		
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
