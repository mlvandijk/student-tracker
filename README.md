This application is used in the following content:
* Video: [Debugging Java code in IntelliJ IDEA](https://www.youtube.com/watch?v=V5iQ1FyRtBo)
* Blog post: [Debugging Java code in IntelliJ IDEA](https://blog.jetbrains.com/idea/2025/04/debugging-java-code-in-intellij-idea/)

If you'd like to follow along, please check out the branch `debugger`.


# Student Tracker

A Java application for tracking student test scores. This application allows you to manage student records and their associated test scores, reading data from CSV files and providing a clear summary output.

## Features

- Read student test scores from CSV files
- Validate and store student information
- Track multiple test scores per student
- Calculate and display average test scores per student
- Generate summary reports of student performance
- Input validation for all data fields

## Requirements

- Java 21 or higher
- Maven 3.x

## Building the Project

To build the project, run:

```bash
mvn verify
```

## Running the project

## From IntelliJ IDEA
In class `Main`, click the **Run** button in the gutter next to the class declaration or main method.

### From the command line
To run the application from the command line using Maven, run:
   ```bash
   mvn exec:java -Dexec.mainClass="Main"
   ```

The application will read from `test-scores.csv` in the project root directory and display a summary of all students and their scores.

## Data Validation

The application includes several validation rules:
- Student IDs and names cannot be null or empty
- Test names cannot be null or empty
- Test scores must be between 0 and 10
- Test dates cannot be null
- CSV file must contain at least 5 columns per row

## Project Structure

- `Main.java`: Application entry point and CSV processing
- `Student.java`: Student data model with validation
- `TestScore.java`: Test score data model with validation

## Dependencies

- JUnit Jupiter 5.10.0 (for testing)
- Maven Surefire Plugin 3.1.2 (for running tests)

## Credit
This demo application was built with the help of:
* [JetBrains AI Assistant](https://www.jetbrains.com/ai/)
* [JetBrains Junie](https://www.jetbrains.com/junie/)
