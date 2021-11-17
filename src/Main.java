import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    Path fileName = Path.of("bible_daily.txt");

    public static void main(String[] args) {

        Main main = new Main();
        main.run();
    }

    public void run(){
        double sumLengths = 0;
        int count;
        try {
            String fileLocation = Files.readString(getPathName());

            String[] split = fileLocation.split("[^\\p{L}0-9\"]+");
            count = split.length;

            HashMap<Integer, Integer> occurrenceMap = new HashMap<>();
            for (String s : split) {
                double length = s.length();
                sumLengths = sumLengths + length;
                occurrenceMap.merge((int) length, 1, Integer::sum);
            }

            System.out.println("Word Count = " + count);

            System.out.println("Average word length = " + getAverage(sumLengths, count));

            printOccurrenceCount(occurrenceMap);

            printMostFrequent(occurrenceMap);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFileName(String fileName) {
        this.fileName = Path.of(fileName);
    }

    public Path getPathName(){
        return fileName;
    }

    public String getAverage(double sum_of_lengths, double number_of_words){

        DecimalFormat df = new DecimalFormat("0.##");
        return df.format(sum_of_lengths/number_of_words);
    }

    public void printOccurrenceCount(HashMap<Integer, Integer> map){
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Number of words of length " + entry.getKey().toString() + " is " + entry.getValue().toString());
        }
    }

    public void printMostFrequent(HashMap<Integer, Integer> map) {

        int max = Collections.max(map.values());

        List<Integer> frequentList = map.entrySet().stream()
                .filter(integerIntegerEntry -> integerIntegerEntry.getValue() == max)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println("The most frequently occurring word length is " + max + ", for word length(s) of " + frequentList);
    }
}

