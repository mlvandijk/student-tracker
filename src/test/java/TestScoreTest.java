import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.time.LocalDate;

public class TestScoreTest {
    private final LocalDate testDate = LocalDate.of(2024, 1, 1);

    @Test
    public void testNullTestName() {
        assertThrows(IllegalArgumentException.class, () -> new TestScore(null, 8.5, testDate));
    }

    @Test
    public void testInvalidTestName() {
        assertThrows(IllegalArgumentException.class, () -> new TestScore("", 8.5, testDate));
        assertThrows(IllegalArgumentException.class, () -> new TestScore("   ", 8.5, testDate));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Test1", "Test2", "Test3", "Test4"})
    public void testValidTestNames(String testName) {
        TestScore score = new TestScore(testName, 8.5, testDate);
        assertEquals(testName, score.getTest());
    }

    @Test
    public void testNegativeScoreThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new TestScore("Test1", -1.0, testDate));
    }

    @Test
    public void testScoreAboveMaximumThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new TestScore("Test1", 10.1, testDate));
        assertThrows(IllegalArgumentException.class, () -> new TestScore("Test1", 11.0, testDate));
    }

    @Test
    public void testValidScoreRange() {
        TestScore score = new TestScore("Test1", 0.0, testDate);
        assertEquals(0.0, score.getTestScore(), 0.001);

        score.setTestScore(5.5);
        assertEquals(5.5, score.getTestScore(), 0.001);

        score.setTestScore(10.0);
        assertEquals(10.0, score.getTestScore(), 0.001);
    }

    @Test
    public void testSetValidTestName() {
        TestScore score = new TestScore("Test1", 8.5, testDate);
        score.setTest("Test2");
        assertEquals("Test2", score.getTest());
    }

    @Test
    public void testSetInvalidTestName() {
        TestScore score = new TestScore("Test1", 8.5, testDate);
        assertThrows(IllegalArgumentException.class, () -> score.setTest(null));
        assertThrows(IllegalArgumentException.class, () -> score.setTest(""));
        assertThrows(IllegalArgumentException.class, () -> score.setTest("   "));
    }

    @Test
    public void testSetValidScore() {
        TestScore score = new TestScore("Test1", 8.5, testDate);
        score.setTestScore(9.0);
        assertEquals(9.0, score.getTestScore(), 0.001);
    }

    @Test
    public void testSetInvalidScore() {
        TestScore score = new TestScore("Test1", 8.5, testDate);
        assertThrows(IllegalArgumentException.class, () -> score.setTestScore(-1.0));
        assertThrows(IllegalArgumentException.class, () -> score.setTestScore(10.1));
    }

    @Test
    public void testTrimming() {
        TestScore score = new TestScore("  Test1  ", 8.5, testDate);
        assertEquals("Test1", score.getTest());

        score.setTest("  Test2  ");
        assertEquals("Test2", score.getTest());
    }

    @Test
    public void testNullDate() {
        assertThrows(IllegalArgumentException.class, () -> new TestScore("Test1", 8.5, null));
    }

    @Test
    public void testSetNullDate() {
        TestScore score = new TestScore("Test1", 8.5, testDate);
        assertThrows(IllegalArgumentException.class, () -> score.setDate(null));
    }

    @Test
    public void testValidDate() {
        LocalDate date = LocalDate.of(2024, 1, 1);
        TestScore score = new TestScore("Test1", 8.5, date);
        assertEquals(date, score.getDate());

        LocalDate newDate = LocalDate.of(2024, 1, 2);
        score.setDate(newDate);
        assertEquals(newDate, score.getDate());
    }

    @Test
    public void testInvalidDateFormat() {
        assertThrows(java.time.format.DateTimeParseException.class, 
            () -> LocalDate.parse("2024-13-01")); // Invalid month
        assertThrows(java.time.format.DateTimeParseException.class, 
            () -> LocalDate.parse("2024-01-32")); // Invalid day
        assertThrows(java.time.format.DateTimeParseException.class, 
            () -> LocalDate.parse("2024/01/01")); // Wrong format
        assertThrows(java.time.format.DateTimeParseException.class, 
            () -> LocalDate.parse("01-01-2024")); // Wrong format
        assertThrows(java.time.format.DateTimeParseException.class, 
            () -> LocalDate.parse("not-a-date")); // Invalid format
    }

    @Test
    public void testFutureDate() {
        LocalDate futureDate = LocalDate.of(2100, 1, 1);
        assertThrows(IllegalArgumentException.class, () -> new TestScore("Test1", 8.5, futureDate));

        TestScore score = new TestScore("Test1", 8.5, LocalDate.now());
        assertThrows(IllegalArgumentException.class, () -> score.setDate(futureDate));
    }

    @Test
    public void testVeryOldDate() {
        LocalDate oldDate = LocalDate.of(1800, 1, 1);
        TestScore score = new TestScore("Test1", 8.5, oldDate);
        assertEquals(oldDate, score.getDate());
    }
}
