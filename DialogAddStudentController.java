//Class that displays Dialog box to add
//Student
// Author: Maurice C

package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import sample.model.Datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DialogAddStudentController {

    @FXML
    private TextField studentFirstName, studentLastName, yearEnrolled, studentEmail, studentLocation;

    @FXML
    private ComboBox studentCourse1, studentCourse2;

    private Datasource datasource;
    public static final String DB_NAME = "university.db";
    public static final String CONNECTION = "jdbc:sqlite:C:\\Users\\Owner\\Documents\\university.db";

    public static final String TABLE_STUDENTS = "students";
    public static final String COLUMN_STUDENTS_ID = "id";
    public static final String COLUMN_STUDENTS_FIRST_NAME = "firstName";
    public static final String COLUMN_STUDENTS_LAST_NAME = "lastName";
    public static final String COLUMN_STUDENTS_YEAR_ENROLLED = "yearEnrolled";
    public static final String COLUMN_STUDENTS_EMAIL = "email";
    public static final String COLUMN_STUDENTS_COURSE1 = "course1";
    public static final String COLUMN_STUDENTS_COURSE2 = "course2";
    public static final String COLUMN_STUDENTS_LOCATION = "location";
    private Connection conn;


    //Add a student to the database
    public void addStudenToDatabase(){
        try{
            conn = DriverManager.getConnection(CONNECTION);
            Statement statement = conn.createStatement();
            statement.execute("INSERT INTO " + TABLE_STUDENTS + " " +
                    "(" +
                    COLUMN_STUDENTS_FIRST_NAME + ", " +
                    COLUMN_STUDENTS_LAST_NAME + ", " +
                    COLUMN_STUDENTS_YEAR_ENROLLED + ", " +
                    COLUMN_STUDENTS_EMAIL + ", " +
                    COLUMN_STUDENTS_COURSE1 + "," +
                    COLUMN_STUDENTS_COURSE2 + ", " +
                    COLUMN_STUDENTS_LOCATION +") " +
                    "VALUES ('" +
                    studentFirstName.getText() + "', '" +
                    studentLastName.getText() + "', '" +
                    yearEnrolled.getText() + "', '" +
                    studentEmail.getText() + "', '" +
                    studentCourse1.getValue() + "', '" +
                    studentCourse2.getValue() + "', '" +
                    studentLocation.getText() + "');");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}