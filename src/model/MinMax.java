package model;

import java.util.List;

public class MinMax {

	private int profundidadeAtual;
	private int profundidadeMaxima = 10;

	public Tabuleiro buscaMelhorJogada(Tabuleiro tabuleiroAtual) {
		profundidadeAtual = 0;

		List<Tabuleiro> jogadas = geraJogadasPossiveis();

		Tabuleiro melhorJogada = null;

		int melhorScore = Integer.MIN_VALUE;

		for (Tabuleiro jogada : jogadas) {
			int score = partiuProximo(jogada, true);
			if (score > melhorScore) {
				melhorScore = score;
				melhorJogada = jogada;
			}
		}

		return melhorJogada;
	}

	private List<Tabuleiro> geraJogadasPossiveis() {
		// TODO Auto-generated method stub
		return null;
	}

	private int partiuProximo(Tabuleiro jogada, boolean max) {

		if (jogada.ehFinal() || profundidadeAtual == profundidadeMaxima) {
			return jogada.getValor();
		} else {
			List<Tabuleiro> jogadas = geraJogadasPossiveis();

			int melhorValor;
			if (max)
				melhorValor = Integer.MIN_VALUE;
			else
				melhorValor = Integer.MAX_VALUE;

			for (Tabuleiro proxTabuleiro : jogadas) {
				int valor = partiuProximo(proxTabuleiro, !max);

				if ((max && valor > melhorValor) || (!max && valor < melhorValor)) {
					melhorValor = valor;
				}
			}

			return melhorValor;
		}
	}

}
