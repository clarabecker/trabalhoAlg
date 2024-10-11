package src.trabalho;

import deque.ArrayDeque;
import list.ArrayList;
import stack.ArrayStack;
import stack.Stack;
import java.util.Iterator;


public class Turno {
    private Jogador jogador;
    private ArrayDeque<String> CartasMesa;
    private Stack<String> MonteCartas;

    public Turno(Jogador jogador, Stack<String> MonteCartas, ArrayDeque<String> CartasMesa) {
        this.jogador = jogador;
        this.MonteCartas = MonteCartas;
        this.CartasMesa = CartasMesa;
    }

    public boolean coletarCartaEsquerda(String carta) {
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

    public boolean coletarCartaDireita(String carta) {
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

    public void distribuirCartas(Jogador j1, Jogador j2){
        for (int i = 0; i < 12; i++) {
            if (i < 4) {
                j1.CartasNaMao.add(MonteCartas.pop());
            } else if (i < 8) {
                j2.CartasNaMao.add(MonteCartas.pop());
            } else {
                if (!MonteCartas.isEmpty()) {
                    CartasMesa.addFirst(MonteCartas.pop());
                }
            }
        }
    }

    public void compraCartas() {
        jogador.CartasNaMao.add(MonteCartas.pop());
    }

    public void procurarCartaMão(String carta) {
        Iterator<String> iterator = jogador.CartasNaMao.iterator();
        while (iterator.hasNext()) {
            String cartas = iterator.next();
            if (cartas.equalsIgnoreCase(carta)) {
                iterator.remove();
                jogador.CartasColetadas.push(carta);
            }
        }
    }

    public String verificarCartas(){
        for (String cartas : jogador.CartasNaMao) {
            if (coletarCartaDireita(cartas)){
                coletarCartaDireita(cartas);
                return cartas;
            }else if(coletarCartaEsquerda(cartas)){
                coletarCartaEsquerda(cartas);
                return cartas;
            }
        }
        return null;
    }

    public void terminoTurnoGeral(){
        boolean houveColetas = (coletarCartaDireita(CartasMesa.last())||coletarCartaEsquerda(CartasMesa.first()));

        //Não houve coleta
        if(!houveColetas){
            //Comportamento dos jogadores --> PONTO 9 DO DOCUMENTO
            String primeiraCarta = jogador.CartasNaMao.remove(0);
            if(jogador.getId()==1){
                CartasMesa.addFirst(primeiraCarta);
            }else if(jogador.getId()==2){
                CartasMesa.addLast(primeiraCarta);
            }
        }
        retirarCartaFinalTurno(houveColetas);
        compraCartas();

    }

    public void retirarCartaFinalTurno(boolean houveColetas){
        //PONTO 11 DO DOCUMENTO
        String novaCartaMesa = MonteCartas.pop();
        if(houveColetas){
            //Se houve coleta a nova carta é posicionada aonde ocorreu a coleta
            if(!CartasMesa.isEmpty() && CartasMesa.first().equalsIgnoreCase(novaCartaMesa)){
                CartasMesa.addFirst(novaCartaMesa);
            } else {
                CartasMesa.addLast(novaCartaMesa);
            }
        }else{
            //Se não houve coleta, a nova carta ficará na extremidade oposta
            if(jogador.getId()==1){
                CartasMesa.addLast(novaCartaMesa);
            }else if(jogador.getId()==2){
                CartasMesa.addFirst(novaCartaMesa);
            }
        }


    }


}

