package src.trabalho;

import deque.ArrayDeque;
import stack.Stack;

public class Turno {
    private Jogador jogador;
    private ArrayDeque<String> CartasMesa;
    private Stack<String> MonteCartas;

    public Turno(Jogador jogador, Stack<String> MonteCartas, ArrayDeque<String> CartasMesa) {
        this.jogador = jogador;
        this.MonteCartas = MonteCartas;
        this.CartasMesa = CartasMesa;
    }

    public void AddCartaEsquerda(String carta) {
        if (carta.equalsIgnoreCase(CartasMesa.first())) {
            jogador.CartasColetadas.push(CartasMesa.removeFirst());
        }
    }

    public void AddCartaDireita(String carta, Jogador jogador) {
        if (carta.equalsIgnoreCase(CartasMesa.last())) {
            jogador.CartasColetadas.push(CartasMesa.removeLast());
        }
    }

}

