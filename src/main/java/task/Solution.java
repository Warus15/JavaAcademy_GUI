package task;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.stream.Collectors;

public class Solution {
    private final String key; //Key that user enters in starting screen
    private final String regex = "[^A-Za-z ]"; //Regex to get rid of special characters (without spaces, so that algorithm can divide sentence into words)
    private final String spRegex = "[^A-Za-z]"; //Regex to get rid of spaces, so that algorithm can easily get sentence length
    private final HashSet<Character> keyCharacters; //Set of unique characters from given key

    private double keyCharsInSentenceOccurrences = 0; //Total occurrences of key characters in whole sentence
    private String sentence;

    private ArrayList<String> wordsInSentence;

    //ArrayLists to store WordGroups. The point of using two ArrayLists is that groupNames stores only names of the group
    //so that algorithm can check i certain group exists, and if it doesn't, then create it.
    //
    //Another thing, is that index of group in wordGroups is the same as its name in groupNames, so it is easy to get
    //position of certain group
    private ArrayList<String> groupNames;
    private ArrayList<WordGroup> wordGroups;

    private ArrayList<Record> results;

    private String totalFrequencyText;

    public Solution(String key) {
        this.groupNames = new ArrayList<>();
        this.wordGroups = new ArrayList<>();
        this.results = new ArrayList<>();

        //Setting key to lower case, so that algorithm is case insensitive
        this.key = key.toLowerCase(Locale.ROOT);

        //Obtaining unique characters from key
        this.keyCharacters = this.key.chars().mapToObj(e -> (char) e).collect(Collectors.toCollection(HashSet::new));
    }

    //Main method of this class, calls each of methods to get final result
    public void solve(String sentence){
        prepareSentence(sentence);
        prepareWordsList();
        countKeyCharsOccurrences();
        prepareGroups();
        listGroups();
        calculateTotalFrequency();

        System.out.println();
    }

    private void prepareSentence(String sentence){
        sentence = sentence.toLowerCase(Locale.ROOT); //Lower case, to make app case insensitive
        sentence = sentence.replaceAll(regex, ""); //Getting rid of special characters


        this.sentence = sentence;
    }

    private void prepareWordsList(){
        wordsInSentence = Arrays.stream(sentence.split(" ")).collect(Collectors.toCollection(ArrayList::new));

        System.out.println(wordsInSentence.toString());
    }

    //Counts how many time characters from key appeared in sentence
    private void countKeyCharsOccurrences(){
        int occurrences = 0;
        for(int i=0; i<sentence.length(); ++i)
            if(keyCharacters.contains(sentence.charAt(i)))
                ++occurrences;

        keyCharsInSentenceOccurrences = occurrences;
    }

    private void prepareGroups(){
        for(String word : wordsInSentence){
            int wordLength = word.length();
            StringBuilder groupName = new StringBuilder(); //Unique name of the group
            HashSet<Character> keyCharsInWord = new HashSet<>(); //Set of characters from key in a word

            //If word contains character from key, it's pushed to the set
            for(char c : keyCharacters){
                if(word.indexOf(c) >= 0)
                    keyCharsInWord.add(c);
            }

            //First part of group name - letters from key that appear in word
            for(char c : keyCharsInWord){
                groupName.append(c);
            }

            //Appending length of the word to group name
            groupName.append(" ").append(wordLength);

            //If group with given name doesn't exist, program creates it, and adds its name to groupName list
            //to avoid creating it next time
            //
            //Also, if word doesn't contain any of characters from key, there's no need to create it
            if(!groupNames.contains(groupName.toString()) && !keyCharsInWord.isEmpty()){
                groupNames.add(groupName.toString());
                wordGroups.add(new WordGroup(keyCharsInWord, wordLength, word, keyCharsInSentenceOccurrences));
            } else if (!keyCharsInWord.isEmpty()){
                //If given group already exists, program finds its index in groupNames
                //This index is also and index of group in the wordGroups list.
                //Then program appends word to the group
                int index = groupNames.indexOf(groupName.toString());
                wordGroups.get(index).addWord(word);
            }
        }
    }

    //This method gets result record from each group
    //Then array list is sorted from the lowest to the highest freuquency
    private void listGroups(){
        for(WordGroup g : wordGroups)
            results.add(g.getResult());

        results.sort(Record::compareTo);

        //Prints result in the console
        for(Record r : results)
            r.printResult();
    }

    private void calculateTotalFrequency(){
        sentence = sentence.replaceAll(spRegex, ""); //Getting rid of spaces in sentence, so it contains only letters

        double length = sentence.length();
        double quotient = keyCharsInSentenceOccurrences/length;
        double totalFrequency = Math.round(quotient * 100.0) / 100.0;

        totalFrequencyText = "Total Frequency: " + totalFrequency + " (" + keyCharsInSentenceOccurrences + "/" + length + ")";

        System.out.println(totalFrequencyText);
    }

    public ArrayList<Record> getResults() {
        return results;
    }

    public String getTotalFrequencyText() {
        return totalFrequencyText;
    }

    //This method clear variables, so that new call of solution.solve() in main screen has valid output
    public void clear(){
        wordsInSentence = new ArrayList<>();
        groupNames = new ArrayList<>();
        wordGroups = new ArrayList<>();
        results = new ArrayList<>();

        keyCharsInSentenceOccurrences = 0;
    }
}
