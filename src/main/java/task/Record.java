package task;

import java.util.ArrayList;
import java.util.HashSet;

public class Record implements Comparable<Record>{
    private final HashSet<Character> chars;
    private final int wordLength;
    private final ArrayList<String> words;
    private final double frequency;
    private final double occurrences;
    private final double totalOccurrences;

    public Record(HashSet<Character> chars, int wordLength, ArrayList<String> words, double frequency, double occurrences, double totalOccurrences) {
        this.chars = chars;
        this.wordLength = wordLength;
        this.words = words;
        this.frequency = frequency;
        this.occurrences = occurrences;
        this.totalOccurrences = totalOccurrences;
    }

    public void printResult(){
        System.out.println(
                chars.toString() + " " + wordLength + " " + words.toString() + " = " + frequency + " (" + occurrences + "/" + totalOccurrences + ")"
        );
    }

    @Override
    public int compareTo(Record o) {
        if(o.frequency <= this.frequency) return 1;
        if(o.frequency > this.frequency) return -1;
        return 0;
    }

    public HashSet<Character> getChars() {
        return chars;
    }

    public int getWordLength() {
        return wordLength;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public double getFrequency() {
        return frequency;
    }

    public double getOccurrences() {
        return occurrences;
    }

    public double getTotalOccurrences() {
        return totalOccurrences;
    }
}
