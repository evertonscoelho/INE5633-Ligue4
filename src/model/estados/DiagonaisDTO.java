package model.estados;

public class DiagonaisDTO {

	int[] posicoes;
	int idDiagonal;
	
	public DiagonaisDTO(int[] posicoesDiagonal, int i) {
		this.posicoes = posicoesDiagonal;
		this.idDiagonal = i;
	}
	public int[] getPosicoes() {
		return posicoes;
	}
	public int getId() {
		return idDiagonal;
	}
	
	public void setId(int id){
		this.idDiagonal = id;
	}
	
	public void setPosicoes(int[] posicoes){
		this.posicoes = posicoes;
	}
	

}
