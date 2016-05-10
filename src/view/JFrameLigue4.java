package view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Mito;

public class JFrameLigue4 extends JFrame {

	private JPanelLigue4 panel;
	private Mito mito;

	public JFrameLigue4(Mito mito) {
		// Nao traduzido
		super("Ligue 4");
		this.mito = mito;
		panel = new JPanelLigue4(this);
		setContentPane(panel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(500, 550));
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
	}

	public void interaja() {
		setVisible(true);
	}

	public void mostraMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}

	public void imprimeTabuleiro(int[][] tabuleiro) {
		panel.atualizaTabuleiro(tabuleiro);
		remove(panel);
		this.setContentPane(panel);
	}

	public void posicaoClick(int coluna) {
		mito.posicaoClicada(coluna);
	}

	public boolean getVezJogador() {
		String[] escolha = {"Humano", "Mito", "Yu-gi-Yo"};
		int resp = JOptionPane.showOptionDialog(null, "Quem comeca esse grande duelo?", "Quem?", 0, JOptionPane.QUESTION_MESSAGE, null, escolha, escolha[0]);
		if(resp == 0){
			return true;
		}else{
			return false;
		}
	}

}
