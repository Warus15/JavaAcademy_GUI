package task;

import java.util.ArrayList;
import java.util.HashSet;

public class WordGroup {
    private final HashSet<Character> characters;
    private final int wordLength;
    private final ArrayList<String> wordsInGroup;
    private final double totalOccurrencesInSentence;
    private double occurrences = 0;
    private double frequency;

    private Record record;

    public WordGroup(HashSet<Character> characters, int wordLength, String word, double totalOccurrencesInSentence) {
        this.characters = characters;
        this.wordLength = wordLength;
        this.totalOccurrencesInSentence = totalOccurrencesInSentence;
        this.wordsInGroup = new ArrayList<>();

        addWord(word);
    }

    public void addWord(String word){
        wordsInGroup.add(word);
    }

    //counts how many times letters from key appear in whole group
    private void countOccurrences(){
        for(String word : wordsInGroup){
            for(int i=0; i<word.length(); ++i)
                if(characters.contains(word.charAt(i)))
                    ++occurrences;
        }
    }

    private void calculateFrequency(){
        countOccurrences();

        double quotient = occurrences/totalOccurrencesInSentence;
        frequency = Math.round(quotient * 100.0) / 100.0;
    }

    public Record getResult(){
        calculateFrequency();

        record = new Record(characters, wordLength, wordsInGroup, frequency, occurrences, totalOccurrencesInSentence);

        return record;
    }
}
