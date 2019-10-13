//Main controller for program


package sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import sample.model.Datasource;
import sample.model.Faculty;
import sample.model.Students;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    //Class fields
@FXML
private TableView students;

@FXML
private TableView<Faculty> faculty;

@FXML
private BorderPane mainWindowPane;


    //List the Students in the database
    public void listStudents(){
        Task<ObservableList<Students>> task = new GetllAllStudents();
        students.itemsProperty().bind(task.valueProperty());
        new Thread(task).start();
    }

    //List the Faculty members from database
    public void listFaculty(){
        Task<ObservableList<Faculty>> task1 = new GetllAllFaculty();
        faculty.itemsProperty().bind(task1.valueProperty());
        new Thread(task1).start();

    }


    //Open Dialog pane to add faculty member to database
    public void addFaculty(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainWindowPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("dialogAddFaculty.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException e){
            System.out.println("ERROR");
        }

        //Add dialog buttons to dialog pane
        dialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);


        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.APPLY){
            DialogAddFacultyController control = fxmlLoader.getController();
            control.addFaculty();
        }
    }


    //Open dialog pane to add a student to database
    public void addStudentDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainWindowPane.getScene().getWindow());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("dialogAddStudent.fxml"));
        try{
            dialog.getDialogPane().setContent(fxmlLoader.load());

        } catch (IOException e){
            System.out.println("ERROR");
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.APPLY){
            DialogAddStudentController control = fxmlLoader.getController();
            control.addStudenToDatabase();
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //List All students from database on program open
        listStudents();

        //List all faculty from database on program open
        listFaculty();
    }
}

class GetllAllStudents extends Task {
    @Override
    public ObservableList<Students> call(){
        return FXCollections.observableArrayList(Datasource.getInstance().queryStudents(Datasource.ORDER_ASC));
    }
}


class GetllAllFaculty extends Task {
    @Override
    public ObservableList<Faculty> call(){
        return FXCollections.observableArrayList(Datasource.getInstance().queryFaculty(Datasource.ORDER_ASC));
    }
}