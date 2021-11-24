import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WordCounter {
    static final Pattern dot = Pattern.compile("\\.(?=\\s|$)|\\n+|\\r+");
    Counter counter;

    public static void main(String[] args) {
        WordCounter main = new WordCounter();
        main.CountWords(args[0]);
    }

    public void CountWords(String input) {

        try {

            Stream<String> words = Files.lines(Paths.get("" + input + ""))
                    .map(word -> word.replaceAll(dot.toString(), ""))
                    .flatMap(line -> Arrays.stream(line.split(" ")));

            Map<Integer, Integer> frequencyMap = words
                    .collect(Collectors.toConcurrentMap(
                            String::length,
                            v -> 1, Integer::sum));

            counter = new Counter(frequencyMap);

            printWordCount();

            printAverageWordLength();

            printFrequencies();

            printMostFrequents();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printWordCount() {
        System.out.println("Word count = " + counter.getTotalWords());
    }

    public void printAverageWordLength() {
        DecimalFormat df = new DecimalFormat("0.###");
        System.out.println("Average word length = " + df.format(counter.getSumWordsLength() / counter.getTotalWords()));
    }

    public void printFrequencies() {
        for (Map.Entry<Integer, Integer> entry : counter.getFrequencyMap().entrySet()) {
            System.out.println("Number of words of length " + entry.getKey() + " is " + entry.getValue());
        }
    }

    public void printMostFrequents() {
        System.out.println("The most frequently occurring word length is " + counter.getMaxWord() + ", for word lengths of " + counter.getMostFrequentList());
    }
}

