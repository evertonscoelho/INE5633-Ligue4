package view;

import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.constantes.Constantes;

public class JPanelLigue4 extends JPanel {
	
	
		
	private JLabel tabuleiro[][] = new JLabel[Constantes.LINHAS][Constantes.COLUNAS];
	private Icon vazia;
	private Icon pecaHumano;
	private Icon pecaPC;
	private JFrameLigue4 jFrameLigue4; 
		
	public JPanelLigue4(JFrameLigue4 jFrameLigue4) {
		setBorder(BorderFactory.createTitledBorder("Ligue 4"));
		setSize(150, 150);
		this.setLayout(null);
		initComponents();
		this.jFrameLigue4 = jFrameLigue4;
	    criaTabuleiro();
	}
	private void initComponents() {
		
		ClassLoader cl = this.getClass().getClassLoader();
		vazia = new ImageIcon(cl.getResource("pecaVazia.gif"));
		pecaHumano = new ImageIcon(cl.getResource("pecaHumano.gif"));
		pecaPC = new ImageIcon(
				cl.getResource("pecaPC.gif"));

	}

	public void criaTabuleiro() {
		int y = 70;

		for (int linha = 5; linha >= 0; linha--) {
			int x = 50;
			for (int coluna = 6; coluna >= 0; coluna--) {
				final Integer auxLinha = linha;
				final Integer auxColuna = coluna;
				tabuleiro[linha][coluna] = new JLabel();
				tabuleiro[linha][coluna].setBounds(new Rectangle(x, y, 50, 50));
				tabuleiro[linha][coluna].setIcon(vazia);
				x += 50;
				this.add(tabuleiro[linha][coluna]);
				tabuleiro[linha][coluna]
						.addMouseListener(new java.awt.event.MouseAdapter() {
							public void mouseClicked(java.awt.event.MouseEvent e) {
								posicaoClick(auxLinha, auxColuna);
							}
						});
			}
			y += 50;

		}
	}
	
	public void atualizaTabuleiro(int[][] tabuleiroInterface) {
		for (int linha = 0; linha < Constantes.LINHAS; linha++) {
			for (int coluna = 0; coluna < Constantes.COLUNAS; coluna++) {
				if(tabuleiroInterface[linha][coluna] == 0){
					tabuleiro[linha][coluna].setIcon(vazia);
				}else if(tabuleiroInterface[linha][coluna] == 1){
					tabuleiro[linha][coluna].setIcon(pecaHumano);
				}else{
					tabuleiro[linha][coluna].setIcon(pecaPC);
				}
			}
		}
	}

	protected void posicaoClick(Integer auxLinha, Integer auxColuna) {
		jFrameLigue4.posicaoClick(auxColuna);
		
	}

}
