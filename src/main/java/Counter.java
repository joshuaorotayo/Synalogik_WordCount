import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Counter {
    private final int maxWord;
    private final Map<Integer, Integer> frequencyMap;
    private int totalWords;
    private double sumWordsLength;
    private List<Integer> frequentList;

    public Counter(Map<Integer, Integer> map) {
        totalWords = setTotalWords(map);
        maxWord = setMaxWord(map);
        sumWordsLength = setSumWordsLength(map);
        frequentList = setMostFrequentList(map);
        frequencyMap = map;
    }

    public int getTotalWords() {
        return totalWords;
    }

    public int setTotalWords(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            totalWords += entry.getValue();
        }
        return totalWords;
    }

    public int getMaxWord() {
        return maxWord;
    }

    public int setMaxWord(Map<Integer, Integer> map) {
        return Collections.max(map.values());
    }

    public double getSumWordsLength() {
        return sumWordsLength;
    }

    public double setSumWordsLength(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            sumWordsLength += entry.getKey() * entry.getValue();
        }
        return sumWordsLength;
    }

    public List<Integer> getMostFrequentList() {
        return frequentList;
    }

    public List<Integer> setMostFrequentList(Map<Integer, Integer> map) {
        return frequentList = map.entrySet().stream()
                .filter(integerIntegerEntry -> Objects.equals(integerIntegerEntry.getValue(), Collections.max(map.values())))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public Map<Integer, Integer> getFrequencyMap() {
        return frequencyMap;
    }
}
