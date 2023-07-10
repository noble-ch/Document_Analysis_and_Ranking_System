# Word Frequency Counter GUI

The **Word Frequency Counter GUI** is a Java program that provides a graphical user interface for analyzing text files and calculating word frequencies and TF-IDF (Term Frequency-Inverse Document Frequency) values. The program allows users to choose text files, preprocess them by removing stopwords, stemming the words, and then calculate the word frequencies and TF-IDF values. Users can also search for specific terms and view the search results ranked by cosine similarity.

## Features

- File selection: Users can choose multiple text files for analysis.
- Preprocessing: The program preprocesses the text by removing stopwords and stemming the words using the Porter stemmer algorithm.
- Word frequency calculation: The program calculates the frequency of each word in the selected files.
- TF-IDF calculation: The program calculates the TF-IDF values for each word in the selected files.
- Search functionality: Users can search for specific terms and view the search results ranked by cosine similarity.
- Ranked document list: The program displays a ranked list of documents based on the search results.

## Usage

1. Launch the program by running the `main` method in the `WordFrequencyCounterGUI` class.
2. The program window will appear with a table and buttons at the top.
3. Click the "Choose Files" button to select text files for analysis. Only files with a `.txt` extension will be displayed in the file chooser dialog.
4. After selecting the files, the program will process them, calculate word frequencies and TF-IDF values, and display the results in the table.
5. Enter a search term in the "Search Term" text field and click the "Search" button to search for the term in the documents. The search results will be displayed in the table and ranked document list.
6. Double-click on a document in the ranked document list to open the corresponding file using the default system program.

## Configuration

The program requires the following libraries:

- `com.formdev.flatlaf.FlatLightLaf`: Provides the FlatLaf look and feel for the program's graphical interface.

Before running the program, ensure that these libraries are available in the classpath.

## Contributing

Contributions to the Word Frequency Counter GUI program are welcome. If you find any issues or have suggestions for improvements, please submit them to the GitHub repository of the program.

## License

The Word Frequency Counter GUI program is licensed under the [MIT License](https://opensource.org/licenses/MIT). Feel free to use and modify the program according to the terms of the license.
