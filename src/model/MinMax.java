package model;

import java.util.List;

public class MinMax {

	private int profundidadeMaxima = Constantes.PROFUNDIDADE_MAXIMA;
	private int[] jogadaRealizar;

	public int[] buscaMelhorJogada(Tabuleiro tabuleiroAtual) {
		minMax(new Nodo(tabuleiroAtual), 1, true, Double.MIN_VALUE,
				Double.MAX_VALUE);
		return jogadaRealizar;
	}

	private double minMax(Nodo nodo, int nivel, boolean max, double alpha,
			double beta) {
		if (nodo.getTabuleiro().ehFinal() || nivel == profundidadeMaxima) {
			return nodo.getValor();
		} else {
			List<int[]> filhosPossiveis = nodo.geraFilhosPossiveis();
			double valorFilho;
			if (max) {
				for (int[] filho : filhosPossiveis) {
					valorFilho = minMax(nodo.geraFilho(filho, !max), nivel + 1,
							!max, alpha, beta);
					if (alpha < valorFilho) {
						alpha = valorFilho;
						if (alpha >= beta) {
							jogadaRealizar = filho;
							return alpha;
						}
					}
				}
				return alpha;
			} else {
				for (int[] filho : filhosPossiveis) {
					valorFilho = minMax(nodo.geraFilho(filho, !max), nivel + 1,
							!max, alpha, beta);
					if (beta > valorFilho) {
						beta = valorFilho;
						if (alpha >= beta) {
							jogadaRealizar = filho;
							return beta;
						}
					}
				}
				return beta;
			}
		}
	}
}
