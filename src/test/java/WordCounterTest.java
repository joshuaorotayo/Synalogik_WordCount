import junit.framework.TestCase;
import org.junit.BeforeClass;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class WordCounterTest extends TestCase {
    private static final File resourcesDirectory = new File("src/test/java/data");
    private static final String wordFile = resourcesDirectory.getAbsolutePath() + "/test 1.txt";
    private static WordCounter testCounter;

    @Override
    public void setUp() throws Exception {
        testCounter = new WordCounter();
        testCounter.setupCounter(wordFile);
        super.setUp();
    }

    public void testMain() {
        assertNotNull(testCounter.counter);
    }

    public void testCountWords() {
        assertEquals(9, testCounter.counter.getTotalWords());
    }

    public void testSumWordsLength() {
        assertEquals(41.0, testCounter.counter.getSumWordsLength());
    }

    public void testAverageWordLength() {
        DecimalFormat df = new DecimalFormat("0.###");
        assertEquals("4.556", df.format(testCounter.counter.getSumWordsLength() / testCounter.counter.getTotalWords()));
    }

    public void testMostFrequents() {
        List<Integer> frequentArray = Arrays.asList(4,5);
        assertEquals(testCounter.counter.getMostFrequentList(), frequentArray);
    }
}