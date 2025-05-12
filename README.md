This Java project reads all `.txt` files in a directory and counts the occurrences of words, either sequentially or using parallel execution with multithreading.

## 🛠 Features
- Counts total occurrences of a word across multiple files.
- Supports both sequential and parallel execution modes.
- Uses `ExecutorService` and `CompletableFuture` or `Runnable` with shared `ConcurrentHashMap`.
- Thread-safe word counting with `Map.merge()`.

## 📁 Folder Structure
```
src/
└── ParallelTextReader/
    ├── CounterDriver.java
    ├── ParallelReader.java
    └── SimpleReader.java
```

## 🚀 How to Run
1. Place `.txt` files in `src/ParallelTextReader/TextFiles/`
2. Run `CounterDriver.java`
3. Enter the word to search and choose execution mode:
   - `1` = Parallel
   - `2` = Sequential

## ✅ Requirements
- Java 17+
- IntelliJ IDEA or any Java IDE

## ✍️ Author
Made for multithreading practice and understanding Java concurrency.
