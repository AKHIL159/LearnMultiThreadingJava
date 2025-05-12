package ParallelTextReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleReader {
    public static void countWordOccurrences(String filePath, String wordToCount, Map<String, Integer> wordCountMap) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if(!word.isEmpty()) {
                        wordCountMap.merge(word, 1, Integer::sum);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}