package sample.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Students {
    private SimpleIntegerProperty id;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleIntegerProperty yearEnrolled;
    private SimpleStringProperty email;
    private SimpleStringProperty course1;
    private SimpleStringProperty course2;
    private SimpleStringProperty location;

    public Students(){
        this.id = new SimpleIntegerProperty();
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.yearEnrolled = new SimpleIntegerProperty();
        this.email = new SimpleStringProperty();
        this.course1 = new SimpleStringProperty();
        this.course2 = new SimpleStringProperty();
        this.location = new SimpleStringProperty();
    }

    public int getId() {
        return id.get();
    }

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }

    public int getYearEnrolled() {
        return yearEnrolled.get();
    }

    public SimpleIntegerProperty yearEnrolledProperty() {
        return yearEnrolled;
    }

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public String getCourse1() {
        return course1.get();
    }

    public SimpleStringProperty course1Property() {
        return course1;
    }



    public String getCourse2() {
        return course2.get();
    }

    public String getLocation() {
        return location.get();
    }

    public int idProperty() {
        return id.get();
    }

    public String getfirstName() {
        return firstName.get();
    }

    public String getlastName() {
        return lastName.get();
    }

    public int getyearEnrolled() {
        return yearEnrolled.get();
    }

    public String getemail() {
        return email.get();
    }

    public String getcourse1() {
        return course1.get();
    }

    public String getcourse2() {
        return course2.get();
    }

    public String getlocation() {
        return location.get();
    }


    public StringProperty course2Property() {
        return course2;
    }

    public StringProperty locationProperty() {
        return location;
    }


    public void setId(int id) {
        this.id.set(id);
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public void setYearEnrolled(int yearEnrolled) {
        this.yearEnrolled.set(yearEnrolled);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setCourse1(String course1) {
        this.course1.set(course1);
    }

    public void setCourse2(String course2) {
        this.course2.set(course2);
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public StringProperty firstNameProperty(){
        return firstName;
    }

    public StringProperty lastNameProperty(){
        return lastName;
    }


}
