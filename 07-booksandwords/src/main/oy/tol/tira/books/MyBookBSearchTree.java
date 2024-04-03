package oy.tol.tira.books;

import java.io.*;
import java.util.Locale;

public class MyBookBSearchTree implements Book {
    private KeyValueBSearchTree<String, Integer> words;
    private String bookFile = null;

    private String wordsToIgnoreFile = null;
    private WordFilter filter;
    private int uniqueWordCount = 0;
    private int totalWordCount = 0;
    private int ignoredWordsTotal = 0;
    private long loopCount = 0;

    public MyBookBSearchTree() {
        this.words = new KeyValueBSearchTree<>();
        this.filter = new WordFilter();

    }
    @Override
    public void setSource(String fileName, String ignoreWordsFile) throws FileNotFoundException {
        if (!checkFile(fileName) || !checkFile(ignoreWordsFile)) {
            throw new FileNotFoundException("Cannot find the specified files");
        }
        bookFile = fileName;
        wordsToIgnoreFile = ignoreWordsFile;
        try{
            filter.readFile(ignoreWordsFile);
        } catch (IOException e) {
            throw new FileNotFoundException("Error reading file: " + e.getMessage());
        }
    }


    @Override
    public void countUniqueWords() throws IOException{
        if (bookFile == null || wordsToIgnoreFile == null) {
            throw new IOException("No file(s) specified");
        }

        uniqueWordCount = 0;
        totalWordCount = 0;
        loopCount = 0;
        ignoredWordsTotal = 0;

        words = new KeyValueBSearchTree<>();
        filter = new WordFilter();
        filter.readFile(wordsToIgnoreFile);

        try (BufferedReader reader = new BufferedReader(new FileReader(bookFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line);
            }
        }
        uniqueWordCount = words.size();
    }

    private void processLine(String line){
        for (String word : line.split("\\P{IsAlphabetic}+")) {
            word = word.toLowerCase(Locale.ROOT);
            if (word.length() > 1) {
                addToWords(word);
            }
        }
    }

    private void addToWords(String word){
        if (!filter.shouldFilter(word)) {
            Integer count = words.find(word);
            if (count == null) {
                uniqueWordCount++;
            }
            count = (count == null) ? 1 : count + 1;
            words.add(word, count);
            totalWordCount++;
        } else {
            ignoredWordsTotal++;
        }
    }


    @Override
    public void report() {
        if (words == null) {
            System.out.println("*** No words to report! ***");
            return;
        }
        System.out.println("Listing words from a file: " + bookFile);
        System.out.println("Ignoring words from a file: " + wordsToIgnoreFile);
        System.out.println("Sorting the results...");

        // Sorting the KeyValueHashTable before reporting
        Pair<String, Integer>[] sortedWords = words.toSortedArray();
        for (int index = 0; index < Math.min(100, sortedWords.length); index++) {
            String word = String.format("%-20s", sortedWords[index].getKey()).replace(' ', '.');
            System.out.format("%4d. %s %6d%n", index + 1, word, sortedWords[index].getValue());
        }
        System.out.println("Count of words in total: " + totalWordCount);
        System.out.println("Count of unique words:    " + uniqueWordCount);
        System.out.println("Count of words to ignore:    " + filter.ignoreWordCount());
        System.out.println("Ignored words count:      " + ignoredWordsTotal);
        System.out.println("How many times the inner loop rolled: " + loopCount);
    }

    @Override
    public void close() {
        words = new KeyValueBSearchTree<>();
        filter.close();
        uniqueWordCount = 0;
        totalWordCount = 0;
        ignoredWordsTotal = 0;
    }

    @Override
    public int getUniqueWordCount() {
        return uniqueWordCount;
    }

    @Override
    public int getTotalWordCount() {
        return totalWordCount;
    }

    @Override
    public String getWordInListAt(int position) {
        Pair<String, Integer>[] sortedWords = words.toSortedArray();
        if (position >= 0 && position < sortedWords.length) {
            return sortedWords[position].getKey();
        }
        return null;
    }

    @Override
    public int getWordCountInListAt(int position) {
        Pair<String, Integer>[] sortedWords = words.toSortedArray();
        if (position >= 0 && position < sortedWords.length) {
            return sortedWords[position].getValue();
        }
        return -1;
    }
    private boolean checkFile(String fileName) {
        if (fileName != null) {
            File file = new File(fileName);
            return file.exists() && !file.isDirectory();
        }
        return false;
    }
}
