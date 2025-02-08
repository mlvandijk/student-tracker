import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;

public class StudentTest {
    private final LocalDate testDate = LocalDate.of(2024, 1, 1);

    @Test
    public void testValidConstruction() {
        Student student = new Student("S123", "John Doe");
        assertEquals("S123", student.getStudentId());
        assertEquals("John Doe", student.getStudentName());
        assertTrue(student.getTestScores().isEmpty());
    }

    @Test
    public void testNullIdThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Student(null, "John Doe"));
    }

    @Test
    public void testEmptyIdThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Student("", "John Doe"));
        assertThrows(IllegalArgumentException.class, () -> new Student("   ", "John Doe"));
    }

    @Test
    public void testNullNameThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Student("S123", null));
    }

    @Test
    public void testEmptyNameThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Student("S123", ""));
        assertThrows(IllegalArgumentException.class, () -> new Student("S123", "   "));
    }

    @Test
    public void testSetValidId() {
        Student student = new Student("S123", "John Doe");
        student.setStudentId("S456");
        assertEquals("S456", student.getStudentId());
    }

    @Test
    public void testSetInvalidIdThrowsIllegalArgumentException() {
        Student student = new Student("S123", "John Doe");
        assertThrows(IllegalArgumentException.class, () -> student.setStudentId(null));
        assertThrows(IllegalArgumentException.class, () -> student.setStudentId(""));
        assertThrows(IllegalArgumentException.class, () -> student.setStudentId("   "));
    }

    @Test
    public void testSetValidName() {
        Student student = new Student("S123", "John Doe");
        student.setStudentName("Jane Doe");
        assertEquals("Jane Doe", student.getStudentName());
    }

    @Test
    public void testSetInvalidNameThrowsIllegalArgumentException() {
        Student student = new Student("S123", "John Doe");
        assertThrows(IllegalArgumentException.class, () -> student.setStudentName(null));
        assertThrows(IllegalArgumentException.class, () -> student.setStudentName(""));
        assertThrows(IllegalArgumentException.class, () -> student.setStudentName("   "));
    }

    @Test
    public void testAddTestScore() {
        Student student = new Student("S123", "John Doe");
        TestScore score = new TestScore("Math", 8.5, testDate);
        student.addTestScore(score);

        assertEquals(1, student.getTestScores().size());
        assertEquals(score, student.getTestScores().getFirst());
    }

    @Test
    public void testAddNullTestScore() {
        Student student = new Student("S123", "John Doe");
        assertThrows(IllegalArgumentException.class, () -> student.addTestScore(null));
    }

    @Test
    public void testTestScoresImmutability() {
        Student student = new Student("S123", "John Doe");
        TestScore score = new TestScore("Math", 8.5, testDate);
        student.addTestScore(score);

        List<TestScore> scores = student.getTestScores();
        assertThrows(UnsupportedOperationException.class, () -> scores.add(new TestScore("Physics", 9.0, testDate)));
    }

    @Test
    public void testTrimming() {
        Student student = new Student("  S123  ", "  John Doe  ");
        assertEquals("S123", student.getStudentId());
        assertEquals("John Doe", student.getStudentName());

        student.setStudentId("  S456  ");
        student.setStudentName("  Jane Doe  ");
        assertEquals("S456", student.getStudentId());
        assertEquals("Jane Doe", student.getStudentName());
    }

    @Test
    public void testGetAverageScoreNoScores() {
        Student student = new Student("S123", "John Doe");
        assertEquals(0.0, student.getAverageScore(), 0.001);
    }

    @Test
    public void testGetAverageScoreSingleScore() {
        Student student = new Student("S123", "John Doe");
        student.addTestScore(new TestScore("Math", 8.5, testDate));
        assertEquals(8.5, student.getAverageScore(), 0.001);
    }

    @Test
    public void testGetAverageScoreMultipleScores() {
        Student student = new Student("S123", "John Doe");
        student.addTestScore(new TestScore("Math", 8.5, testDate));
        student.addTestScore(new TestScore("Physics", 7.5, testDate));
        student.addTestScore(new TestScore("Chemistry", 9.0, testDate));
        // Average should be (8.5 + 7.5 + 9.0) / 3 = 8.333...
        assertEquals(8.333, student.getAverageScore(), 0.001);
    }
}
