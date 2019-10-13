//Class that displays Dialog box to add
//Faculty member
// Author: Maurice C


package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DialogAddFacultyController {



    public static final String TABLE_FACULTY = "faculty";
    public static final String COLUMN_FACULTY_ID = "id";
    public static final String COLUMN_FACULTY_FIRST_NAME = "firstName";
    public static final String COLUMN_FACULTY_LAST_NAME = "lastName";
    public static final String COLUMN_FACULTY_COURSE_TEACHING = "courseTeaching";
    public static final String CONNECTION = "jdbc:sqlite:C:\\Users\\Owner\\Documents\\university.db";


    private Connection conn;
    @FXML
    private TextField facultyFirstName, facultyLastName;

    @FXML
    private ComboBox courseTeaching;



    //Add faculty member to database
    public void addFaculty(){
        try{
            conn = DriverManager.getConnection(CONNECTION);
            Statement statement = conn.createStatement();
            statement.execute("INSERT INTO " + TABLE_FACULTY + " " +
                    "(" +
                    COLUMN_FACULTY_FIRST_NAME + ", " +
                    COLUMN_FACULTY_LAST_NAME + ", " +
                    COLUMN_FACULTY_COURSE_TEACHING +") " +
                    "VALUES ('" +
                    facultyFirstName.getText() + "', '" +
                    facultyLastName.getText() + "', '" +
                    courseTeaching.getValue() + "');");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public boolean validateText(String text){

        if(text.isBlank()){
            return false;
        } else {
            return true;
        }
    }
    }