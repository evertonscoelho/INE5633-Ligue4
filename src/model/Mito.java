package model;

import Excecao.ExceptionJogadaInvalida;
import view.JFrameLigue4;

public class Mito {

	private JFrameLigue4 jframeLigue4;
	private Tabuleiro tabuleiroAtual;
	private MinMax minMax;
	private boolean vezJogador;
	private boolean fimDeJogo;

	public Mito() {
		minMax = new MinMax();
		tabuleiroAtual = new Tabuleiro();
	}

	public void interaja() {
		jframeLigue4 = new JFrameLigue4(this);
		jframeLigue4.interaja();
		vezJogador = jframeLigue4.getVezJogador();
	}

	public void posicaoClicada(int coluna) {
		// Atualiza tabuleiro com click
		if (!fimDeJogo) {
			if (vezJogador) {
				try {
					tabuleiroAtual.geraJogada(coluna, vezJogador);
				} catch (ExceptionJogadaInvalida e) {
					jframeLigue4.mostraMensagem("Jogada invalida");
				}
				// atualiza tabuleiro na tela
				jframeLigue4.imprimeTabuleiro(tabuleiroAtual.getPosicoes());
				verificaFimDeJogo(tabuleiroAtual, vezJogador);
				vezJogador = false;
				jogaMito();
			} else {
				jframeLigue4.mostraMensagem("Aguarde o MITO jogar");
			}
		} else {
			jframeLigue4.mostraMensagem("Este jogo ja acabou =(");
		}
	}

	private void verificaFimDeJogo(Tabuleiro tabuleiroAtual2, boolean vezJogador2) {
		// TODO Auto-generated method stub
		fimDeJogo = true;
	}

	private void jogaMito() {
		if(!fimDeJogo){
		    tabuleiroAtual = minMax.buscaMelhorJogada(tabuleiroAtual);
		    jframeLigue4.imprimeTabuleiro(tabuleiroAtual.getPosicoes());
		    verificaFimDeJogo(tabuleiroAtual, vezJogador);
		    vezJogador = true;
		}    
	}

}
