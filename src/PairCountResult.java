public class PairCountResult {

    private final String word1;
    private final String word2;
    private final int frequency;

    public PairCountResult(String word1, String word2, int frequency) {
        this.word1 = word1;
        this.word2 = word2;
        this.frequency = frequency;
    }

    // Getters
    public String getWord1(){
        return word1;
    }

    public String getWord2(){
        return word2;
    }

    public int getFrequency(){
        return frequency;
    }
}
