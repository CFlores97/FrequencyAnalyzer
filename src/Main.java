import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Uso: java Main <rutaWordFreq> <rutaPairFreq>");
            return;
        }

        String singleWordsFile = args[0];
        String pairsFile = args[1];

        // Soporte mínimo en 5000
        int minSupport = 5000;
        FrequencyAnalyzer analyzer = new FrequencyAnalyzer(minSupport);

        try {
            // 1) Palabras frecuentes (ordenadas descendentemente)
            System.out.println("=== Top 10 ítems de 1 palabra (Soporte >= " + minSupport + ") ===");
            ArrayList<WordCountResult> singleWords = analyzer.getSingleFrequentWords(singleWordsFile);

            for (int i = 0; i < 10 && i < singleWords.size(); i++) {
                // Numeramos cada línea (i+1)
                System.out.println((i + 1) + ") " + singleWords.get(i));
            }

            // 2) Pares frecuentes (ordenados descendentemente)
            System.out.println("\n=== Top 10 ítems de 2 palabras (Soporte >= " + minSupport + ") ===");
            ArrayList<PairCountResult> pairs = analyzer.getFrequentPairs(pairsFile);

            for (int i = 0; i < 10 && i < pairs.size(); i++) {
                System.out.println((i + 1) + ") " + pairs.get(i));
            }

            // 3) "Hallazgos / Análisis" DINÁMICO:
            System.out.println("\n--- Hallazgos / Análisis Automático ---");

            // a) Palabra con mayor frecuencia
            if (!singleWords.isEmpty()) {
                WordCountResult topSingle = singleWords.get(0); // La primera es la de mayor frecuencia
                System.out.println("• La palabra con mayor frecuencia es '"
                        + topSingle.getWord() + "' con "
                        + topSingle.getFrequency() + " apariciones.");
            } else {
                System.out.println("• No hay palabras con frecuencia >= " + minSupport);
            }

            // b) Par con mayor frecuencia
            if (!pairs.isEmpty()) {
                PairCountResult topPair = pairs.get(0); // El primero es el de mayor frecuencia
                System.out.println("• El par con mayor frecuencia es ('"
                        + topPair.getWord1() + "', '"
                        + topPair.getWord2() + "') con "
                        + topPair.getFrequency() + " apariciones.");
            } else {
                System.out.println("• No hay pares con frecuencia >= " + minSupport);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
