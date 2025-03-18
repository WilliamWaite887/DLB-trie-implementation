package cs1501_p2;

import java.util.ArrayList;

public class DLB implements Dict {
    private DLBNode root;
    private final char endofWord = '$';
    private DLBNode currSearch;
    private String currPrefix;

    public DLB() {
        root = new DLBNode('\0');
        currSearch = root;
        currPrefix = "";
    }
 
 /**
     * Add a new word to the dictionary
     *
     * @param key New word to be added to the dictionary
     */
    public void add(String key) {
        key = key + endofWord;
        DLBNode current = root;
        for (int i = 0; i < key.length(); i++){
            char c = key.charAt(i);
            DLBNode prev = null;
            DLBNode curr = current.getRight();
            while (curr != null && curr.getLet() < c) {
                prev = curr;
                curr = curr.getRight();
            }
            if (curr != null && curr.getLet() == c) {
                //char already exists do nothing
            } else {
                DLBNode newNode = new DLBNode(c);
                if (prev == null) {
                    newNode.setRight(current.getRight());
                    current.setRight(newNode);
                } else {
                    newNode.setRight(curr);
                    prev.setRight(newNode);
                }
                curr = newNode;
            }

            if (i < key.length()-1) {
                if (curr.getDown() == null) {
                    curr.setDown(new DLBNode('\0'));
                }
                current = curr.getDown();
            }
        }
    }
    
 /**
     * Check if the dictionary contains a word
     *
     * @param key Word to search the dictionary for
     *
     * @return true if key is in the dictionary, false otherwise
     */
    public boolean contains(String key) {
        DLBNode currLevel = root;
        for (int i = 0; i < key.length(); i++) {
            char letter = key.charAt(i);
            DLBNode currNode = currLevel;
            while (currNode != null && currNode.getLet() < letter) {
                currNode = currNode.getRight();
            }
            if (currNode == null || currNode.getLet() != letter) {
                return false;
            }
            currLevel = currNode.getDown();
        }
        if (currLevel != null) {
            DLBNode end = currLevel;
            while (end != null) {
                if (end.getLet() == endofWord) {
                    return true;
                }
                end = end.getRight();
            }
        }
        return false;
    }

    //helper that i can use for both containsPrefix and searchbychar

    private DLBNode isPrefix(String prefix){
        DLBNode currLevel = root;
        DLBNode currNode = null;
        for (int i = 0; i < prefix.length(); i++){
            char letter = prefix.charAt(i);
            currNode = currLevel.getRight();
            while (currNode != null && currNode.getLet() < letter){
                currNode = currNode.getRight();
            }
            if (currNode == null || currNode.getLet() != letter) {
                return null;
            }
            // If this is the last letter, return the node.
            if (i == prefix.length() - 1) {
                return currNode;
            }
            currLevel = currNode.getDown();
            if (currLevel == null){
                return null;
            }
        }
        return currNode;
    }

    /**
     * Check if a String is a valid prefix to a word in the dictionary
     *
     * @param pre Prefix to search the dictionary for
     *
     * @return true if prefix is valid, false otherwise
     */
    public boolean containsPrefix(String pre){
        DLBNode node = isPrefix(pre);
        return node != null;
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
    public int searchByChar(char next) {
        if (currSearch == null) {
            return -1;
        }
        
        // If currSearch is the root, search among its right siblings.
        // Otherwise, search among the children (the "down" list) of the current node.
        DLBNode child;
        if (currSearch == root) {
            child = currSearch.getRight();
        } else {
            child = currSearch.getDown();
        }
        
        while (child != null && child.getLet() < next) {
            child = child.getRight();
        }
        
        if (child == null || child.getLet() != next) {
            currSearch = null;
            return -1;
        }
        
        // Update currSearch to the node containing the matched letter.
        currSearch = child;
        currPrefix += next;
        
        // Check if the current node's children include the end-of-word marker.
        DLBNode pointer = (currSearch.getDown() == null) ? null : currSearch.getDown().getRight();
        boolean isWord = false;
        boolean hasPrefix = false;
        while (pointer != null) {
            if (pointer.getLet() == endofWord) {
                isWord = true;
            } else {
                hasPrefix = true;
            }
            pointer = pointer.getRight();
        }


        if (isWord && hasPrefix) return 2;
        else if (isWord) return 1;
        else if (hasPrefix) return 0;
        else {
            currSearch = null;
            return -1;
        }
    }
    
    

    /**
     * Reset the state of the current by-character search
     */
    public void resetByChar(){
        currSearch = root;
        currPrefix = "";
    }

    /**
     * Suggest up to 5 words from the dictionary based on the current
     * by-character search. Ordering should depend on the implementation.
     * 
     * @return ArrayList<String> List of up to 5 words that are prefixed by
     *         the current by-character search
     */
    public ArrayList<String> suggest() {
        ArrayList<String> suggestions = new ArrayList<>();
        if (currSearch == null || currSearch == root) {
            return suggestions;
        }
        
        // Gather completions starting from the children of the current node.
        ArrayList<String> suffixes = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        DLBNode child = currSearch.getDown();
        if (child != null && child.getLet() == '\0') {
            child = child.getRight();
        }
        if (child != null){
            gatherSuffixes(child, sb, suffixes);
        }
        
        // Prepend the current prefix (tracked in currPrefix) to each suffix.
        for (String suffix : suffixes) {
            suggestions.add(currPrefix + suffix);
        }
        
        // Return at most 5 suggestions.
        return new ArrayList<>(suggestions.subList(0, Math.min(5, suggestions.size())));
    }
    
    
    /**
     * A helper that traverses the subtree rooted at `node`, building suffixes,
     * and adding them to `suggestions`.
     */
    private void gatherSuffixes(DLBNode node, StringBuilder sb, ArrayList<String> suggestions) {
        if (node == null) {
            return;
        }
        
        DLBNode curr = node;
        while (curr != null) {
            // If this is the first character of the suffix and it's a dummy node, skip it.
            if (curr.getLet() == '\0') {
                curr = curr.getRight();
                continue;
            }
            
            if (curr.getLet() == endofWord) {
                suggestions.add(sb.toString());
            } else {
                sb.append(curr.getLet());
                gatherSuffixes(curr.getDown(), sb, suggestions);
                sb.deleteCharAt(sb.length() - 1);
            }
            curr = curr.getRight();
        }
    }

    /**
     * List all of the words currently stored in the dictionary
     * 
     * @return ArrayList<String> List of all valid words in the dictionary
     */
    public ArrayList<String> traverse(){
        ArrayList<String> words = new ArrayList<>();
        traverseHelp(root.getRight(), new StringBuilder(), words);
        return words;
    }

    //helper method for traverse

    public void traverseHelp(DLBNode node, StringBuilder path, ArrayList<String> words) {
        if (node == null) return;
    
        DLBNode curr = node;
        while (curr != null) {
            if (curr.getLet() == '\0') {
                curr = curr.getRight();
                continue;
            }
            if (curr.getLet() == endofWord) {
                words.add(path.toString()); 
            } else {
                path.append(curr.getLet());
                traverseHelp(curr.getDown(), path, words); // Traverse down first
                path.deleteCharAt(path.length() - 1);
            }
            curr = curr.getRight();
        }
    }

    /**
     * Count the number of words in the dictionary
     *
     * @return int, the number of (distinct) words in the dictionary
     */
    public int count(){
        return countHelp(root.getRight());
    }

    //helper for count

    public int countHelp(DLBNode node) {
        if (node == null) return 0;
    
        int total = 0;
        DLBNode curr = node;
        while (curr != null) {
            if (curr.getLet() == endofWord) {
                total++;
            }
            if (curr.getDown() != null) {
                total += countHelp(curr.getDown().getRight());
            }
            curr = curr.getRight();
        }
        
        
        return total;
    }
    
}
