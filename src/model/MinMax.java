package model;

import java.util.List;

import model.constantes.Constantes;

public class MinMax {

	private int profundidadeMaxima = Constantes.PROFUNDIDADE_MAXIMA;
	private int[] jogadaRealizar = new int[2];

	public Tabuleiro buscaMelhorJogada(Tabuleiro tabuleiroAtual) {
		minMax(new Nodo(tabuleiroAtual), 1, true, -8000000,
				+8000000);
		tabuleiroAtual.geraJogada(jogadaRealizar[0], jogadaRealizar[1], false);
		return tabuleiroAtual;
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
					valorFilho = minMax(nodo.geraFilho(filho, false), nivel + 1,
							!max, alpha, beta);
					valorFilho = valorFilho / ( nivel*0.05);
					if (alpha < valorFilho) {
						alpha = valorFilho;
						jogadaRealizar[0] = filho[0];
						jogadaRealizar[1] = filho[1];
						if (alpha >= beta) {
							return alpha;
						}
					}
				}
				return alpha;
			} else {
				for (int[] filho : filhosPossiveis) {
					valorFilho = minMax(nodo.geraFilho(filho, true), nivel + 1,
							!max, alpha, beta);
					valorFilho = valorFilho / ( nivel*0.05);
					if (beta > valorFilho) {
						beta = valorFilho;
						jogadaRealizar[0] = filho[0];
						jogadaRealizar[1] = filho[1];
						if (alpha >= beta) {
							return beta;
						}
					}
				}
				return beta;
			}
		}
	}
}
