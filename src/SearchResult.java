
/**
 * 
 * @author Noble
 * 
 * program objective :- Sort in descending order based on occurrence count
 * 
 * Functionality test Positive

 */
public class SearchResult implements Comparable<SearchResult> {
    private String document;
    private int count;

    public SearchResult(String document, int count) {
        this.document = document;
        this.count = count;
    }

    public String getDocument() {
        return document;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int compareTo(SearchResult other) {
        // Sort in descending order based on occurrence count
        return Integer.compare(other.count, this.count);
    }
}
