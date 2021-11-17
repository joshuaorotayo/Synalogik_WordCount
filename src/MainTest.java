import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    public Main m = new Main();

    @Test
    void main() {
        m.setFileName("test 1.txt");
        m.run();
    }

    @Test
    void testGetAverage() {
        assertEquals("2.5", m.getAverage(5.0, 2.0), "Average length of words calculated incorrectly");
        assertEquals("718", m.getAverage(2154, 3.0), "Average length of words calculated incorrectly");
        assertEquals("6.33", m.getAverage(19, 3.0), "Average length of words calculated incorrectly");
    }

    @Test
    void testSetPathName(){
        m.setFileName("test 3.txt");
        assertEquals("test 3.txt", m.getPathName().toString(), "New Path name not set correctly");
    }

    @Test
    void testGetPathName(){
        assertEquals("bible_daily.txt", m.getPathName().toString(),"Path name does not match saved path name");
    }
}