/**
 * This class implements javas comparator interface to compare 2 different semesters
 *
 * @author Yasin Rhamatzada
 */
import java.util.Comparator;

public class SemesterComparator implements Comparator {
    /**
     * This method compares 2 semesters
     *
     * @param right the first semmester
     * @param left the second semester
     * @return 1 or -1 depending on result of comparison
     */
    @Override
    public int compare(Object right, Object left) {
        Course leftC = (Course) left, rightC = (Course) right;
        if (leftC.getSemester().compareTo(rightC.getSemester()) < 0)
            return 1;
        else if (leftC.getSemester().compareTo(rightC.getSemester()) > 0)
            return -1;
        return 0;
    }

}
