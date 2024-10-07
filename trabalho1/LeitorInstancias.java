package trabalho1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitorInstancias {

    public static List<String[]> lerInstancias(String nomeArquivo) {
        List<String[]> instancias = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] valores = linha.split(" ");
                instancias.add(valores);
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        return instancias;
    }
}
