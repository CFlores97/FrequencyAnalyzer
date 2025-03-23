public class WordCountResult {
    private final String word;
    private final int frequency;

    // Constructor
    public WordCountResult(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    // Getters

    public String getWord() {
        return word;
    }

    public int getFrequency(){
        return frequency;
    }

    @Override
    public String toString(){
        return word + ": " + frequency;
    }
}
