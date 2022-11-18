package se2203b.assignments.adminapp;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Holds information about the customers account
 * @author Gabriel Olivotto
 */
public class CustomerAccount {

    // Properties
    private final StringProperty userName;
    private final StringProperty passWord;
    private final StringProperty email;

    // Constructor
    public CustomerAccount(String userName, String passWord, String email) {
        this.userName = new SimpleStringProperty(userName);
        this.passWord = new SimpleStringProperty(passWord);
        this.email = new SimpleStringProperty(email);
    }

    // Getters and Setters
    public void setUserName(String userName) {
        this.userName.set(userName);
    }
    public StringProperty userNameProperty() {
        return userName;
    }
    public String getUserName() {
        return userName.get();
    }

    public void setPassWord(String passWord) {
        this.passWord.set(passWord);
    }
    public StringProperty passWordProperty() {
        return passWord;
    }
    public String getPassWord() {
        return passWord.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
    public StringProperty emailProperty() {
        return email;
    }
    public String getEmail() {
        return email.get();
    }
}
