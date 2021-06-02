package task;

import java.util.HashSet;

public class TableViewRecord {
    private HashSet<Character> lettersInGroup;
    private int wordLength;
    private double frequency;
    private String exactValue;

    public TableViewRecord(HashSet<Character> lettersInGroup, int wordLength, double frequency, String exactValue) {
        this.lettersInGroup = lettersInGroup;
        this.wordLength = wordLength;
        this.frequency = frequency;
        this.exactValue = exactValue;
    }

    //Getters and setters required by JFX to add record to TableView
    public HashSet<Character> getLettersInGroup() {
        return lettersInGroup;
    }

    public void setLettersInGroup(HashSet<Character> lettersInGroup) {
        this.lettersInGroup = lettersInGroup;
    }

    public int getWordLength() {
        return wordLength;
    }

    public void setWordLength(int wordLength) {
        this.wordLength = wordLength;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public String getExactValue() {
        return exactValue;
    }

    public void setExactValue(String exactValue) {
        this.exactValue = exactValue;
    }
}
