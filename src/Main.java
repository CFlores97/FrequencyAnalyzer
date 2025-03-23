import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java FrequencyAnalyzer <rutaWordFreq> <rutaPairFreq>");
            return;
        }

        String singleWordsFile = args[0];
        String pairsFile = args[1];

        // Soporte mínimo en 5000
        int minSupport = 5000;
        FrequencyAnalyzer analyzer = new FrequencyAnalyzer(minSupport);

        try {
            // Palabras frecuentes
            System.out.println("=== Palabras Frecuentes (Soporte >= 5000) ===");
            ArrayList<WordCountResult> singleWords = analyzer.getSingleFrequentWords(singleWordsFile);

            // Mostrar top 10
            for (int i = 0; i < 10 && i < singleWords.size(); i++) {
                System.out.println(singleWords.get(i));
            }

            System.out.println("\n=== Pares Frecuentes (Soporte >= 5000) ===");
            ArrayList<PairCountResult> pairs = analyzer.getFrequentPairs(pairsFile);

            // Mostrar top 10
            for (int i = 0; i < 10 && i < pairs.size(); i++) {
                System.out.println(pairs.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}