package src.trabalho;

import stack.ArrayStack;
import stack.Stack;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String nomeArquivo = "src/trabalho/partidas.txt";
        List<String[]> instancias = LeitorInstancias.lerInstancias(nomeArquivo);

        for (int i = 0; i < 100; i++) {
            Stack<String> CartasCompra = new ArrayStack<>();

            for (String carta : instancias.get(i)) {
                CartasCompra.push(carta);
            }

            JogoCartas jogo = new JogoCartas(CartasCompra);
            jogo.inicioPartida();
        }

    }
}
