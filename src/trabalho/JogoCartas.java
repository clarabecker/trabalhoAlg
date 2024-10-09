package src.trabalho;
import deque.ArrayDeque;
import stack.Stack;

public class JogoCartas {
    public Stack<String> MonteCartas;

    public JogoCartas(Stack<String> MonteCartas) {
        this.MonteCartas = MonteCartas;
    }

    Jogador jogador1 = new Jogador();
    Jogador jogador2 = new Jogador();
    ArrayDeque<String> CartasMesa = new ArrayDeque<>();

    public void inicioPartida(){
        for (int i = 0; i < 12; i++) {
            if (i < 4) {
                jogador1.CartasNaMão.add(MonteCartas.pop());
            } else if (i >= 4 && i < 8) {
                jogador2.CartasNaMão.add(MonteCartas.pop());
            } else {
                if (!MonteCartas.isEmpty()) {
                    CartasMesa.addFirst(MonteCartas.pop());
                }
            }
        }

        int index = 0;

        //Loop comentado, mas os turnos devem ocorrer até terminar as cartas
        //while(!MonteCartas.isEmpty()) {
            Turno turno1 = new Turno(jogador1, MonteCartas, CartasMesa);
            //carta escolhida tem que ir em uma variável conforme o comportamento do jogador

            //Conseguiu coletar carta
            String cartaJogador1 = "DelRey";
            turno1.addCartaEsquerda(cartaJogador1);
            //ao final do turno o jogador sempre compra carta independente se conseguiu coletar ou não
            turno1.compraCartas();

            Turno turno2 = new Turno(jogador2, MonteCartas, CartasMesa);

            String cartaJogador2 = "147";

            //Se ele não consegue coletar carta ele adiciona uma carta na mesa
            if(!turno2.addCartaDireita(cartaJogador2)) {
                cartaJogador2 = "Fusca";
                turno2.addCartasMesa(cartaJogador2, "direita");
                turno2.compraCartas();
            }
            turno2.compraCartas();
        //}

        //fim de jogo
        calcularPontuacao(jogador1);
        calcularPontuacao(jogador2);

        System.out.print(jogador1.pontuacao + " ");
        System.out.print(jogador2.pontuacao + " ");

        if(jogador1.pontuacao > jogador2.pontuacao) {
            System.out.print("Jogador 1");
        } else if(jogador1.pontuacao == jogador2.pontuacao) {
            System.out.print("Empate");
        } else {
            System.out.print("Jogador2");
        }
    }

    public void calcularPontuacao(Jogador jogador) {
        int index = 0;

        while(!jogador.CartasColetadas.isEmpty()) {
            jogador.CartasColetadas.pop();
            index++;
        }

        jogador.pontuacao = index;
    }
}