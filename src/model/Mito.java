package model;

import view.JFrameLigue4;

public class Mito {

	private JFrameLigue4 jframeLigue4;
	public void interaja() {
		jframeLigue4 = new JFrameLigue4(this);
		jframeLigue4.interaja();
		
	}

	public void posicaoClicada(int coluna) {
		//Atualiza tabuleiro com click
		
		int m[][] = { {0, 0, 0, 0, 0, 0,0}, {0, 0, 0, 0, 0, 0,0}, {0, 0, 0, 0, 0, 0,0}, {0, 0, 0, 0, 0, 0,0}, {2, 2, 0, 0, 2, 1,1}, {1, 1, 0, 1, 1, 2,1}};

        jframeLigue4.imprimeTabuleiro(m);
		// TODO Auto-generated method stub
		
	}

  

}
