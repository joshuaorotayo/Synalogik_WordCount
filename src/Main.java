import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    static final Pattern dot = Pattern.compile("\\.(?=\\s|$)|\\n+|\\r+");

    public static void main(String[] args) {
        Main main = new Main();

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Enter the location and file name of the file to be counted ");
        System.out.println("e.g. test 1.txt for a local file\n");
        main.run();
    }

    public void run() {
        double sumLengths = 0;
        int  sumWords = 0;
        DecimalFormat df = new DecimalFormat("0.###");
        // TODO: 20/11/2021 rework as api, fix POM file 

        try {

            Stream<String> words = Files.lines(Paths.get("test 1.txt"))
                    .map (word -> word.replaceAll(dot.toString(),""))
                    .flatMap(line -> Arrays.stream(line.split(" ")));

            Map<Integer, Integer> frequencyMap = words
                    .collect(Collectors.toConcurrentMap(
                    k -> k.length(),
                    v -> 1, Integer::sum));

            for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
                sumLengths += entry.getKey() * entry.getValue();
                sumWords += entry.getValue();
            }

            System.out.println("Word count = " + sumWords);

            System.out.println("Average word length = " + df.format(sumLengths/sumWords));

            for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
                System.out.println("[NEW] Number of words of length " + entry.getKey()+ " is " + entry.getValue());
            }

            System.out.println("The most frequently occurring word length is " + getMostFrequent(frequencyMap) + ", for word lengths of " + getFrequentList(frequencyMap));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getMostFrequent(Map<Integer, Integer> map) {
        return Collections.max(map.values());
    }

    public List<Integer> getFrequentList(Map<Integer, Integer> map) {
        return map.entrySet().stream()
                .filter(integerIntegerEntry -> integerIntegerEntry.getValue() == Collections.max(map.values()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}

