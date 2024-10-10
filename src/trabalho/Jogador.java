package src.trabalho;

import stack.ArrayStack;
import stack.Stack;
import java.util.ArrayList;
import java.util.List;

public class Jogador {
    public List<String> CartasNaMão = new ArrayList();
    public Stack<String> CartasColetadas = new ArrayStack<>();

    public Jogador() {
    }
}
