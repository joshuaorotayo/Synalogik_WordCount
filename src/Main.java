import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    Path fileName = Path.of("test 1.txt");
    static final Pattern dot = Pattern.compile("\\.(?=\\s|$)");

    public static void main(String[] args) {
        Main main = new Main();

        //Scanner s =  new Scanner(System.in);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Enter the location and file name of the file to be counted ");
        System.out.println("e.g. test 1.txt for a local file\n");
        main.run();
    }

    public void run(){
        double sumLengths = 0;

        try {
            String fileLocation = Files.readString(getPathName());
            Matcher m = dot.matcher(fileLocation);
            String[] split = m.replaceAll("").split(" ");

            Stream<String> fileContent = Files.lines(Paths.get("test 1.txt"));

            HashMap<Integer, Integer> occurrenceMap = new HashMap<>();
            for (String s : split) {
                sumLengths = sumLengths + s.length();
                occurrenceMap.merge(s.length(), 1, Integer::sum);
            }

            System.out.println("Word Count = " + split.length);

            System.out.println("Average word length = " + getAverage(sumLengths, split.length));

            printOccurrenceCount(occurrenceMap);

            int mostFrequent = getMostFrequent(occurrenceMap);
            System.out.println("The most frequently occurring word length is " + mostFrequent + ", for word length(s) of " + getFrequentList(occurrenceMap, mostFrequent));

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
        DecimalFormat df = new DecimalFormat("0.###");
        return df.format(sum_of_lengths/number_of_words);
    }

    public void printOccurrenceCount(HashMap<Integer, Integer> map){
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println("Number of words of length " + entry.getKey().toString() + " is " + entry.getValue().toString());
        }
    }

    public int getMostFrequent(HashMap<Integer, Integer> map) {
        return Collections.max(map.values());
    }

    public List<Integer> getFrequentList(HashMap<Integer, Integer> map, int max){
       List<Integer> frequentList = map.entrySet().stream()
                .filter(integerIntegerEntry -> integerIntegerEntry.getValue() == max)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return  frequentList;
    }
}

