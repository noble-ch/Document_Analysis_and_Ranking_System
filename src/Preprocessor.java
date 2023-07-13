import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Noble
 * 
 * Program Objective :- to Split the text into tokens based on white space and punctuation
 *                    - Normalize the word by converting it to lowercase 
 *                    - remove any non-alphabetic characters
 * 
 * Functionality test Positive
 */
public class Preprocessor {
    public List<String> tokenize(String text) {
        // Split the text into tokens based on whitespace and punctuation
        String[] tokens = text.split("[\\s\\p{Punct}]+");
        return Arrays.asList(tokens);
    }

        public String normalize(String word) {
        // Normalize the word by converting it to lowercase and removing any non-alphabetic characters
        StringBuilder normalized = new StringBuilder();
        for (char ch : word.toCharArray()) {
            if (Character.isLetter(ch)) {
                normalized.append(Character.toLowerCase(ch));
            }
        }
        return normalized.toString();
    }
}
