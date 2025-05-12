package ParallelTextReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.*;

public class CounterDriver {
    static final String directoryPath = "E:/intellij idea/LearnMulti/src/main/java/ParallelTextReader/TextFiles";
    static Map<String, Integer> wordCountMap = new ConcurrentHashMap<>();

    public static Map<String, Integer> countWordOccurrencesInDirectory(String directoryPath, String wordToCount, boolean parallel) {
//        int totalCount = 0;
        File directory = new File(directoryPath);
        File[] files = directory.listFiles((dir, name) -> name.endsWith(".txt"));
        if(parallel) {
            ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//            Map<String, Integer> wordCountMap = new ConcurrentHashMap<>();
//            List<Future<Integer>> futures = new ArrayList<>();
//            try{
//                for (File file : files) {
//                    futures.add(executor.submit(new ParallelReader(wordToCount, file.getAbsolutePath())));
//                }
//                for (Future<Integer> future : futures) {
//                    totalCount += future.get();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            finally {
//                executor.shutdown();
//            }
//            List<CompletableFuture<Integer>> futures = new ArrayList<>();
            List<Future<?>> futures = new ArrayList<>();
            try{
                for(File file: files){
//                    futures.add(CompletableFuture.supplyAsync(() -> new ParallelReader(file.getAbsolutePath(),
//                            wordToCount, wordCountMap).call(), executor));
                    futures.add(executor.submit(new ParallelReader(file.getAbsolutePath(), wordToCount, wordCountMap)));
                }
                for (Future<?> future : futures) {
                    future.get();
                }
            }
            catch (Exception e){ e.printStackTrace();}
            finally {
                executor.shutdown();
            }
        }
        else{
            for (File file : files) {
                SimpleReader.countWordOccurrences(file.getAbsolutePath(), wordToCount, wordCountMap);
            }
        }
        return wordCountMap;
    }

    public static void main(String[] args) {
        // Configuration
        Scanner sc = new Scanner(System.in);
//        System.out.print("Enter the word to count: ");
//        final String wordToCount = sc.nextLine();
        final String wordToCount = "";
        System.out.print("Enter the mode of execution(Parallel = 1, Sequential = 2): ");
        final boolean mode = sc.nextInt() == 1;
        int wordCount = 5;
//        System.out.println("Searching for given word...");
        System.out.print("How many words are to be searched? Ans = ");
        wordCount = sc.nextInt();
        //Execution and Timing
        long startTime = System.currentTimeMillis();
        Map<String, Integer> occurrences = countWordOccurrencesInDirectory(directoryPath, wordToCount, mode);
        String isParallel = mode ? "Parallel" : "Sequential";
        System.out.println("Current execution mode: " + isParallel);
//        System.out.println("The word \"" + wordToCount + "\" appears " + occurrences + " times.");
        System.out.printf("Top ",wordCount," repeated words are: ");
        occurrences.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(wordCount)
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
//        System.out.println("total time taken: ~" + (System.currentTimeMillis() - startTime)/1000 + "s");
        System.out.println("total time taken: " + (System.currentTimeMillis() - startTime) + "ms");
    }
}
