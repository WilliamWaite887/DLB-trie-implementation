package cs1501_p2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class UserHistory implements Dict {
    private final DLB dlb;
    private final HashMap<String, Integer> wordFrequency;
    private final StringBuilder currentPrefix;
    private boolean failedSearch;

    public UserHistory() {
        dlb = new DLB();
        wordFrequency = new HashMap<>();
        currentPrefix = new StringBuilder();
        failedSearch = false;
        
    }

    // Load user history from a file
    public void loadUserHistory(String fname) {
        try {
            File file = new File(fname);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String word = parts[0].trim();
                    int count = Integer.parseInt(parts[1].trim());
                    for (int i = 0; i < count; i++) {
                        this.add(word);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("User history file not found: " + fname);
        }
    }

    // Save user history to a file
    public void saveUserHistory(String fname) {
        try (PrintWriter writer = new PrintWriter(fname)) {
            for (String word : wordFrequency.keySet()) {
                writer.printf("%s:%d\n", word, wordFrequency.get(word));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Failed to save user history: " + e.getMessage());
        }
    }
    /**
     * Add a new word to the dictionary
     *
     * @param key New word to be added to the dictionary
     */
    public void add(String key){
        dlb.add(key);
        wordFrequency.put(key, wordFrequency.getOrDefault(key, 0) + 1);

    }

    /**
     * Check if the dictionary contains a word
     *
     * @param key Word to search the dictionary for
     *
     * @return true if key is in the dictionary, false otherwise
     */
    public boolean contains(String key){
        return dlb.contains(key);
    }

    /**
     * Check if a String is a valid prefix to a word in the dictionary
     *
     * @param pre Prefix to search the dictionary for
     *
     * @return true if prefix is valid, false otherwise
     */
    public boolean containsPrefix(String pre){
        return dlb.containsPrefix(pre);
    }

    /**
     * Search for a word one character at a time
     *
     * @param next Next character to search for
     *
     * @return int value indicating result for current by-character search:
     *         -1: not a valid word or prefix
     *         0: valid prefix, but not a valid word
     *         1: valid word, but not a valid prefix to any other words
     *         2: both valid word and a valid prefix to other words
     */
    public int searchByChar(char next){
        if (failedSearch) return -1;
        currentPrefix.append(next);
        int result = dlb.searchByChar(next);
        if (result == -1) {
            failedSearch = true;
            currentPrefix.setLength(currentPrefix.length() - 1);
            dlb.resetByChar();
        }
        if (result == 1 && dlb.containsPrefix(currentPrefix.toString())) {
            return 2;
        }
        return result;
    }

    /**
     * Reset the state of the current by-character search
     */
    public void resetByChar(){
        currentPrefix.setLength(0);
        dlb.resetByChar();
        failedSearch = false;
    }

    /**
     * Suggest up to 5 words from the dictionary based on the current
     * by-character search. Ordering should depend on the implementation.
     * 
     * @return ArrayList<String> List of up to 5 words that are prefixed by
     *         the current by-character search
     */
    public ArrayList<String> suggest() {
        if (failedSearch) return new ArrayList<>();
        String prefix = currentPrefix.toString();
        ArrayList<String> allWords = dlb.traverse();  // get all words stored in the user history DLB
        ArrayList<String> matching = new ArrayList<>();
        
        // Filter words that start with the current prefix
        for (String word : allWords) {
            if (word.startsWith(prefix)) {
                matching.add(word);
            }
        }
        
        // Sort the matching words by frequency (descending) then lexicographically
        matching.sort((a, b) -> {
            int freqA = wordFrequency.getOrDefault(a, 0);
            int freqB = wordFrequency.getOrDefault(b, 0);
            if (freqB != freqA) {
                return Integer.compare(freqB, freqA);
            } else {
                return a.compareTo(b);
            }
        });
        System.out.println("Final Suggestions: " + matching);
        System.out.println("All words from DLB: " + allWords);
        return new ArrayList<>(matching.subList(0, Math.min(5, matching.size())));
    }
    
    

    /**
     * List all of the words currently stored in the dictionary
     * 
     * @return ArrayList<String> List of all valid words in the dictionary
     */
    public ArrayList<String> traverse(){
        return dlb.traverse();
    }

    /**
     * Count the number of words in the dictionary
     *
     * @return int, the number of (distinct) words in the dictionary
     */
    public int count(){
        return wordFrequency.size();       
    }
}