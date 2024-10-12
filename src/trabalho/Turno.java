package src.trabalho;

import deque.ArrayDeque;
import list.ArrayList;
import stack.ArrayStack;
import stack.Stack;
import java.util.Iterator;


public class Turno {
    private  Jogador jogador;
    private  ArrayDeque<String> CartasMesa;
    private Stack<String> MonteCartas;

    public Turno(Jogador jogador, Stack<String> MonteCartas, ArrayDeque<String> CartasMesa) {
        this.jogador = jogador;
        this.MonteCartas = MonteCartas;
        this.CartasMesa = CartasMesa;
    }

    public void mostrarCartasMesa(){
        System.out.println(CartasMesa.toString());
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


    //Distribuir cartas para Jogadores e Mesa --> PONTOS 3 E 4
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

    //PARA ALGUNS TESTES
    public int verificarCartas(){
        int cont=0;
        for (String cartas : jogador.CartasNaMao) {
            if (coletarCartaDireita(cartas)){
                cont++;

            }else if(coletarCartaEsquerda(cartas)){
                coletarCartaEsquerda(cartas);

            }
        }
        return cont;
    }

    public void terminoTurnoGeral(){
        boolean houveColetas = (coletarCartaDireita(CartasMesa.last())||coletarCartaEsquerda(CartasMesa.first()));

        //Não houve coleta
        if(!houveColetas){
            //Comportamento dos jogadores --> PONTO 9 DO DOCUMENTO
            if(jogador.getId()==1){
                CartasMesa.addFirst(jogador.removerPrimeiraCarta());
            }else if(jogador.getId()==2){

                CartasMesa.addLast(jogador.removerUltimaCarta());
            }
        }
        addCartaFinalTurno(houveColetas);
        jogador.compraCartas(MonteCartas);

    }

    public void addCartaFinalTurno(boolean houveColetas){
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


    //Implementar o fluxo do jogo
    public void executarTurno() {
        boolean coletou = false;

        //Teste para ver se podemos coletar
        if (!CartasMesa.isEmpty() && jogador.podeColetar(CartasMesa.first())) {
            coletou = true;
            coletarCartaEsquerda(CartasMesa.first());
        } else if (!CartasMesa.isEmpty() && jogador.podeColetar(CartasMesa.last())) {
            coletou = true;
            coletarCartaDireita(CartasMesa.last());
        }

        //Se não coletar
        if (!coletou) {
            terminoTurnoGeral();
        }

        //Se o monte estiver vazio
        if (!MonteCartas.isEmpty()) {
            jogador.compraCartas(MonteCartas);
        }

        addCartaFinalTurno(coletou);
    }

}

