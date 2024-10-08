package src.trabalho;

import deque.ArrayDeque;
import stack.ArrayStack;
import stack.Stack;

import java.util.List;

public class JogoCartas {

    public static void main(String args[]) {
        String nomeArquivo = "src/trabalho/partidas.txt";
        List<String[]> instancias = LeitorInstancias.lerInstancias(nomeArquivo);

        for (int i = 0; i < 1; i++) {
            Stack<String> CartasCompra = new ArrayStack<>();

            for (String carta : instancias.get(i)) {
                CartasCompra.push(carta);
            }

            IniciodePartida(CartasCompra);
        }

    }

    public static void IniciodePartida(Stack<String> CartasCompra) {
        Jogador jogador1 = new Jogador();
        Jogador jogador2 = new Jogador();
        ArrayDeque<String> CartasMesa = new ArrayDeque<>();

        for (int i = 0; i < 12; i++) {
            if (i < 4) {
                jogador1.CartasNaMão.add(CartasCompra.pop());
            } else if (i < 8) {
                jogador2.CartasNaMão.add(CartasCompra.pop());
            } else {
                if (!CartasCompra.isEmpty()) {
                    CartasMesa.addFirst(CartasCompra.pop());
                }
            }
        }
    }

}