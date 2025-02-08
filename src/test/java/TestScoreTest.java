import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class TestScoreTest {

    @Test
    public void testNullTestName() {
        assertThrows(IllegalArgumentException.class, () -> new TestScore(null, 8.5));
    }

    @Test
    public void testInvalidTestName() {
        assertThrows(IllegalArgumentException.class, () -> new TestScore("", 8.5));
        assertThrows(IllegalArgumentException.class, () -> new TestScore("   ", 8.5));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Test1", "Test2", "Test3", "Test4"})
    public void testValidTestNames(String testName) {
        TestScore score = new TestScore(testName, 8.5);
        assertEquals(testName, score.getTest());
    }

    @Test
    public void testNegativeScoreThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new TestScore("Test1", -1.0));
    }

    @Test
    public void testScoreAboveMaximumThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new TestScore("Test1", 10.1));
        assertThrows(IllegalArgumentException.class, () -> new TestScore("Test1", 11.0));
    }

    @Test
    public void testValidScoreRange() {
        TestScore score = new TestScore("Test1", 0.0);
        assertEquals(0.0, score.getTestScore(), 0.001);

        score.setTestScore(5.5);
        assertEquals(5.5, score.getTestScore(), 0.001);

        score.setTestScore(10.0);
        assertEquals(10.0, score.getTestScore(), 0.001);
    }

    @Test
    public void testSetValidTestName() {
        TestScore score = new TestScore("Test1", 8.5);
        score.setTest("Test2");
        assertEquals("Test2", score.getTest());
    }

    @Test
    public void testSetInvalidTestName() {
        TestScore score = new TestScore("Test1", 8.5);
        assertThrows(IllegalArgumentException.class, () -> score.setTest(null));
        assertThrows(IllegalArgumentException.class, () -> score.setTest(""));
        assertThrows(IllegalArgumentException.class, () -> score.setTest("   "));
    }

    @Test
    public void testSetValidScore() {
        TestScore score = new TestScore("Test1", 8.5);
        score.setTestScore(9.0);
        assertEquals(9.0, score.getTestScore(), 0.001);
    }

    @Test
    public void testSetInvalidScore() {
        TestScore score = new TestScore("Test1", 8.5);
        assertThrows(IllegalArgumentException.class, () -> score.setTestScore(-1.0));
        assertThrows(IllegalArgumentException.class, () -> score.setTestScore(10.1));
    }

    @Test
    public void testTrimming() {
        TestScore score = new TestScore("  Test1  ", 8.5);
        assertEquals("Test1", score.getTest());

        score.setTest("  Test2  ");
        assertEquals("Test2", score.getTest());
    }
}
