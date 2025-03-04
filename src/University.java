import java.util.*;

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

class Course {
    private String name;
    private int capacity;
    private List<String> prerequisites;
    private int enrolledStudents;

    public Course(String name, int capacity, List<String> prerequisites) {
        this.name = name;
        this.capacity = capacity;
        this.prerequisites = prerequisites;
        this.enrolledStudents = 0;
    }

    public String getName() {
        return name;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public void enrollStudent(List<String> completedCourses) throws CourseFullException, PrerequisiteNotMetException {
        if (enrolledStudents >= capacity) {
            throw new CourseFullException("Error: Course is full.");
        }
        for (String prerequisite : prerequisites) {
            if (!completedCourses.contains(prerequisite)) {
                throw new PrerequisiteNotMetException("Error: PrerequisiteNotMetException - Complete " + prerequisite + " before enrolling in " + name + ".");
            }
        }
        enrolledStudents++;
        System.out.println("Successfully enrolled in " + name + ".");
    }
}

public class UniversityEnrollmentSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Course advancedJava = new Course("Advanced Java", 2, Arrays.asList("Core Java"));
        List<String> completedCourses = new ArrayList<>();
        
        try {
            System.out.print("Enroll in Course: ");
            String courseName = scanner.nextLine();
            
            System.out.print("Prerequisite Completed (yes/no): ");
            String prerequisiteStatus = scanner.nextLine();
            
            if (prerequisiteStatus.equalsIgnoreCase("yes")) {
                completedCourses.add("Core Java");
            }
            
            if (courseName.equalsIgnoreCase("Advanced Java")) {
                advancedJava.enrollStudent(completedCourses);
            } else {
                System.out.println("Course not found.");
            }
        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input.");
        } finally {
            scanner.close();
        }
    }
}
