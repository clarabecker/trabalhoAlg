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

    public void addCartaEsquerda(String carta) {
        ArrayDeque<String> AuxDeque = new ArrayDeque<>();
        boolean adicionou = false;

        if (carta.equalsIgnoreCase(CartasMesa.first())) {
            jogador.CartasColetadas.push(CartasMesa.removeFirst());

            while(!CartasMesa.isEmpty()) {
                if(carta.equalsIgnoreCase(CartasMesa.last())) {
                    jogador.CartasColetadas.push(CartasMesa.removeFirst());
                } else {
                    AuxDeque.addFirst(CartasMesa.removeLast());
                }

                while(!AuxDeque.isEmpty()) {
                    CartasMesa.addFirst(AuxDeque.removeLast());
                }
            }

        }

        if(!adicionou) {
            System.out.println("Nenhuma carta foi adicionada");
        }

    }

    public void addCartaDireita(String carta) {
        ArrayDeque<String> AuxDeque = new ArrayDeque<>();
        boolean adicionou = false;

        if (carta.equalsIgnoreCase(CartasMesa.last())) {
            jogador.CartasColetadas.push(CartasMesa.removeLast());

            while(!CartasMesa.isEmpty()) {
                if(carta.equalsIgnoreCase(CartasMesa.last())) {
                    jogador.CartasColetadas.push(CartasMesa.removeLast());
                } else {
                    AuxDeque.addFirst(CartasMesa.removeLast());
                }

                while(!AuxDeque.isEmpty()) {
                    CartasMesa.addFirst(AuxDeque.removeLast());
                }
            }

        }
        if(!adicionou) {
            System.out.println("Nenhuma carta foi adicionada");
        }
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



}

