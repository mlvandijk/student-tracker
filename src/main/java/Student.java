import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Student {
    private String studentId;
    private String studentName;
    private final List<TestScore> testScores;

    public Student(String studentId, String studentName) {
        setStudentId(studentId);
        setStudentName(studentName);
        this.testScores = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty");
        }
        this.studentId = studentId.trim();
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("Student name cannot be null or empty");
        }
        this.studentName = studentName.trim();
    }

    public List<TestScore> getTestScores() {
        return Collections.unmodifiableList(testScores);
    }

    public void addTestScore(TestScore testScore) {
        if (testScore == null) {
            throw new IllegalArgumentException("Test score cannot be null");
        }
        this.testScores.add(testScore);
    }

    public double getAverageScore() {
        if (testScores.isEmpty()) {
            return 0.0;
        }
        double totalScore = 0.0;
        for (TestScore testScore : testScores) {
            totalScore += testScore.getTestScore();
        }
        return totalScore / testScores.size(); // forget to divide by number of test scores to get the wrong result
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", testScores=" + testScores +
                '}';
    }
}
