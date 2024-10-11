package src.trabalho;

import stack.ArrayStack;
import stack.Stack;
import java.util.ArrayList;
import java.util.List;

public class Jogador {
    public List<String> CartasNaMao;
    public Stack<String> CartasColetadas;
    public int pontuacao;
    public int id;

    public Jogador(int id) {
        this.id = id;
        this.CartasNaMao = new ArrayList<>();
        this.CartasColetadas = new ArrayStack<>();

    }

    public int getId() {
        return this.id;
    }

    public void adicionarCartaNaMao(String carta) {
        CartasNaMao.add(carta);
    }

    public String removerPrimeiraCarta() {;
        return CartasNaMao.remove(0);
    }

    // Remove a última carta da mão e retorna
    public String removerUltimaCarta() {
        int ultimoIndice = CartasNaMao.size()-1;
        return CartasNaMao.remove(ultimoIndice);
    }

    public void coletarCarta(String carta) {
        CartasColetadas.push(carta);
    }

    public int getTotalCartasColetadas() {
        return CartasColetadas.size();
    }

    public void mostrarCartasNaMao() {
        System.out.println("Cartas na mão do Jogador " + id + ": " + CartasNaMao);
    }

    public void mostrarCartasColetadas() {
        System.out.println("Cartas coletadas pelo Jogador " + id + ": " + CartasColetadas);
    }

}
