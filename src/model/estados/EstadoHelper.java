package model.estados;

public class EstadoHelper {

	EstadoSituacao[] estadoSituacao;
	Estado estado;
	int seqJogador;
	int seqPC;
	int seqVazio;
	int seqVazioAnterior;
	int indice;

	public EstadoSituacao[] atualizaEstado(int[] posicoes, EstadoSituacao[] estadoSituacaos, Estado estado, int indice) {
		this.estadoSituacao = estadoSituacaos;
		this.indice = indice;
		this.estado = estado;
		zeraValores();
		for (int posicao : posicoes) {
			if (posicao == 0) {
				this.leuZero();
			} else if (posicao == 1) {
				this.leuJogador();
			} else {
				this.leuPc();
			}
		}
		this.fimLeitura();
		return this.estadoSituacao;
	}

	private void zeraValores() {
		estadoSituacao[indice].setDupla_jogador(0);
		estadoSituacao[indice].setDupla_pc(0);
		estadoSituacao[indice].setTripla_jogador(0);
		estadoSituacao[indice].setTripla_pc(0);
		estadoSituacao[indice].setQuadra_jogador(0);
		estadoSituacao[indice].setQuadra_pc(0);
	}

	private void fimLeitura() {
		this.descobreSeq(this.seqPC, this.seqVazio+seqVazioAnterior, false);
		this.descobreSeq(this.seqJogador, this.seqVazio+seqVazioAnterior, true);
	}

	private void leuPc() {
		if(seqJogador >= 2){
			this.descobreSeq(this.seqPC, this.seqVazio+seqVazioAnterior, false);
		}
		seqVazioAnterior = seqVazio;
		seqVazio = 0;
		seqJogador = 0;
		seqPC++;
	}

	private void leuJogador() {
		if(seqPC >= 2){
			this.descobreSeq(this.seqJogador, this.seqVazio+seqVazioAnterior, true);
		}
		seqVazioAnterior = seqVazio;
		seqVazio = 0;
		seqPC = 0;
		seqJogador++;

	}

	private void leuZero() {
		this.seqVazio++;
	}

	private void descobreSeq(int valor, int seqZero, boolean jog) {
		boolean quadra = false, dupla = false, tripla = false;
		if (valor >= 4) {
			quadra = true;
			this.estado.setFim();
		} else if (valor == 3 && seqZero >= 1) {
			tripla = true;
		} else if (valor == 2 && seqZero >= 2) {
			dupla = true;
		}
		if (jog) {
			if (quadra) {
				this.estadoSituacao[this.indice].setQuadra_jogador(this.estadoSituacao[this.indice].getQuadra_jogador());
			}
			if (tripla) {
				this.estadoSituacao[this.indice].setTripla_jogador(this.estadoSituacao[this.indice].getTripla_jogador());
			}
			if (dupla) {
				this.estadoSituacao[this.indice].setDupla_jogador(this.estadoSituacao[this.indice].getDupla_jogador());
			}
		} else {
			if (quadra) {
				this.estadoSituacao[this.indice].setQuadra_pc(this.estadoSituacao[this.indice].getQuadra_pc());
			}
			if (tripla) {
				this.estadoSituacao[this.indice].setTripla_pc(this.estadoSituacao[this.indice].getTripla_pc());
			}
			if (dupla) {
				this.estadoSituacao[this.indice].setDupla_pc(this.estadoSituacao[this.indice].getDupla_pc());
			}
		}
	}
}
