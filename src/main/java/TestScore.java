import java.time.LocalDate;

public class TestScore {
    private String test;
    private double testScore;
    private LocalDate date;

    public TestScore(String test, double testScore, LocalDate date) {
        setTest(test);
        setTestScore(testScore);
        setDate(date);
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        if (test == null || test.trim().isEmpty()) {
            throw new IllegalArgumentException("Test name cannot be null or empty");
        }
        this.test = test.trim();
    }

    public double getTestScore() {
        return testScore;
    }

    public void setTestScore(double testScore) {
        if (testScore < 0) {
            throw new IllegalArgumentException("Test score cannot be negative");
        }
        if (testScore > 10) {
            throw new IllegalArgumentException("Test score cannot be greater than 10");
        }
        this.testScore = testScore;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date cannot be in the future");
        }
        this.date = date;
    }
}
