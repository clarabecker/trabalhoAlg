package src;
import deque.ArrayDeque;
import stack.Stack;

public class JogoCartas {
    public Stack<String> MonteCartas;
    ArrayDeque<String> CartasMesa = new ArrayDeque<>();
    int numeroTurnos;

    public JogoCartas(Stack<String> MonteCartas) {
        this.MonteCartas = MonteCartas;
        numeroTurnos = 0;

    }

    Jogador jogador1 = new Jogador(1);
    Jogador jogador2 = new Jogador(2);



    public void inicioPartida(){
        realizarTurnos(jogador1, jogador2, CartasMesa);
        fimDoJogo();
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


    public void realizarTurnos(Jogador jogador1, Jogador jogador2, ArrayDeque<String> CartasMesa) {
        distribuirCartas(jogador1, jogador2);
        while (!MonteCartas.isEmpty()) {
            // Jogador 1 realiza sua ação
            Turno turnoJogador1 = new Turno(jogador1, MonteCartas, CartasMesa);
            turnoJogador1.executarTurno();
            numeroTurnos++;

            if (MonteCartas.isEmpty()) break;

            // Jogador 2 realiza sua ação
            Turno turnoJogador2 = new Turno(jogador2, MonteCartas, CartasMesa);
            turnoJogador2.executarTurno();
            numeroTurnos++;

            if (MonteCartas.isEmpty()) break;
        }
        //System.out.println("Duração do jogo: " + numeroTurnos + " turnos.");
   }


    //PONTO 12 --> FIM DO JOGO
    public void fimDoJogo(){
            calcularPontuacao(jogador1);
            calcularPontuacao(jogador2);

            System.out.print(jogador1.pontuacao + " ");
            System.out.print(jogador2.pontuacao + " ");

            if(jogador1.pontuacao > jogador2.pontuacao) {
                System.out.print("Jogador 1");
            } else if(jogador1.pontuacao == jogador2.pontuacao) {
                System.out.print("Empate");
            } else {
                System.out.print("Jogador 2");
            }
        System.out.println("");
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