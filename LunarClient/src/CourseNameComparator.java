/**
 * This class implements javas comparator interface, and compares 2 classes
 *
 * @author Yasin Rhamatzada
 */
import java.util.Comparator;

public class CourseNameComparator implements Comparator {
    /**
     * This method compares the 2 courses
     *
     * @param right the first course
     * @param left the second course
     * @return 1 or -1 depending on result from comparison
     */
    @Override
    public int compare(Object right, Object left) {
        Course leftC = (Course) left, rightC = (Course) right;
        if (leftC.getDepartment().compareTo(rightC.getDepartment()) < 0)
            return 1;
        else if (leftC.getDepartment().compareTo(rightC.getDepartment()) > 0)
            return -1;
        else if (leftC.getDepartment().compareTo(rightC.getDepartment()) == 0) {
            if (leftC.getNumber() < rightC.getNumber())
                return 1;
            else
                return -1;
        }
        return 0;
    }

}
