package src.trabalho;

import deque.ArrayDeque;
import stack.ArrayStack;
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

    public boolean addCartaEsquerda(String carta) {
        ArrayDeque<String> AuxDeque = new ArrayDeque<>();
        boolean adicionou = false;

        if (carta.equalsIgnoreCase(CartasMesa.first())) {
            jogador.CartasColetadas.push(CartasMesa.removeFirst());
            adicionou = true;
            while(!CartasMesa.isEmpty()) {
                if(carta.equalsIgnoreCase(CartasMesa.last())) {
                    jogador.CartasColetadas.push(CartasMesa.removeFirst());
                } else {
                    AuxDeque.addFirst(CartasMesa.removeLast());
                }
            }

            while(!AuxDeque.isEmpty()) {
                CartasMesa.addFirst(AuxDeque.removeLast());
            }

        }
        return adicionou;
    }

    public boolean addCartaDireita(String carta) {
        ArrayDeque<String> AuxDeque = new ArrayDeque<>();
        boolean adicionou = false;

        if (carta.equalsIgnoreCase(CartasMesa.last())) {
            jogador.CartasColetadas.push(CartasMesa.removeLast());
            procurarCartaMão(carta);

            adicionou = true;
            while(!CartasMesa.isEmpty()) {
                if(carta.equalsIgnoreCase(CartasMesa.last())) {
                    jogador.CartasColetadas.push(CartasMesa.removeLast());
                    procurarCartaMão(carta);
                } else {
                    AuxDeque.addFirst(CartasMesa.removeLast());
                }
            }

            while(!AuxDeque.isEmpty()) {
                CartasMesa.addFirst(AuxDeque.removeLast());
            }

        }
        return adicionou;
    }

    public void addCartasMesa(String carta, String lado) {
        ArrayDeque<String> AuxCartaJogador = new ArrayDeque<>();

        if (lado.equalsIgnoreCase("Esquerda")) {
            while(!jogador.CartasColetadas.isEmpty()) {
                if(carta.equalsIgnoreCase(jogador.CartasColetadas.top())){
                    CartasMesa.addFirst(jogador.CartasColetadas.top());
                } else {
                     AuxCartaJogador.addFirst(jogador.CartasColetadas.pop());
                }
            }

            while(!AuxCartaJogador.isEmpty()) {
                jogador.CartasColetadas.push(AuxCartaJogador.removeLast());
            }
        } else if (lado.equalsIgnoreCase("Direita")){
            while(!jogador.CartasColetadas.isEmpty()) {
                if(carta.equalsIgnoreCase(jogador.CartasColetadas.top())){
                    CartasMesa.addLast(jogador.CartasColetadas.top());
                } else {
                    AuxCartaJogador.addFirst(jogador.CartasColetadas.pop());
                }
            }

            while(!AuxCartaJogador.isEmpty()) {
                jogador.CartasColetadas.push(AuxCartaJogador.removeLast());
            }
        }
    }

    public void compraCartas() {
        jogador.CartasNaMão.add(MonteCartas.pop());
    }

    public void procurarCartaMão(String carta) {
        for (String cartas : jogador.CartasNaMão) {
            if (cartas.equalsIgnoreCase(carta)) {
                jogador.CartasNaMão.remove(carta);
                jogador.CartasColetadas.push(carta);
            }
        }
    }


}

