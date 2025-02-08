# Student Tracker Project Guidelines

## Project Overview
The Student Tracker is a Java application designed for managing and tracking student test scores. It processes student data from CSV files and provides comprehensive performance summaries.

### Key Features
- CSV file processing for student test scores
- Student and test score data validation
- Multiple test score tracking per student
- Average score calculations and performance reporting
- Robust input validation for all data fields

### Technical Stack
- Java 21+
- Maven 3.x
- JUnit Jupiter for testing

## Development Guidelines

### Testing Requirements
1. Always run all tests in project to make sure you didn't introduce regression before submitting the task.
2. Use @ParameterizedTest for tests with multiple examples.

### Data Validation Rules
- Student IDs and names must not be null or empty
- Test names must not be null or empty
- Test scores must be between 0 and 10
- Test dates must not be null
- CSV input must contain at least 5 columns per row
