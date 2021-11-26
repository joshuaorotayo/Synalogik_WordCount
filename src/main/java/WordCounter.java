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
    static final Pattern fullstops = Pattern.compile("\\.(?=\\s|$)|\\n+|\\r+|\"");
    Counter counter;

    public static void main(String[] args) {
        if(args.length == 0)
        {
            System.out.println("Please enter a file name to be counted. Proper Usage is: java WordCounter [filename]");
            System.exit(0);
        }
       else
       {
            WordCounter main = new WordCounter();
            main.setupCounter(args[0]);
            main.printResults();
        }
    }

    public void setupCounter(String input) {
        try {
            Stream<String> words = Files.lines(Paths.get("" + input + ""))
                    .map(word -> word.trim().replaceAll(fullstops.toString(), ""))
                    .flatMap(line -> Arrays.stream(line.split(" ")).filter(item -> !item.trim().isEmpty()));

            Map<Integer, Integer> frequencyMap = words
                    .collect(Collectors.toConcurrentMap(
                            String::length,
                            v -> 1, Integer::sum));

            counter = new Counter(frequencyMap);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printResults(){
        printWordCount();

        printAverageWordLength();

        printFrequencies();

        printMostFrequents();
    }

    public void printWordCount() {
        System.out.println("\nWord count = " + counter.getTotalWords());
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

