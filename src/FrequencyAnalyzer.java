import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FrequencyAnalyzer {

    private int minFrequency;

    public FrequencyAnalyzer(int minFrequency) {
        this.minFrequency = minFrequency;
    }

    // Getter
    public int getMinFrequency(){
        return minFrequency;
    }

    /*
    * Leer archivo de frecuencia de single words, luego sortearlos en orden descendiente
    *
    *
    * */

    public ArrayList<WordCountResult> getSingleFrequentWords(String singleWordsPath) throws IOException {
        Map<String, Integer> frequencyDict = new HashMap<>();
        ArrayList<WordCountResult> resultados = new ArrayList<>();

        FileReader fr = new FileReader(singleWordsPath);
        BufferedReader br = new BufferedReader(fr);

        try(br){
            String line;
            while((line = br.readLine()) != null){
                String[] words = line.split("\\s+");

                if (words.length == 2) {
                    String word = words[0];
                    int frequency = Integer.parseInt(words[1]);

                    // filtracion de minSupport
                    if (frequency >= minFrequency) {
                        frequencyDict.put(word, frequency);
                    }
                }
            }
        }

        // Mandarlos al arraylist de resultados
        for(Map.Entry<String, Integer> entry : frequencyDict.entrySet()){
            resultados.add(new WordCountResult(entry.getKey(), entry.getValue()));
        }

        // Sortear el arraylist en orden descendente
        resultados.sort(new Comparator<WordCountResult>() {
            @Override
            public int compare(WordCountResult o1, WordCountResult o2) {
                return o2.getFrequency() - o1.getFrequency();
            }
        });


        return resultados;

    }

    public ArrayList<PairCountResult> getFrequentPairs(String pairsWith) throws IOException {
        Map<String, Integer> frequencyDict = new HashMap<>();

        FileReader fr = new FileReader(pairsWith);
        BufferedReader br = new BufferedReader(fr);

        try(br){
            String line;
            while((line = br.readLine()) != null){
                String[] parts = line.split("\\s+");
                if (parts.length == 2) {
                    String word = parts[0];
                    int frequency = Integer.parseInt(parts[1]);

                    if (frequency >= minFrequency) {
                        frequencyDict.put(word, frequency);
                    }
                }
            }
        }

        ArrayList<PairCountResult> results = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : frequencyDict.entrySet()) {
            // entry.getKey() es "pizza,delivery"
            String[] words = entry.getKey().split(",");
            if (words.length == 2) {
                String w1 = words[0];
                String w2 = words[1];
                results.add(new PairCountResult(w1, w2, entry.getValue()));
            }
        }

        results.sort(new Comparator<PairCountResult>() {
            @Override
            public int compare(PairCountResult o1, PairCountResult o2) {
                return Integer.compare(o2.getFrequency(), o1.getFrequency());
            }
        });

        return results;

    }
}

