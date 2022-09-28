/**
 * This class represents a student object
 *
 * @author Yasin Rhamatzada
 */
import java.io.*;
import java.util.*;

public class Student implements Serializable {
    private String webID;
    private ArrayList<Course> courses = new ArrayList();

    /**
     * This method is the default constructor
     *
     * @param webID the webid of the new student object
     */
    public Student(String webID) {
        this.webID = webID;
    }

    /**
     * This method is the getter for the webID member variable
     *
     * @return the webID of a student
     */
    public String getWebID() {
        return webID;
    }

    /**
     * This method is the setter for the webID
     *the webID of the student
     * @param webID the webid of the student
     */
    public void setWebID(String webID) {
        this.webID = webID;
    }

    /**
     * The getter for the courses list of a student
     *
     * @return the students course list
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * This method is the setter for the course list of a student
     *
     * @param courses the list of courses a student is taking
     */
    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    /**
     * This method is the setter for an individual course, rather than the whole arraylist
     *
     * @param course the course to add
     */
    public void setCourse(Course course) {
        courses.add(course);
    }

    /**
     * This method is the getter for an individual  course
     *
     * @param index the index of the course to get
     * @return the course at the given index
     */
    public Course getCourse(int index) {
        return courses.get(index);
    }

    /**
     * This method removes a course from the course arraylist
     *
     * @param index the index of the course to remove
     */
    public void removeCourse(int index) {
        courses.remove(index);
    }

    /**
     * This method prints out the courses sorted by course name/department
     */
    public void courseNameString() {
        Comparator<Course> comp = new CourseNameComparator();
        courses.sort(comp);

        System.out.println("Dept. Course Semester");
        System.out.println("---------------------");

        for (Course cours : courses)
            System.out.printf("%s %5d %8s\n", cours.getDepartment(), cours.getNumber(), cours.getSemester());
    }

    /**
     * this method prints the courses sorted by semester
     */
    public void semesterString() {
        Comparator<Course> comp = new SemesterComparator();
        courses.sort(comp);

        System.out.println("Dept. Course Semester");
        System.out.println("---------------------");

        for (Course cours : courses)
            System.out.printf("%s %5d %8s\n", cours.getDepartment(), cours.getNumber(), cours.getSemester());
    }

}
