package cs1501_p2;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * test coverage not guaranteed
 * @author    noah
 * @author    Dr. Farnan
 */
class Test_02 {

    /**
     * from a 2016 nyt article
     */
    String[] wineWordsNYT = { "About", "Albany", "And", "Barnhart", "Battles", "Before",
    "Champlain", "Cornell", "Counties", "Country", "European", "Finger", "For", "Fork",
    "Gerry", "In", "Island", "Islands", "Lake", "Lakes", "Let's", "Like", "Long", "Mary",
    "Minnesota", "Mr", "New", "North", "Red", "Revolution", "Saratoga", "So", "These", "They",
    "Thousand", "University", "Victory", "View", "Vineyard", "Washington", "Wisconsin", "York",
    "a", "about", "above", "abundance", "acre", "acres", "adding", "ago", "all", "allowed",
    "along", "an", "and", "any", "are", "areas", "as", "at", "availability", "away", "batch",
    "because", "before", "black", "business", "by", "cabernet", "can", "century", "character",
    "characterize", "chardonnay", "cherry", "cold-hardy", "color", "considered", "couldn't",
    "crescent", "cultivars", "developed", "different", "dozen", "eight", "essential", "even",
    "example", "expanded", "family", "favorable", "feel", "first", "flavor", "flavors",
    "flourished", "foch", "fought", "friends", "from", "gallons", "gave", "going", "grape",
    "grapes", "growing", "half", "harvested", "has", "have", "he", "here", "his", "in",
    "inhospitable", "into", "is", "it", "it's", "join", "just", "keep", "kinds", "la",
    "lacrosse", "last", "like", "looks", "made", "marquette", "marechal", "melody", "more",
    "most", "mouth", "named", "nearly", "new", "northern", "of", "on", "once", "one",
    "operation", "or", "own", "parts", "people", "planted", "profiles", "property", "quarter",
    "raspberry", "real", "red", "red-fruit", "region", "released", "reviews", "riesling",
    "said", "sauvignon", "see", "seven", "several", "site", "slope", "some", "started",
    "strawberry", "such", "than", "that", "the", "their", "these", "they", "think", "three",
    "to", "ton", "top", "traditional", "two", "up", "varieties", "variety", "vines",
    "vineyards", "we", "well-established", "were", "west", "what", "when", "where", "whether",
    "white", "who", "wife", "wine", "wine-producing", "winemaking", "winery", "wines", "with",
    "would", "years", "you" };
    /**
     * from a 2016 nyt article
     */
    String[] nflWordsNYT = { "A", "Aguayo", "Ajayi", "Alabama", "Alfred", "All", "Amari", "Angeles", "Any",
    "Arian", "As", "At", "Backs", "Baylor", "Bengals", "Betting", "Bison", "Boyd", "Brandon", "Braxton", 
    "Bridgewater", "Brissett", "Brock", "Broncos", "Browns", "Buccaneers", "C", "California", "Carolina", 
    "Carroo", "Carson", "Chad", "Championship", "Christian", "Cincinnati", "Clausen", "Cleveland", "Cody", 
    "Coleman", "Cooper", "Corey", "Cousins's", "Cowboys", "Dakota", "Dallas", "Dame", "Darren", "Days", 
    "DeMarco", "DeSean", "Demaryius", "Denver", "Derrick", "Despite", "Division", "Doctson", "Doctson's", 
    "Dolphins", "Drake", "Eagles", "East", "Eifert", "Elliott", "Emmanuel", "England", "Entering", "Even", 
    "Ezekiel", "Fantasy", "Football", "For", "Foster", "From", "Fuller", "Garcon", "Giants", "Given", 
    "Goff", "Gordon", "Green", "Griffin", "Hackenberg", "He", "Heisman", "Henne", "Henry", "Henry's", 
    "Here", "His", "Houston", "However", "I", "III's", "If", "In", "Irish", "J", "Jackson", "Jacoby", 
    "Jared", "Jay", "Jerry", "Jets", "Jimmy", "Jones", "Jordan", "Joseph", "Josh", "Kenyan", "Kessler", 
    "Kirk", "Laquon", "Leinart", "Leonte", "Los", "Lynch", "Marcus", "Mariota", "Matt", "McFadden", 
    "Memphis", "Miami", "Michael", "Miller", "Minnesota", "Mississippi", "Morris", "Murray", "NCAA", 
    "NFC", "NFL", "New", "No", "North", "Not", "Notre", "Ohio", "Oklahoma", "One", "Orleans", 
    "Osweiler", "PPR", "Patriots", "Paxton", "Penn", "Philadelphia", "Pierre", "Pittsburgh", "Playing", 
    "Prosise", "Prosise's", "QB", "Quarterbacks", "Rams", "Randle", "Rawls", "Receivers", "Redskins", 
    "Reed", "Robert", "Roberto", "Rueben", "Running", "Rutgers", "Saints", "Sanders", "Savvy", 
    "Seahawks", "Seattle", "Shepard", "Since", "Southern", "State", "Sterling", "Subdivision", "TCU", 
    "Teddy", "Tennessee", "Texans", "The", "This", "Thomas", "Titans", "To", "Treadwell", "Trophy", "Two", 
    "Tyler", "Unfortunately", "Vikings", "Washington", "Weeden", "Week", "Wentz", "While", "Wide", "Will", 
    "With", "a", "ability", "able", "about", "absolutely", "against", "ago", "alone", "also", "an", 
    "and", "angle", "ankle", "another", "any", "anybody", "are", "arm", "around", "as", "asset", "at", 
    "away", "back", "backfield", "backup", "bag", "be", "because", "become", "been", "before", "behind", 
    "being", "benches", "best", "both", "breakout", "broken", "build", "but", "by", "bye-week", "called", 
    "can", "cannot", "career", "carries", "certainly", "chance", "choice", "class", "clear", "college", 
    "combine", "come", "completely", "concerns", "core", "could", "counting", "counts", "current", "dash", 
    "decent", "deep-league", "deeper", "defenses", "defensive", "desired", "discussion", "do", "does", 
    "draft", "drafted", "drafting", "drafts", "drops", "dual", "dynasty", "each", "early", "eight", 
    "else", "end", "enters", "even", "events", "every", "everything", "expect", "explosive", "extremely", 
    "face", "fantasy", "far", "fastest", "favorite", "featured", "felt", "few", "fill", "filled", 
    "final", "finish", "first", "first-round", "fit", "flashing", "flex", "flier", "football", "for", 
    "forever", "format", "former", "four", "four-star", "frequent", "from", "gamble", "game", "games", 
    "gives", "goal-line", "gone", "good", "graduated", "great", "group", "had", "hands", "has", "have", 
    "having", "he", "healthy", "helped", "high-upside", "highest-drafted", "him", "his", "huge", "ideal", 
    "if", "immediate", "immediately", "impact", "in", "inconsistent", "injury", "into", "is", "it", 
    "just", "key", "kicker", "kind", "lack", "landing", "lands", "last", "late-round", "later", "league", 
    "leagues", "leaves", "led", "left", "legs", "like", "likely", "line", "linemen", "lines", "linked", 
    "little", "look", "looked", "looks", "lost;", "lot", "major", "make", "making", "many", "massive", 
    "match", "member", "members", "minus", "mixed", "monitored", "more", "most", "moving", "much", "name", 
    "necessary", "need", "needs", "negative", "neither", "newest", "next", "no", "nor", "not", "observers", 
    "of", "off", "off-season", "offense", "offensive", "offers", "on", "one", "only", "open", 
    "opportunities", "opportunity", "opting", "option", "options", "or", "other", "our", "out", "outside",
    "over", "overdraft", "owners", "pass-heavy", "passes", "passing", "percent", "physically", "pick",
    "picked", "picks", "play", "player", "players", "plays", "point", "point-per-reception", "polarizing", 
    "positions", "potential", "powerhouse", "practically", "programs", "progresses", "prohibitive", 
    "prolific", "prospect", "provide", "quarterback", "quarterbacks", "quite", "ran", "raw", "read", 
    "receiver", "receivers", "recognize", "recommend", "recovering", "recruit", "red", "red-zone", 
    "redshirt", "regular-season", "reigning", "relevance", "relevant", "reliability", "remember", 
    "replacement", "resulted", "return", "revitalized", "right", "role", "rookie", "rookies", "round", 
    "route-runner", "run", "run-first", "running", "rushed", "rushing", "sacked", "sacks", "safety", "same", 
    "scouts", "season", "season;", "seasons", "second", "seem", "seems", "seven", "share", "shocking", 
    "should", "signal-callers", "similarities", "situations", "six", "skills", "slot", "snap", "sole", 
    "some", "something", "sophomore", "speed", "speedy", "spent", "spots", "stability", "standard", 
    "star", "star-level", "start", "started", "starter", "starters", "starting", "statistics", "status", 
    "stays", "steady", "step", "still", "struggled", "stud", "sure-handed", "switched", "take", "taken", 
    "talent", "talented", "tallied", "target", "targets", "team", "teammate", "terrible", "terribly", 
    "than", "that", "the", "their", "there", "these", "they", "third-down", "this", "though", "threat;", 
    "threats", "three-down", "throwing", "throws", "tied", "tight", "time", "to", "tools", "top", 
    "touchdown", "touchdowns", "trades", "training", "turn", "turned", "two", "under", "undrafted", "unless", 
    "unlikely", "up", "upon", "upside", "use", "value", "vertical", "very", "was", "we", "weapons", "week", 
    "weeks", "wheels", "when", "whether", "while", "who", "wide", "wideouts", "will", "win", "winner", 
    "wise", "with", "within", "work", "worth", "would", "wrong", "yard", "yards", "year", "years", 
    "year's", "young", "zone" };


    @Test
    @DisplayName("Testing Small DLB")
    void testDLBSmall() {
        String dict_fname = "build/resources/test/dictionary.txt";

        DLB dlb = new DLB();
        assertEquals(0, dlb.count(), "Should be empty");

        try (Scanner s = new Scanner(new File(dict_fname))) {
            while (s.hasNext()) {
                String word = s.nextLine();
                assertTrue(!dlb.contains(word));
                dlb.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(7, dlb.count(), "Incorrect number of keys");

        String[] checks = new String[] {"A", "a", "definite", "dict", "dictionary", "is", "this"};
        for (String c : checks) {
            assertTrue(dlb.contains(c), "DLB should contain " + c);
        }

        checks = new String[] { "not", "there", "Dict", "dictionar", "i", "a'" };
        for (String c : checks) {
            assertTrue(!dlb.contains(c), "DLB should not contain " + c);
        }

        checks = new String[] { "i", "dict", "A", "thi" };
        for (String c : checks) {
            assertTrue(dlb.containsPrefix(c), c + " should be a valid prefix");
        }

        assertEquals(-1, dlb.searchByChar('q'), "q should not be a prefix or key");
        assertEquals(-1, dlb.searchByChar('a'), "qa should not be a prefix or key");
        dlb.resetByChar();
        assertEquals(0, dlb.searchByChar('d'), "d should be a valid prefix");
        assertEquals(0, dlb.searchByChar('i'), "di should be a valid prefix");
        assertEquals(0, dlb.searchByChar('c'), "dic should be a valid prefix");
        assertEquals(2, dlb.searchByChar('t'), "dict should be a valid prefix and key");
        assertEquals(0, dlb.searchByChar('i'), "dicti should be a valid prefix");
        assertEquals(0, dlb.searchByChar('o'), "dictio should be a valid prefix");
        assertEquals(0, dlb.searchByChar('n'), "diction should be a valid prefix");
        
        dlb.traverse();
        assertEquals(0, dlb.searchByChar('a'), "dictiona should be a valid prefix, and traverse should not impact searchByChar results");
        dlb.suggest();
        assertEquals(0, dlb.searchByChar('r'), "dictionar should be a valid prefix, and suggest should not impact searchByChar results");

        assertEquals(1, dlb.searchByChar('y'), "dictionary should be a valid key");
        assertEquals(-1, dlb.searchByChar('s'), "dictionarys should be a valid key");
        dlb.resetByChar();

        assertEquals(0, dlb.searchByChar('i'), "i should be a valid prefix");
        assertTrue(dlb.contains("this"), "Should be able to still run contains");
        assertEquals(1, dlb.searchByChar('s'), "is should be a valid key, even if interrupted by contains");
        dlb.resetByChar();

        dlb.searchByChar('d');
        ArrayList<String> sugs = dlb.suggest();
        String[] expected = new String[] { "definite", "dict", "dictionary" };
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], sugs.get(i), "Expected suggestion " + expected[i] + " got " + sugs.get(i));
        }

        ArrayList<String> trav = dlb.traverse();
        expected = new String[] { "A", "a", "definite", "dict", "dictionary", "is", "this" };
        assertEquals(expected.length, trav.size(), "Traversal returned a different number of keys than expected");
        for (String word : expected) {
            assertTrue(trav.contains(word), "Traversal should contain " + word + " but doesn't");
        }
    }
    @Test
    @DisplayName("Testing Large DLB")
    void testDLBLarge() {
        DLB dlb = new DLB();
        assertEquals(0, dlb.count(), "Should be empty");

        String[] words = wineWordsNYT;

        for (String s : words) {
            assertTrue(!dlb.contains(s));
            dlb.add(s);
            assertTrue(dlb.contains(s));
        }

        assertEquals(209, dlb.count(), "Incorrect number of keys");

        String[] checks = new String[] { "Island", "Lakes", "Red", "They", "Wisconsin", "and",
        "different", "family", "grape", "have", "is", "made", "marquette", "of", "said", "three",
        "varieties", "white", "wine", "would", "years", "you" };

        for (String c : checks) {
            assertTrue(dlb.contains(c), "DLB should contain " + c);
        }

        checks = new String[] { "producing", "well", "tradition", "Nearly", "albany", "review",
        "it'", "hello", "A" };
        for (String c : checks) {
            assertTrue(!dlb.contains(c), "DLB should not contain " + c);
        }

        checks = new String[] { "Barn", "North", "Rev", "V", "a", "and", "any", "as", "batc",
        "becau", "by", "cresc", "develo", "in", "is", "la", "lacrosse", "more", "named", "of",
        "red-frui", "s", "to", "ton", "wher", "woul" };
        for (String c : checks) {
            assertTrue(dlb.containsPrefix(c), c + " should be a valid prefix");
        }

        assertEquals(-1, dlb.searchByChar('Q'), "Q should not be a prefix or key");
        assertEquals(-1, dlb.searchByChar('a'), "Qa should not be a prefix or key");
        dlb.resetByChar();

        assertEquals(2, dlb.searchByChar('a'), "a should be a valid prefix and key");
        ArrayList<String> sugs = dlb.suggest();
        String[] expected = new String[] { "a", "about", "above", "abundance", "acre" };
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], sugs.get(i), "Expected suggestion " + expected[i] + " got " + sugs.get(i));
        }
        assertEquals(5, sugs.size(), "Should suggest exactly 5 words");
        
        assertEquals(0, dlb.searchByChar('l'), "al should be a valid prefix");
        sugs = dlb.suggest();
        expected = new String[] { "all", "allowed", "along" };
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], sugs.get(i), "Expected suggestion " + expected[i] + " got " + sugs.get(i));
        }
        assertEquals(3, sugs.size(), "Should suggest exactly 3 words");
        
        assertEquals(2, dlb.searchByChar('l'), "all should be a valid prefix and key");
        sugs = dlb.suggest();
        expected = new String[] { "all", "allowed" };
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], sugs.get(i), "Expected suggestion " + expected[i] + " got " + sugs.get(i));
        }
        assertEquals(2, sugs.size(), "Should suggest exactly 2 words");
        
        assertEquals(0, dlb.searchByChar('o'), "allo should be a valid prefix");
        assertEquals(1, dlb.suggest().size(), "Should suggest exactly 1 word");
        assertTrue(dlb.contains("allowed"), "DLB should still contain allowed");
        assertEquals(0, dlb.searchByChar('w'), "allow should be a valid prefix. Contains may have impacted this");
        assertEquals(1, dlb.suggest().size(), "Should suggest exactly 1 word");
        ArrayList<String> trav = dlb.traverse();
        assertEquals(0, dlb.searchByChar('e'), "allowe should be a valid prefix. Traverse may have impacted this");
        assertEquals(1, dlb.suggest().size(), "Should suggest exactly 1 word");
        assertEquals(1, dlb.searchByChar('d'), "allowed should be a valid key");
        assertEquals(1, dlb.suggest().size(), "Should suggest exactly 1 word");
        assertEquals(-1, dlb.searchByChar('d'), "allowedd should not be a valid key or prefix");
        assertEquals(0, dlb.suggest().size(), "There should be no suggestions left");
        
        dlb.resetByChar();

        ArrayList<String> trav2 = dlb.traverse();
        assertEquals(trav.size(), trav2.size(), "Traversal returned non-equivalent arraylists at different points");
        for (String word : dlb.traverse()) {
            assertTrue(trav.contains(word), "Traversal returned non-equivalent arraylists at different points");
        }
        
        assertEquals(words.length, trav2.size(), "Traversal returned a different number of keys than expected");
        for (String word : words) {
            assertTrue(trav.contains(word),
                    "Traversal should contain " + word + " but doesn't");
        }
    }

    @Test
    @DisplayName("Testing UserHistory")
    void testHistory() {
        UserHistory uh = new UserHistory();
        assertEquals(0, uh.count(), "Should be empty");

        // from a 2016 nytimes article
        String[] words = nflWordsNYT;

        /*
         * this section adds in random order to UserHistory, but each word's frequency is
         * equivalent to the number of factors its original index (in the original, sorted
         * list) has from the set {1, 2, 3, 4, 5, 6}
         */
        ArrayList<Integer> indices = new ArrayList<Integer>();
        for (int i = 0; i < words.length; i++) indices.add(i); 
        while (indices.size() > 0) {
            int indexOfIndex = ((int) ((0.3 * indices.size()) + 57 * words[indices.size() % words.length].length())) % indices.size();
            int index = indices.get(indexOfIndex);  // craziest line of code i've ever written
            for (int i = 1; i <= 6; i++) {
                if (index%i == 0) uh.add(words[index]);
            }
            indices.remove(indexOfIndex);
        }
        assertEquals(619, uh.count(), "Should have 619 distinct words");
        
        assertEquals(0, uh.searchByChar('r'), "search by char should return 0");
        String[] expectedSugs = new String[] { "right", "red-zone", "read", "receivers", "reliability" };
        ArrayList<String> sugs = uh.suggest();
        for (int i = 0; i < expectedSugs.length; i++) {
            assertEquals(expectedSugs[i], sugs.get(i), "Expected suggestion " + expectedSugs[i] + " but got " + sugs.get(i));
        }

        assertEquals(0, uh.searchByChar('e'), "search by char should return 0");
        expectedSugs = new String[] { "red-zone", "read", "receivers", "reliability", "recommend" };
        sugs = uh.suggest();
        for (int i = 0; i < expectedSugs.length; i++) {
            assertEquals(expectedSugs[i], sugs.get(i), "Expected suggestion " + expectedSugs[i] + " but got " + sugs.get(i));
        }
        
        assertEquals(0, uh.searchByChar('c'), "search by char should return 0");
        expectedSugs = new String[] { "receivers", "recommend", "recovering", "recruit", "receiver" };
        sugs = uh.suggest();
        for (int i = 0; i < expectedSugs.length; i++) {
            assertEquals(expectedSugs[i], sugs.get(i), "Expected suggestion " + expectedSugs[i] + " but got " + sugs.get(i));
        }
        
        assertEquals(0, uh.searchByChar('o'), "search by char should return 0");
        expectedSugs = new String[] { "recommend", "recovering", "recognize" };
        sugs = uh.suggest();
        assertEquals(3, sugs.size(), "Should only suggest 3 words");
        for (int i = 0; i < expectedSugs.length; i++) {
            assertEquals(expectedSugs[i], sugs.get(i), "Expected suggestion " + expectedSugs[i] + " but got " + sugs.get(i));
        }

        uh.add("recovering");
        uh.add("recovering");
        uh.add("recovering");
        uh.add("recovering");
        uh.add("recovering");
        uh.add("recovering");

        uh.resetByChar();

        assertEquals(0, uh.searchByChar('r'), "search by char should return 0");
        expectedSugs = new String[] { "recovering", "right", "red-zone", "read", "receivers" };
        sugs = uh.suggest();
        for (int i = 0; i < expectedSugs.length; i++) {
            assertEquals(expectedSugs[i], sugs.get(i), "Expected suggestion " + expectedSugs[i] + " but got " + sugs.get(i));
        }

        assertEquals(0, uh.searchByChar('e'), "search by char should return 0");
        expectedSugs = new String[] { "recovering", "red-zone", "read", "receivers", "reliability" };
        sugs = uh.suggest();
        for (int i = 0; i < expectedSugs.length; i++) {
            assertEquals(expectedSugs[i], sugs.get(i), "Expected suggestion " + expectedSugs[i] + " but got " + sugs.get(i));
        }
        
        assertEquals(0, uh.searchByChar('c'), "search by char should return 0");
        expectedSugs = new String[] { "recovering", "receivers", "recommend", "recruit", "receiver" };
        sugs = uh.suggest();
        for (int i = 0; i < expectedSugs.length; i++) {
            assertEquals(expectedSugs[i], sugs.get(i), "Expected suggestion " + expectedSugs[i] + " but got " + sugs.get(i));
        }
        
        assertEquals(0, uh.searchByChar('o'), "search by char should return 0");
        expectedSugs = new String[] { "recovering", "recommend", "recognize" };
        sugs = uh.suggest();
        assertEquals(3, sugs.size(), "Should only suggest 3 words");
        for (int i = 0; i < expectedSugs.length; i++) {
            assertEquals(expectedSugs[i], sugs.get(i), "Expected suggestion " + expectedSugs[i] + " but got " + sugs.get(i));
        }
        
        assertEquals(0, uh.searchByChar('g'), "search by char should return 0");
        sugs = uh.suggest();
        assertEquals(1, sugs.size(), "Should only suggest 1 word");
        assertEquals("recognize", sugs.get(0), "Expected suggestion recognize but got " + sugs.get(0));   
        
        assertEquals(-1, uh.searchByChar('a'), "search by char should return -1");
        assertEquals(0, uh.suggest().size(), "Should not have any suggestions left");
    }

    @Test
    @DisplayName("Testing AutoCompleter")
    void testAutoCompleter() {
        String dict_fname = "build/resources/main/dictionary.txt";
        String uhist_state_fname = "build/resources/test/uhist_state2.p2";

        AutoCompleter ac = new AutoCompleter(dict_fname);

        ArrayList<String> sugs = ac.nextChar('y');
        String[] expected = new String[] { "y", "y'all", "ya", "yabber", "yabber's" };
        assertEquals(5, sugs.size(), "Should have 5 suggestions");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], sugs.get(i),
            "Expected suggestion " + expected[i] + " got " + sugs.get(i));
        }

        sugs = ac.nextChar('o');
        expected = new String[] { "yo", "yob", "yobbo", "yobbos", "yobs" };
        assertEquals(5, sugs.size(), "Should have 5 suggestions");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], sugs.get(i),
            "Expected suggestion " + expected[i] + " got " + sugs.get(i));
        }

        sugs = ac.nextChar('g');
        expected = new String[] { "yoga", "yoga's", "yoghurt", "yoghurt's", "yoghurts" };
        assertEquals(5, sugs.size(), "Should have 5 suggestions");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], sugs.get(i),
            "Expected suggestion " + expected[i] + " got " + sugs.get(i));
        }
        
        sugs = ac.nextChar('o');
        expected = new String[] { "yogourt", "yogourt's", "yogourts" };
        assertEquals(3, sugs.size(), "Should have 3 suggestions");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], sugs.get(i),
                    "(finish dictionary) Expected suggestion " + expected[i] + " got " + sugs.get(i));
        }

        ac.finishWord("yogourts");

        sugs = ac.nextChar('y');
        expected = new String[] { "yogourts", "y", "y'all", "ya", "yabber" };
        assertEquals(5, sugs.size(), "Should have 5 suggestions");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], sugs.get(i),
            "Expected suggestion " + expected[i] + " got " + sugs.get(i));
        }

        sugs = ac.nextChar('o');
        expected = new String[] { "yogourts", "yo", "yob", "yobbo", "yobbos"};
        assertEquals(5, sugs.size(), "Should have 5 suggestions");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], sugs.get(i),
            "Expected suggestion " + expected[i] + " got " + sugs.get(i));
        }

        sugs = ac.nextChar('g');
        expected = new String[] { "yogourts", "yoga", "yoga's", "yoghurt", "yoghurt's", };
        assertEquals(5, sugs.size(), "Should have 5 suggestions");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], sugs.get(i),
            "Expected suggestion " + expected[i] + " got " + sugs.get(i));
        }
        
        sugs = ac.nextChar('o');
        expected = new String[] { "yogourts", "yogourt", "yogourt's" };
        assertEquals(3, sugs.size(), "Should have 3 suggestions");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], sugs.get(i),
                    "(finish dictionary) Expected suggestion " + expected[i] + " got " + sugs.get(i));
        }

        sugs = ac.nextChar('n');
        assertEquals(0, sugs.size(), "Should have 0 suggestions");

        ac.finishWord("yippeee");

        sugs = ac.nextChar('y');
        expected = new String[] { "yippeee", "yogourts", "y", "y'all", "ya"};
        assertEquals(5, sugs.size(), "Should have 5 suggestions");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], sugs.get(i),
            "Expected suggestion " + expected[i] + " got " + sugs.get(i));
        }

        ac.finishWord("yyyyyy");
        ac.finishWord("yodel");
        ac.finishWord("yodel");
        ac.finishWord("yankee");
        ac.finishWord("yodel");
        ac.finishWord("yarmulke");
        ac.finishWord("yodel");
        ac.finishWord("yankee");
        ac.finishWord("yyyyyy");

        /*
         * if you want to see what this file looks like, comment out the last line of this test!
         */
        ac.saveUserHistory(uhist_state_fname);

        assertEquals("yodel", ac.nextChar('y').get(0), "yodel should be the first suggestion");
        assertEquals("yankee", ac.nextChar('a').get(0), "yankee should be the first suggestion");
        assertEquals("yarmulke", ac.nextChar('r').get(0), "yarmulke should be the first suggestion");
        assertEquals("yarrow", ac.nextChar('r').get(0), "yarrow should be the first suggestion");
        ac.finishWord("yyyyyy");
        ac.finishWord("yyyyyy");
        ac.finishWord("yyyyyy");
        assertEquals("yyyyyy", ac.nextChar('y').get(0), "yyyyyy should be the first suggestion");
        assertEquals("yodel", ac.nextChar('o').get(0), "yodel should be the first suggestion");

        ac = new AutoCompleter(dict_fname, uhist_state_fname);
        assertEquals("yodel", ac.nextChar('y').get(0), "yodel should be the first suggestion");

        for (String w : nflWordsNYT) {
            ac.finishWord(w);
            ac.finishWord(w);
        }

        sugs = ac.nextChar('y');
        expected = new String[] { "yodel", "yankee", "yard", "yards", "year"};
        assertEquals(5, sugs.size(), "Should have 5 suggestions");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], sugs.get(i),
            "Expected suggestion " + expected[i] + " got " + sugs.get(i));
        }
        
        sugs = ac.nextChar('a');
        expected = new String[] { "yankee", "yard", "yards", "yarmulke", "ya"};
        assertEquals(5, sugs.size(), "Should have 5 suggestions");
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], sugs.get(i),
            "Expected suggestion " + expected[i] + " got " + sugs.get(i));
        }

        ac.saveUserHistory(uhist_state_fname);  // for diagnostic purposes. check what your final file looks like?
    }
}
