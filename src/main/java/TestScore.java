public class TestScore {
    private String test;
    private double testScore;

    public TestScore(String test, double testScore) {
        setTest(test);
        setTestScore(testScore);
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
}
