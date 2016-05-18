package controller;

import model.MinMax;
import model.Tabuleiro;
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
		if(!vezJogador)
			jogaMito();
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
				verificaFimDeJogo();
				vezJogador = false;
				jogaMito();
			} else {
				jframeLigue4.mostraMensagem("Aguarde o MITO jogar");
			}
		} else {
			jframeLigue4.mostraMensagem("Este jogo ja acabou =(");
		}
	}

	private void verificaFimDeJogo() {
		if(tabuleiroAtual.ehFinal()){
			fimDeJogo = true;
			if(!vezJogador){
				jframeLigue4.mostraMensagem("Jogo acabou, você ganhou");
			}else{
				jframeLigue4.mostraMensagem("Jogo acabou, mito ganhou");
			}
			
		}
	}

	private void jogaMito() {
		if(!fimDeJogo){
			
			int[] jogadaRealizar = minMax.buscaMelhorJogada(tabuleiroAtual.CopiaTudo(tabuleiroAtual));
			try {
				tabuleiroAtual.geraJogada(jogadaRealizar[0],  vezJogador);
			    vezJogador = true;
			} catch (ExceptionJogadaInvalida e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    jframeLigue4.imprimeTabuleiro(tabuleiroAtual.getPosicoes());
		    verificaFimDeJogo();
		}    
	}

}
