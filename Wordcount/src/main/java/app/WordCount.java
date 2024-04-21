package app.wordcount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WordCount {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java WordCount <path_to_file>");
            System.exit(1);
        }

        String filePath = args[0];

        try {
            Map<String, Integer> wordCountMap = countWords(filePath);
            printWordCount(wordCountMap);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    static Map<String, Integer> countWords(String filePath) throws IOException {
        Map<String, Integer> wordCountMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");

                for (String word : words) {
                    word = word.toLowerCase(); // Case insensitive
                    wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1);
                }
            }
        }

        return wordCountMap;
    }

    private static void printWordCount(Map<String, Integer> wordCountMap) {
        // Sort the map by values (word counts)
        Map<String, Integer> sortedWordCountMap = new TreeMap<>((word1, word2) ->
                wordCountMap.get(word2).compareTo(wordCountMap.get(word1)));

        sortedWordCountMap.putAll(wordCountMap);

        // Print word counts
        for (Map.Entry<String, Integer> entry : sortedWordCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}