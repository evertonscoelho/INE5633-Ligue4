package model;

import java.util.List;

public class MinMax {
    
    private int profundidadeAtual;
    private int profundidadeMaxima = 10;


    public Tabuleiro buscaMelhorJogada(Tabuleiro tabuleiroAtual) {
        profundidadeAtual = 0;
        
        List<Jogada> jogadas = tabuleiroAtual.getJogadasPossiveis();
        
        Jogada melhorJogada = null;
        
        int melhorScore = Integer.MIN_VALUE;
        
        
        for(Jogada jogada : jogadas){
            int score = partiuProximo(jogada, tabuleiroAtual, true);
            if(score > melhorScore){
                melhorScore = score;
                melhorJogada = jogada;
            }
        }
        
        return melhorJogada;
    }
    
    private int partiuProximo(Jogada jogada, Tabuleiro tabuleiroAtual, boolean max) {
        Tabuleiro novoTabuleiro = tabuleiroAtual.jogar(jogada);
        
        if(novoTabuleiro.ehFinal() 
                || profundidadeAtual == profundidadeMaxima){
            return novoTabuleiro.getValor();
        } else {
            List<Jogada> jogadas = novoTabuleiro.getJogadasPossiveis();
        
            int melhorValor;
            if(max)
            	melhorValor = Integer.MIN_VALUE;
            else 
            	melhorValor = Integer.MAX_VALUE;
            
            for(Jogada jogadaFor : jogadas){
                int valor = partiuProximo(jogadaFor, novoTabuleiro, !max);
                
                if((max && valor > melhorValor) 
                        || (!max && valor < melhorValor)){
                    melhorValor = valor;
                }
            }
            
            
            return melhorValor;
        }
    }*/
    
}
