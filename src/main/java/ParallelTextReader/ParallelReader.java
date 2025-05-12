package ParallelTextReader;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class ParallelReader implements Runnable {
    String wordToCount;
    String filePath;
    Map<String, Integer> wordCountMap;
    public ParallelReader(String filePath, String WordToCount, Map<String, Integer> wordCountMap) {
        this.wordToCount = WordToCount;
        this.filePath = filePath;
        this.wordCountMap = wordCountMap;
    }
    @Override
    public void run(){
        SimpleReader.countWordOccurrences(filePath, wordToCount, wordCountMap);
    }
}
