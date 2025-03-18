package cs1501_p2;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class AutoCompleter implements AutoComplete_Inter {
    public DLB dlb;
    public UserHistory userHistory;
    public StringBuilder currPrefix;

    // Constructor with only dictionary file
    public AutoCompleter(String dictFile) {
        this.dlb = new DLB();
        this.userHistory = new UserHistory();
        this.currPrefix = new StringBuilder();

        try {
            File file = new File(dictFile);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine().trim();
                if (!word.isEmpty()) {
                    dlb.add(word);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Dictionary file not found: " + dictFile);
        }
    }

    // Constructor with dictionary file and user history file
    public AutoCompleter(String dictFile, String userHistoryFile) {
        this(dictFile); // Call the single-argument constructor to initialize DLB and UserHistory
        userHistory.loadUserHistory(userHistoryFile); // Load user history from the file
    }
/**
     * Produce up to 5 suggestions based on the current word the user has
     * entered These suggestions should be pulled first from the user history
     * dictionary then from the initial dictionary. Any words pulled from user
     * history should be ordered by frequency of use. Any words pulled from
     * the initial dictionary should be in ascending order by their character
     * value ("ASCIIbetical" order).
     *
     * @param next char the user just entered
     *
     * @return ArrayList<String> List of up to 5 words prefixed by cur
     */
    public ArrayList<String> nextChar(char next) {
        // Update the current state in both dictionaries.
        userHistory.searchByChar(next);
        dlb.searchByChar(next);
    
        // Retrieve suggestions (assumed to be complete words) from each.
        ArrayList<String> userSuggestions = userHistory.suggest();
        ArrayList<String> dlbSuggestions = dlb.suggest();
    
        // Combine suggestions: user history suggestions take priority.
        ArrayList<String> suggestions = new ArrayList<>();
        for (String word : userSuggestions) {
            if (!suggestions.contains(word)) {
                suggestions.add(word);
                if (suggestions.size() == 5) break;
            }
        }
        for (String word : dlbSuggestions) {
            if (!suggestions.contains(word)) {
                suggestions.add(word);
                if (suggestions.size() == 5) break;
            }
        }
    
        return new ArrayList<>(suggestions.subList(0, Math.min(5, suggestions.size())));
    }

    /**
     * Process the user having selected the current word
     *
     * @param cur String representing the text the user has entered so far
     */
    public void finishWord(String cur){
        userHistory.add(cur);
        userHistory.resetByChar();
        dlb.resetByChar();
        
    }

    /**
     * Save the state of the user history to a file
     *
     * @param fname String filename to write history state to
     */
    public void saveUserHistory(String fname){
        userHistory.saveUserHistory(fname);
        
    }
}
