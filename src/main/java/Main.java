import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Path.of("test-scores.csv"));
            Map<String, Student> students = new HashMap<>();

            // Skip header line if it exists
            boolean firstLine = true;
            for (String line : lines) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String studentId = parts[0];
                    String studentName = parts[1];
                    String testName = parts[2];
                    double testScore = Double.parseDouble(parts[3]);

                    // Get or create student
                    Student student = students.computeIfAbsent(studentId,
                        id -> new Student(id, studentName));

                    // Add test score
                    student.addTestScore(new TestScore(testName, testScore));
                }
            }

            // Print all students and their scores
            for (Student student : students.values()) {
                System.out.println("Student: " + student.getStudentName() + " (ID: " + student.getStudentId() + ")");
                for (TestScore score : student.getTestScores()) {
                    System.out.println("  " + score.getTest() + ": " + score.getTestScore());
                }
                System.out.println();
            }

        } catch (java.io.IOException e) {
            System.err.println("Failed to read the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid test score format in CSV: " + e.getMessage());
        }
    }
}
