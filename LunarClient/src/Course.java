/**
 * This class represents teh course object
 *
 * @author Yasin Rhamatzada
 */
import java.io.*;

public class Course implements Serializable {
    private String department;
    private int number;
    private String semester;

    /**
     * This method is the default constructor, which creates a couerse object
     *
     * @param department the department (CSE)
     * @param number the course number (214)
     * @param semester the semester (S2021)
     */
    public Course(String department, int number, String semester) {
        this.department = department;
        this.number = number;
        this.semester = semester;
    }

    /**
     * This method is a getter for the department member variable
     *
     * @return the department for a course
     */
    public String getDepartment() {
        return department;
    }

    /**
     * This method is the setter for department of the course
     *
     * @param department the department of a course
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * This method is the getter for the numbers member variable
     *
     * @return the number for the course
     */
    public int getNumber() {
        return number;
    }

    /**
     * This method is the setter for the member variable
     *
     * @param number the numnber for the course
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * This method is the getter for the semester
     *
     * @return the semester for the course
     */
    public String getSemester() {
        return semester;
    }

    /**
     * The setter for the semester member variable
     *
     * @param semester the semester member variable
     */
    public void setSemester(String semester) {
        this.semester = semester;
    }
}
