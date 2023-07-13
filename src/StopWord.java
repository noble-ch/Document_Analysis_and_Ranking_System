
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Noble
 * 
 * In this program the algorithm takes a group of common 
 * words that appears frequently in english language as an array of Strings 
 * and compare to the indexed terms in order to remove them...
 * 
 * purpose :- to facilitate search and boost processing speed  
 * 
 * expected goal :- remove stop words from document corpus
 * 
 * actual result : - positive
 */

public class StopWord {
        private List<String> stopwords;

    public StopWord() {
        stopwords = Arrays.asList (        "a", "about", "above", "after", "again", "against", "all", "am", "an", "and",
        "any", "are", "as", "at", "be", "because", "been", "before", "being", "below",
        "between", "both", "but", "by", "could", "did", "do", "does", "doing", "down",
        "during", "each", "few", "for", "from", "further", "had", "has", "have", "having",
        "he", "he'd", "he'll", "he's", "her", "here", "here's", "hers", "herself", "him",
        "himself", "his", "how", "how's", "i", "i'd", "i'll", "i'm", "i've", "if", "in",
        "into", "is", "it", "it's", "its", "itself", "let's", "me", "more", "most", "my",
        "myself", "nor", "of", "on", "once", "only", "or", "other", "ought", "our", "ours",
        "ourselves", "out", "over", "own", "same", "she", "she'd", "she'll", "she's", "should",
        "so", "some", "such", "than", "that", "that's", "the", "their", "theirs", "them",
        "themselves", "then", "there", "there's", "these", "they", "they'd", "they'll", "they're",
        "they've", "this", "those", "through", "to", "too", "under", "until", "up", "very",
        "was", "we", "we'd", "we'll", "we're", "we've", "were", "what", "what's", "when",
        "when's", "where", "where's", "which", "while", "who", "who's", "whom", "why", "why's",
        "with", "would", "you", "you'd", "you'll", "you're", "you've", "your", "yours",
        "yourself", "yourselves"); // Add your own list of stopwords
        }
      public List<String> removeStopwords(List<String> tokens) {
        // Remove stopwords from the list of tokens
        List<String> filteredTokens = new ArrayList<>();
        for (String token : tokens) {
            if (!stopwords.contains(token.toLowerCase())) {
                filteredTokens.add(token);
            }
        }
        return filteredTokens;
    }

    List<String> removeStopwords(String searchTerm) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
