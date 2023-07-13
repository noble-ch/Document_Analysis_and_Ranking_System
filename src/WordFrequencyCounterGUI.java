import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;
import java.util.List;

public class WordFrequencyCounterGUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton fileChooserButton;
    private JButton searchButton;
    private JTextField searchTextField;
    private Preprocessor preprocessor;
    private Stemmer stemmer;
    private StopWord stopword;
    private Map<String, Map<String, Integer>> wordFrequencyMap;
    private Map<String, Map<String, Double>> tfIdfMap;

    public WordFrequencyCounterGUI() {
    setTitle("Word Frequency Counter");
        setLayout(new BorderLayout());
   
        
        setVisible(true);
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        wordFrequencyMap = new HashMap<>();
        tfIdfMap = new HashMap<>();
        preprocessor = new Preprocessor();
        stemmer = new Stemmer();
        stopword = new StopWord();
        

        tableModel = new DefaultTableModel(new Object[]{"Term", "Total Occurrence", "Occurrences in Documents"}, 0);
        table = new JTable(tableModel);
      

        JScrollPane scrollPane = new JScrollPane(table);

        fileChooserButton = new JButton("Choose Files");
            fileChooserButton.setPreferredSize(new Dimension(100, 25));
            
             fileChooserButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    fileChooserButton.setBackground(Color.LIGHT_GRAY);
                }
                @Override
                public void mouseExited(MouseEvent e) {
                     fileChooserButton.setBackground(Color.WHITE);
                }
            });
               
           searchButton = new JButton("Search");
           searchButton.setForeground(Color.WHITE);
           searchButton.setBackground(Color.decode("#005073"));
            searchButton.setPreferredSize(new Dimension(80, 25));
            searchButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    searchButton.setBackground(Color.decode("#189ad3"));
                }
                @Override
                public void mouseExited(MouseEvent e) {
                    searchButton.setBackground(Color.decode("#005073"));
                }
        
            });
        
     
        searchTextField = new JTextField(30);
        searchTextField.setPreferredSize(new Dimension(30, 25));
       

        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(1000, 35));
        buttonPanel.add(fileChooserButton);
        buttonPanel.add(new JLabel("Search Term:"));
        buttonPanel.add(searchTextField);
        buttonPanel.add(searchButton);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.NORTH);

        fileChooserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setMultiSelectionEnabled(true);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
                fileChooser.setFileFilter(filter);

                int option = fileChooser.showOpenDialog(WordFrequencyCounterGUI.this);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File[] selectedFiles = fileChooser.getSelectedFiles();
                    processFiles(selectedFiles);
                    calculateTfIdf();
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchTerm = searchTextField.getText().trim().toLowerCase();
                if (!searchTerm.isEmpty()) {
                    searchWord(searchTerm);
                }
            }
        });
    }

    private void processFiles(File[] files) {
        for (File file : files) {
            if (file.isFile()) {
                String filename = file.getName();
                try (Scanner scanner = new Scanner(new FileReader(file))) {
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        List<String> tokens = preprocessor.tokenize(line);
                        List<String> filteredTokens = stopword.removeStopwords(tokens);
                        

                        for (String token : filteredTokens) {
                            String normalizedToken = preprocessor.normalize(token);
                            String stemmedToken = stemmer.porterStemmer(normalizedToken);
                           

                            if (!wordFrequencyMap.containsKey(stemmedToken)) {
                                wordFrequencyMap.put(stemmedToken, new HashMap<>());
                            }

                            if (!wordFrequencyMap.get(stemmedToken).containsKey(filename)) {
                                wordFrequencyMap.get(stemmedToken).put(filename, 1);
                            } else {
                                int count = wordFrequencyMap.get(stemmedToken).get(filename);
                                wordFrequencyMap.get(stemmedToken).put(filename, count + 1);
                            }
                        }
                       
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        updateTable();
    }

 private void updateTable() {
    tableModel.setRowCount(0);
    for (String term : wordFrequencyMap.keySet()) {
        int totalOccurrences = 0;
        Map<String, Integer> occurrencesInDocuments = wordFrequencyMap.get(term);

        for (int count : occurrencesInDocuments.values()) {
            totalOccurrences += count;
        }
        tableModel.addRow(new Object[]{term, totalOccurrences, occurrencesInDocuments});
    }

    // Remove rows with term length <= 1
    for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
        String term = tableModel.getValueAt(i, 0).toString();
        if (term.length() <= 1) {
            tableModel.removeRow(i);
        }
    }
}

    private void calculateTfIdf() {
        int totalDocuments = wordFrequencyMap.values().stream()
                .map(Map::keySet)
                .flatMap(Collection::stream)
                .distinct()
                .toArray().length;

        for (String term : wordFrequencyMap.keySet()) {
            Map<String, Integer> documentFrequency = wordFrequencyMap.get(term);

            double idf = Math.log((double) totalDocuments / documentFrequency.size());

            tfIdfMap.put(term, new HashMap<>());

            for (String document : documentFrequency.keySet()) {
                int termFrequency = documentFrequency.get(document);
                double tfIdf = termFrequency * idf;
                tfIdfMap.get(term).put(document, tfIdf);
            }
        }
    }

    private void searchWord(String searchTerm) {
        Map<String, Double> queryTfIdfMap = new HashMap<>();

        List<String> queryTokens = preprocessor.tokenize(searchTerm);
        List<String> filteredQueryTokens = stopword.removeStopwords(queryTokens);

        for (String token : filteredQueryTokens) {
            String normalizedToken = preprocessor.normalize(token);
            String stemmedToken = stemmer.porterStemmer(normalizedToken);

            if (tfIdfMap.containsKey(stemmedToken)) {
                queryTfIdfMap.put(stemmedToken, tfIdfMap.get(stemmedToken).values().stream()
                        .reduce(0.0, Double::sum));
            }
        }

        List<SearchResult> searchResults = new ArrayList<>();

        for (String document : wordFrequencyMap.values().stream()
                .map(Map::keySet)
                .flatMap(Collection::stream)
                .distinct()
                .toArray(String[]::new)) {
            double cosineSimilarity = calculateCosineSimilarity(queryTfIdfMap, document);

            if (cosineSimilarity > 0) {
                Map<String, Integer> occurrencesInDocuments = new HashMap<>();
                for (String term : wordFrequencyMap.keySet()) {
                    Map<String, Integer> documentFrequency = wordFrequencyMap.get(term);
                    if (documentFrequency.containsKey(document)) {
                        occurrencesInDocuments.put(term, documentFrequency.get(document));
                    }
                }

                searchResults.add(new SearchResult(document, cosineSimilarity, occurrencesInDocuments));
            }
        }

        if (!searchResults.isEmpty()) {
            // Sort search results based on cosine similarity
            Collections.sort(searchResults);

            // Prepare result message
            StringBuilder result = new StringBuilder();
            result.append("Search Results for '").append(searchTerm).append("':\n");

            // Display ranked search results
            for (SearchResult searchResult : searchResults) {
                result.append("- Document: ").append(searchResult.getDocument()).append("\n");
                result.append("  Cosine Similarity: ").append(searchResult.getCosineSimilarity()).append("\n");
                result.append("  Occurrences in Documents: ").append(searchResult.getDocument()).append("\n");
                result.append("\n");
            }

            // Show result in dialog
            JOptionPane.showMessageDialog(this, result.toString(), "Search Results", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No results found for '" + searchTerm + "'", "Search Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private double calculateCosineSimilarity(Map<String, Double> queryTfIdfMap, String document) {
        Map<String, Double> documentTfIdfMap = new HashMap<>();
        for (String term : wordFrequencyMap.keySet()) {
            if (tfIdfMap.get(term).containsKey(document)) {
                documentTfIdfMap.put(term, tfIdfMap.get(term).get(document));
            } else {
                documentTfIdfMap.put(term, 0.0);
            }
        }

        double dotProduct = 0.0;
        double queryMagnitude = 0.0;
        double documentMagnitude = 0.0;

        for (String term : queryTfIdfMap.keySet()) {
            double queryTfIdf = queryTfIdfMap.get(term);
            double documentTfIdf = documentTfIdfMap.get(term);

            dotProduct += queryTfIdf * documentTfIdf;
            queryMagnitude += Math.pow(queryTfIdf, 2);
            documentMagnitude += Math.pow(documentTfIdf, 2);
        }

        queryMagnitude = Math.sqrt(queryMagnitude);
        documentMagnitude = Math.sqrt(documentMagnitude);

        if (queryMagnitude == 0 || documentMagnitude == 0) {
            return 0.0;
        }

        return dotProduct / (queryMagnitude * documentMagnitude);
    }

    private class SearchResult implements Comparable<SearchResult> {
        private String document;
        private double cosineSimilarity;
        private Map<String, Integer> occurrencesInDocuments;

        public SearchResult(String document, double cosineSimilarity, Map<String, Integer> occurrencesInDocuments) {
            this.document = document;
            this.cosineSimilarity = cosineSimilarity;
            this.occurrencesInDocuments = occurrencesInDocuments;
        }

        public String getDocument() {
            return document;
        }

        public double getCosineSimilarity() {
            return cosineSimilarity;
        }

        public Map<String, Integer> getOccurrencesInDocuments() {
            return occurrencesInDocuments;
        }

      
        @Override
        public int compareTo(SearchResult other) {
            return Double.compare(other.cosineSimilarity, this.cosineSimilarity);
        }
    }
    
    
    private void openFile(File file) {
        try {
            // Open the file using the default system program
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            ex.printStackTrace();
         //   JOptionPane.showMessageDialog(FileOpenerGUI.this, "Error opening file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        
        
        
           try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put("Button.arc",999);
            UIManager.put("Component.arc",999);
            UIManager.put("TextComponent.arc", 999);
            UIManager.put("TableComponent.arc", 999);
            
        } catch (UnsupportedLookAndFeelException ex) {
                 System.out.println("hello");
       }
        // Set look and feel to FlatLaf
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WordFrequencyCounterGUI().setVisible(true);
            }
        });
    }
}
