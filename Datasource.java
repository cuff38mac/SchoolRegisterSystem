//Class that connects app to database and SQL statements
// Author: Maurice C

package sample.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {

    //Class fields

    public static final String DB_NAME = "university.db";
    public static final String CONNECTION = "jdbc:sqlite:C:\\Users\\Owner\\IdeaProjects\\SchoolRegisterSystem\\src\\university.db";


    //Student Table, Columns

    public static final String TABLE_STUDENTS = "students";
    public static final String COLUMN_STUDENTS_ID = "id";
    public static final String COLUMN_STUDENTS_FIRST_NAME = "firstName";
    public static final String COLUMN_STUDENTS_LAST_NAME = "lastName";
    public static final String COLUMN_STUDENTS_YEAR_ENROLLED = "yearEnrolled";
    public static final String COLUMN_STUDENTS_EMAIL = "email";
    public static final String COLUMN_STUDENTS_COURSE1 = "course1";
    public static final String COLUMN_STUDENTS_COURSE2 = "course2";
    public static final String COLUMN_STUDENTS_LOCATION = "location";
    public static final int INDEX_STUDENT_ID = 1;
    public static final int INDEX_STUDENT_FIRSTNAME= 2;
    public static final int INDEX_STUDENT_LASTNAME = 3;
    public static final int INDEX_STUDENT_YEAR_ENROLLED = 4;
    public static final int INDEX_STUDENT_EMAIL = 5;
    public static final int INDEX_STUDENT_COURSE1 = 6;
    public static final int INDEX_STUDENT_COURSE2 = 7;
    public static final int INDEX_STUDENT_LOCATION = 8;


    //Statement to create table
    // CREATE TABLE "faculty" (
    //	"id"	INTEGER PRIMARY KEY AUTOINCREMENT,
    //	"firstName"	TEXT NOT NULL,
    //	"lastName"	TEXT NOT NULL,
    //	"courseTeaching"	TEXT NOT NULL
    //)


    //Statment to create students table
    //CREATE TABLE "students" ( "id" INTEGER PRIMARY KEY AUTOINCREMENT,
    // "firstName" TEXT NOT NULL, "lastName" TEXT NOT NULL, "yearEnrolled" INTEGER NOT NULL,
    // "email" TEXT NOT NULL, "course1" TEXT NOT NULL, "course2" TEXT NOT NULL,
    // "location" INTEGER NOT NULL )

    ////Faculty Table, Columns
    public static final String TABLE_FACULTY = "faculty";
    public static final String COLUMN_FACULTY_ID = "id";
    public static final String COLUMN_FACULTY_FIRST_NAME = "firstName";
    public static final String COLUMN_FACULTY_LAST_NAME = "lastName";
    public static final String COLUMN_FACULTY_COURSE_TEACHING = "courseTeaching";
    public static final int INDEX_ID = 1;
    public static final int INDEX_FACULTY_FIRST_NAME = 2;
    public static final int INDEX_FACULTY_LAST_NAME = 3;
    public static final int INDEX_FACULTY_COURSE_TEACHING = 4;

    public static final int ORDER_NONE = 1;
    public static final int ORDER_ASC = 2;
    public static final int ORDER_DES = 3;


    //Statement to select all records from Student Table
    public static final String queryStudentsByCourseName =
            "SELECT " + TABLE_STUDENTS + "." + COLUMN_STUDENTS_LAST_NAME
        + ", " + TABLE_STUDENTS + "." + COLUMN_STUDENTS_FIRST_NAME + " FROM " +
                    TABLE_STUDENTS + " INNER JOIN " + TABLE_FACULTY + " ON " +
                    TABLE_STUDENTS + "." + COLUMN_STUDENTS_COURSE1 + " = " +
                    TABLE_FACULTY + "." + COLUMN_FACULTY_COURSE_TEACHING + " OR " +
                    TABLE_STUDENTS + "." + COLUMN_STUDENTS_COURSE2 + " = " +
                    TABLE_FACULTY + "." + COLUMN_FACULTY_COURSE_TEACHING + " WHERE " +
                    TABLE_FACULTY + "." + COLUMN_FACULTY_COURSE_TEACHING + " = \'";

    public static final String orderByStudentLastNameSort =
            " ORDER BY " + TABLE_STUDENTS + "." + COLUMN_STUDENTS_LAST_NAME +
                    " COLLATE NOCASE ";




    private static Datasource instance = new Datasource();

    public static Datasource getInstance(){
        return instance;
    }


private Datasource(){

}
    private static Connection connection;
    public Statement statement;


    public boolean open() {
        try {
            connection = DriverManager.getConnection(CONNECTION);
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }

    }


    public boolean close(){
        try {
            if (connection != null){
                connection.close();
            }
            return true;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }


    //Returns list of all students in database
    public List<Students> queryStudents(int sortOrder){
        StringBuilder sp = new StringBuilder("SELECT * FROM ");
        sp.append(TABLE_STUDENTS);
        if(sortOrder != ORDER_NONE){
            sp.append(" ORDER BY ");
            sp.append(COLUMN_STUDENTS_LAST_NAME);
            sp.append(" COLLATE NOCASE ");
            if(sortOrder == ORDER_DES){
                sp.append("DESC");
            }else {
                sp.append("ASC");
            }
        }


        try(Statement statment1 = connection.createStatement();
            ResultSet results = statment1.executeQuery(sp.toString())){

            List<Students> students = new ArrayList<>();

            while(results.next()){
                Students student = new Students();
               student.setId(results.getInt(INDEX_STUDENT_ID));
                student.setFirstName(results.getString(INDEX_STUDENT_FIRSTNAME));
               student.setLastName(results.getString(INDEX_STUDENT_LASTNAME));
                student.setId(results.getInt(COLUMN_STUDENTS_YEAR_ENROLLED));
                student.setEmail(results.getString(COLUMN_STUDENTS_EMAIL));
                student.setCourse1(results.getString(COLUMN_STUDENTS_COURSE1));
                student.setCourse2(results.getString(COLUMN_STUDENTS_COURSE2));
                student.setLocation(results.getString(COLUMN_STUDENTS_LOCATION));
                students.add(student);
            }

            return students;

        } catch (SQLException e){
            //e.printStackTrace();
            e.getMessage();
            return null;
        }


    }



    //Returns list of all faculty members in database
    public List<Faculty> queryFaculty(int sortOrder){
        StringBuilder sp = new StringBuilder("SELECT * FROM ");
        sp.append(TABLE_FACULTY);
        if(sortOrder != ORDER_NONE){
            sp.append(" ORDER BY ");
            sp.append(COLUMN_FACULTY_LAST_NAME);
            sp.append(" COLLATE NOCASE ");
            if(sortOrder == ORDER_DES){
                sp.append("DESC");
            }else {
                sp.append("ASC");
            }
        }


            try(Statement statment1 = connection.createStatement();
                ResultSet results = statment1.executeQuery("SELECT * FROM " + TABLE_FACULTY)
            ){

                List<Faculty> faculty = new ArrayList<>();

                while(results.next()){
                    Faculty facultyMember = new Faculty();
                    facultyMember.setId(results.getInt(COLUMN_FACULTY_ID));
                    facultyMember.setFirstName(results.getString(COLUMN_FACULTY_FIRST_NAME));
                    facultyMember.setLastName(results.getString(COLUMN_FACULTY_LAST_NAME));
                    facultyMember.setCourseTeaching(results.getString(COLUMN_FACULTY_COURSE_TEACHING));
                    faculty.add(facultyMember);
                }

                return faculty;

            } catch (SQLException e){
                //e.printStackTrace();
                e.getMessage();
                return null;
            }
    }



//Returns list of students for a given course
    public List<String> queryCoursesforTeacher(String course, int sortOrder){
        StringBuilder sp = new StringBuilder(queryStudentsByCourseName);
        sp.append(course);
        sp.append("\'");
        if(sortOrder != ORDER_NONE){
            sp.append(orderByStudentLastNameSort);
            if(sortOrder == ORDER_DES){
                sp.append("DESC");
            }else {
                sp.append("ASC");
            }
        }
        System.out.println(" SQL STATEMENT = " + sp.toString());
        List<String> students = new ArrayList<>();
        try(Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(sp.toString())){
            while(results.next()){
                students.add((results.getString(1)));
                students.add((results.getString(2)));
            }
        } catch (SQLException e){
            e.getMessage();
        }
        return students;
    }
}
