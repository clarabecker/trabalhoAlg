package src;

import deque.ArrayDeque;
import stack.Stack;




public class Turno {
    private  Jogador jogador;
    private  ArrayDeque<String> CartasMesa;
    private Stack<String> MonteCartas;
    private boolean houveColetasEsquerda = false;
    private boolean houveColetasDireita = false;


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
            int indice = jogador.CartasNaMao.indexOf(carta);
            jogador.CartasColetadas.push(jogador.CartasNaMao.remove(indice));
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
        houveColetasEsquerda = true;
        return adicionou;
    }

    public boolean coletarCartaDireita(String carta) {
        ArrayDeque<String> AuxDeque = new ArrayDeque<>();
        boolean adicionou = false;

        if (carta.equalsIgnoreCase(CartasMesa.last())) {
            jogador.CartasColetadas.push(CartasMesa.removeLast());
            int indice = jogador.CartasNaMao.indexOf(carta);
            jogador.CartasColetadas.push(jogador.CartasNaMao.remove(indice));

            adicionou = true;
            while(!CartasMesa.isEmpty()) {
                if(carta.equalsIgnoreCase(CartasMesa.last())) {
                    jogador.CartasColetadas.push(CartasMesa.removeLast());
                } else {
                    AuxDeque.addFirst(CartasMesa.removeLast());
                }
            }

            while(!AuxDeque.isEmpty()) {
                CartasMesa.addFirst(AuxDeque.removeLast());
            }
        }
        houveColetasDireita = true;
        return adicionou;
    }

    public int procurarDireita(String carta) {
        ArrayDeque<String> auxDeque = new ArrayDeque<>();
        int cont = 1;

            auxDeque.addFirst(CartasMesa.removeLast());
            while (!CartasMesa.isEmpty()) {
                if (carta.equalsIgnoreCase(CartasMesa.last())) {
                    auxDeque.addFirst(CartasMesa.removeLast());
                    cont++;
                } else {
                    break;
                }
            }

            while (!auxDeque.isEmpty()) {
                CartasMesa.addLast(auxDeque.removeLast());
            }
        return cont;
    }

    public int procurarEsquerda(String carta) {
        ArrayDeque<String> auxDeque = new ArrayDeque<>();
        int cont = 1;

            auxDeque.addFirst(CartasMesa.removeFirst());
            while (!CartasMesa.isEmpty()) {
                if (carta.equalsIgnoreCase(CartasMesa.first())) {
                    auxDeque.addFirst(CartasMesa.removeFirst());
                    cont++;

                } else {
                    break;
                }
            }

            while (!auxDeque.isEmpty()) {
                CartasMesa.addFirst(auxDeque.removeLast());
            }

        return cont;
    }

    public void terminoTurnoGeral(){

        //Não houve coleta
        if(!houveColetasDireita && !houveColetasEsquerda){
            //Comportamento dos jogadores para colocar carta na mesa --> PONTO 9 DO DOCUMENTO
            if(jogador.getId()==1){
                CartasMesa.addFirst(jogador.removerPrimeiraCarta());
            }else if(jogador.getId()==2){
                CartasMesa.addLast(jogador.removerUltimaCarta());
            }
            addCartaFinalTurno(false, null);
        }

        //CHAMADA DO PONTO 11 DO DOCUMENTO
        if(houveColetasDireita){
            addCartaFinalTurno(true,"direita");
        }else if(houveColetasEsquerda){
            addCartaFinalTurno(true,"esquerda");
        }



    }


    //PONTO 11
    public void addCartaFinalTurno(boolean houveColeta, String lado){
        //PONTO 11 DO DOCUMENTO
        String novaCartaMesa = MonteCartas.pop();
        if(houveColeta){
            //Se houve coleta a nova carta é posicionada aonde ocorreu a coleta
            if(lado.equals("esquerda")){
                CartasMesa.addFirst(novaCartaMesa);
            } else if(lado.equals("direita")){{
                CartasMesa.addLast(novaCartaMesa);
            }
        }else{
            //Se não houve coleta, a nova carta ficará na extremidade oposta --> Comportamento do jogador
            if(jogador.getId()==1){
                CartasMesa.addLast(jogador.removerPrimeiraCarta());
            }else if(jogador.getId()==2){
                CartasMesa.addFirst(jogador.removerUltimaCarta());
            }
            }
        }
    }


    //Implementação do fluxo do jogo
    public void executarTurno() {
        boolean coletou = false;

        //Teste para ver se podemos coletar
        if(jogador.getId()==1){
            if (!CartasMesa.isEmpty() && jogador.podeColetar(CartasMesa.first())) {
                coletou = true;
                coletarCartaEsquerda(CartasMesa.first());
            } else if (!CartasMesa.isEmpty() && jogador.podeColetar(CartasMesa.last())) {
                coletou = true;
                coletarCartaDireita(CartasMesa.last());
            }
        //comportamento do jogador 2
        }else if(jogador.getId()==2){
            int contEsquerda = 0, contDireita = 0;
            if (!CartasMesa.isEmpty() && jogador.podeColetar(CartasMesa.first())) {
                coletou = true;

                int quantidadeEsquerda = procurarEsquerda(CartasMesa.first());
                if(quantidadeEsquerda>contEsquerda){
                    contEsquerda = quantidadeEsquerda;
                }
            } if (!CartasMesa.isEmpty() && jogador.podeColetar(CartasMesa.last())) {
                coletou = true;

                int quantidadeDireita = procurarDireita(CartasMesa.last());
                if(quantidadeDireita>contDireita){
                    contDireita = quantidadeDireita;
                }
            }

            if((contDireita>contEsquerda)) {
                coletarCartaDireita(CartasMesa.last());
            } else if(contEsquerda>contDireita) {
                coletarCartaEsquerda(CartasMesa.first());
            } else if((contEsquerda+contDireita)!=0){
                coletarCartaDireita(CartasMesa.last());
            }
        }


        terminoTurnoGeral();

        //FINAL DO TURNO --> PONTO 10
        if (!MonteCartas.isEmpty()) {
            jogador.compraCartas(MonteCartas);
        }


    }

}

