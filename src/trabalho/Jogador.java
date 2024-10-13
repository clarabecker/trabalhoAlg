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

    public void compraCartas(Stack<String> MonteCartas) {
        CartasNaMao.add(MonteCartas.pop());
    }


    // Remove a última carta da mão e retorna
    public String removerUltimaCarta() {
        int ultimoIndice = CartasNaMao.size()-1;
        return CartasNaMao.remove(ultimoIndice);
    }

    public String removerPrimeiraCarta() {
        return CartasNaMao.remove(0);
    }


    public int getTotalCartasColetadas() {
        return CartasColetadas.size();
    }


    public boolean podeColetar(String cartaMesa){
        for(String carta : CartasNaMao){
            if(cartaMesa.equalsIgnoreCase(carta)){
                return true;
            }
        }
        return false;
    }

    public void mostrarCartasNaMao() {
        System.out.println("Cartas na mão do Jogador " + id + ": " + CartasNaMao);
    }

    public void mostrarCartasColetadas() {
        System.out.println("Cartas coletadas pelo Jogador " + id + ": " + CartasColetadas);
    }


}
