/**
 * This class is the driver class for the project. It will display the menu and interpret user input
 *
 * @author Yasin Rhamatzada
 */

import java.io.*;
import java.util.*;
import java.lang.*;

public class LunarSystem implements Serializable {
    private static HashMap<String, Student> database = new HashMap<String, Student>();

    /**
     * This method prints the first menu
     */
    public static void printMenu() {
        System.out.println("""
                                
                Menu:
                    L)Login
                    X)Save state and quit
                    Q)Quit without saving state.
                """);
    }

    /**
     * This method prints the menu for the registrar
     */
    public static void printRegistrarOptions() {
        System.out.println("""
                                
                Options:
                     R) Register a student
                     D) De-register a student
                     E) View course enrollment
                     L) Logout
                """);
    }

    /**
     * This method prints the menu for the student
     */
    public static void printStudentOptions() {
        System.out.println("""
                                
                Options:
                    A) Add a class
                    D) Drop a class
                    C) View your classes sorted by course name/department
                    S) View your courses sorted by semester
                    L) Logout
                """);
    }

    /**
     * This method is the main, which is the driver for the UI.
     * The program is looped in a while which contains a main switch, and 2 more switches.
     *
     * @param args a string array
     * @throws IOException exception for serialization
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the Lunar System, a second place course registration " +
                "\nsystem for second rate courses at a second class school." +
                "\n(hint: administrators should input REGISTRAR for their WebID when loging in.");

        Scanner inp = new Scanner(System.in);
        char optMenu, regMenu, stuMenu;
        boolean endOpt = false, endReg, endStu, courseFound = false;
        String webID, courseName, semester = "";
        String[] strArr;

        while (!endOpt) {
            try {
                try {
                    FileInputStream fis = new FileInputStream("databaseStorage.ser");
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    database = (HashMap<String, Student>) ois.readObject();
                    ois.close();
                } catch (FileNotFoundException e) {
                    System.out.println("\nNo previous data found.");
                } catch (ClassNotFoundException e) {
                    System.out.println("\nClassNotFoundException detected.");
                } catch (IOException e) {
                    System.out.println("\nError loading file.");
                }

                printMenu();
                System.out.print("Please select an option: ");
                optMenu = inp.next().charAt(0);
                switch (Character.toUpperCase(optMenu)) {
                    case 'L' -> {
                        inp.nextLine();
                        System.out.print("Enter WebID(CASE SENSITIVE): ");
                        webID = inp.nextLine();
                        if (webID.equals("REGISTRAR")) {
                            System.out.println("Welcome Registrar");
                            endReg = false;

                            while (!endReg) {
                                printRegistrarOptions();
                                System.out.print("Please select an option: ");
                                regMenu = inp.next().charAt(0);

                                switch (Character.toUpperCase(regMenu)) {
                                    case 'R' -> {//register a student
                                        inp.nextLine();
                                        System.out.print("Please enter a webID for the new student: ");
                                        webID = inp.nextLine();
                                        Student newStudent = new Student(webID);
                                        if (!database.containsKey(webID))
                                            database.put(webID, newStudent);
                                        else
                                            System.out.println("Student already exists.");

                                    }
                                    case 'D' -> {//deregister (remove) a student
                                        inp.nextLine();
                                        System.out.print("Please enter a webID for the student to be de-registered: ");
                                        webID = inp.nextLine();
                                        //students.remove(database.)
                                        if (database.containsKey(webID))
                                            database.remove(webID);
                                        else
                                            System.out.println("Student " + webID + " does not exist.");
                                        System.out.println(webID + " de-registered.");
                                    }
                                    case 'E' -> {//view course enrollment
                                        inp.nextLine();
                                        System.out.print("Please enter a course: ");
                                        courseName = inp.nextLine();
                                        strArr = courseName.split(" ");
                                        System.out.println("\nStudents Registered in " + courseName + ": ");
                                        System.out.println("Student    Semester");
                                        System.out.println("-------------------");

                                        List<Student> students = new ArrayList<Student>(database.values());

                                        for (Student st : students)
                                            for (int i = 0; i < st.getCourses().size(); i++)
                                                if (st.getCourse(i).getDepartment().equals(strArr[0]) && st.getCourse(i).getNumber() == Integer.parseInt(strArr[1]))
                                                    System.out.printf("%s %11s\n", st.getWebID(), st.getCourse(i).getSemester());
                                        System.out.println();
                                    }

                                    case 'L' -> {
                                        inp.nextLine();
                                        System.out.println("Registrar logged out.");
                                        endReg = true;
                                    }
                                    default -> System.out.println("Invalid input, try again.");
                                }//end switch
                            }//end while
                        } else if (database.containsKey(webID)) {
                            System.out.println("Welcome " + webID);
                            endStu = false;
                            while (!endStu) {
                                printStudentOptions();
                                System.out.print("Please select an option: ");
                                stuMenu = inp.next().charAt(0);
                                switch (Character.toUpperCase(stuMenu)) {
                                    case 'A' -> {//add class
                                        inp.nextLine();
                                        System.out.print("Please enter a course name(ex: AMS 100): ");
                                        courseName = inp.nextLine();
                                        System.out.print("Please select a semester(ex: F2020): ");
                                        semester = inp.nextLine();
                                        strArr = courseName.split(" ");
                                        Course newCourse = new Course(strArr[0], Integer.parseInt(strArr[1]), semester);
                                        database.get(webID).setCourse(newCourse);
                                        System.out.println(courseName + " added in " + semester + ".");
                                    }
                                    case 'D' -> {//drop class
                                        inp.nextLine();
                                        System.out.print("Please enter course name(ex: AMS 100): ");
                                        courseName = inp.nextLine();
                                        strArr = courseName.split(" ");
                                        for (int i = 0; i < database.get(webID).getCourses().size(); i++)
                                            if (database.get(webID).getCourse(i).getDepartment().equals(strArr[0])
                                                    && database.get(webID).getCourse(i).getNumber() == Integer.parseInt(strArr[1])) {
                                                semester = database.get(webID).getCourse(i).getSemester();
                                                database.get(webID).removeCourse(i);
                                                break;
                                            }
                                        if (semester.charAt(0) == 'F') {
                                            strArr = semester.split("F");
                                            System.out.println(courseName + " dropped from Fall " + strArr[1]);
                                        } else if (semester.charAt(0) == 'S') {
                                            strArr = semester.split("S");
                                            System.out.println(courseName + " dropped from Spring " + strArr[1]);
                                        }
                                    }
                                    case 'C' ->//view classes sorted by course name/department
                                            database.get(webID).courseNameString();
                                    case 'S' ->//view courses sorted by semester
                                            database.get(webID).semesterString();
                                    case 'L' -> {
                                        inp.nextLine();
                                        System.out.println("logged out.");
                                        endStu = true;
                                    }
                                    default -> System.out.println("Invalid input, try again.");
                                }//end student switch
                            }//end student while
                        } else
                            System.out.println("Student " + webID + " does not exist.");
                    }
                    case 'X' -> {//save state and
                        FileOutputStream fos = new FileOutputStream("databaseStorage.ser");
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(database);
                        oos.close();
                        System.out.println("System state saved, system shut down for maintenance.");
                        endOpt = true;
                    }
                    case 'Q' -> {
                        System.out.println("Good bye, please pick the right SUNY next time!");
                        File f = new File("databaseStorage.ser");
                        f.delete();
                        endOpt = true;
                    }
                    default -> System.out.println("Invalid input, try again");
                }//end switch
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }//close while
        inp.close();
    }//close main
}
