package model;

public class Estado {

	private int dupla_jogador;
	private int dupla_pc;
	private int tripla_jogador;
	private int tripla_pc;
	private int quadra_jogador;
	private int quadra_pc;

	
	public Estado(int dupla_jogador, int dupla_pc, int tripla_jogador,
			int tripla_pc, int quadra_jogador, int quadra_pc) {
		super();
		this.dupla_jogador = dupla_jogador;
		this.dupla_pc = dupla_pc;
		this.tripla_jogador = tripla_jogador;
		this.tripla_pc = tripla_pc;
		this.quadra_jogador = quadra_jogador;
		this.quadra_pc = quadra_pc;
	}
	
	public Estado(){
		//TODO
	}
	
	public int getDupla_jogador() {
		return dupla_jogador;
	}

	public void setDupla_jogador(int dupla_jogador) {
		this.dupla_jogador = dupla_jogador;
	}

	public int getDupla_pc() {
		return dupla_pc;
	}

	public void setDupla_pc(int dupla_pc) {
		this.dupla_pc = dupla_pc;
	}

	public int getTripla_jogador() {
		return tripla_jogador;
	}

	public void setTripla_jogador(int tripla_jogador) {
		this.tripla_jogador = tripla_jogador;
	}

	public int getTripla_pc() {
		return tripla_pc;
	}

	public void setTripla_pc(int tripla_pc) {
		this.tripla_pc = tripla_pc;
	}

	public int getQuadra_jogador() {
		return quadra_jogador;
	}

	public void setQuadra_jogador(int quadra_jogador) {
		this.quadra_jogador = quadra_jogador;
	}

	public int getQuadra_pc() {
		return quadra_pc;
	}

	public void setQuadra_pc(int quadra_pc) {
		this.quadra_pc = quadra_pc;
	}

}
