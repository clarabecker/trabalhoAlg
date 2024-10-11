package src.trabalho;
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



//para testes inciais
    public void inicioPartida(){
        Turno turno1 = new Turno(jogador1, MonteCartas, CartasMesa);
        Turno turno2 = new Turno(jogador2, MonteCartas, CartasMesa);

        turno1.distribuirCartas(jogador1, jogador2);
        int index = 0;



        //Loop comentado, mas os turnos devem ocorrer até terminar as cartas
        //while(!MonteCartas.isEmpty()) {



            if(turno1.verificarCartas()!=null){
                String cartaColetada = turno1.verificarCartas();
                System.out.println(cartaColetada);
            }


            //rever
            //ao final do turno o jogador sempre compra carta independente se conseguiu coletar ou não
            turno1.compraCartas();



            String cartaJogador2 = "147";

            //Se ele não consegue coletar carta ele adiciona uma carta na mesa
            if(!turno2.coletarCartaDireita(cartaJogador2)) {
                cartaJogador2 = "Fusca";
                turno2.addCartasMesa(cartaJogador2, "direita");
                turno2.compraCartas();
            }
            turno2.compraCartas();

            //IMPLEMENTEI O TURNO -->  ARRAYLIST
            turno2.terminoTurnoGeral();
        //}

        //Metodo para teste --> fim de jogo
        fimDoJogo();

    }
/*
    public void realizarTurno(Jogador jogador1, Jogador jogador2, ArrayDeque<String> CartasMesa) {
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
        System.out.println("Duração do jogo: " + numeroTurnos + " turnos.");
    }
    */


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