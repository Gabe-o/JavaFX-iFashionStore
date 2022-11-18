package se2203b.assignments.adminapp;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

/**
 * Holds information about a comment
 * @author Gabriel Olivotto
 */
public class Comment {

    // Properties
    private final StringProperty description;
    private final StringProperty date;

    // Constructor
    public Comment(String description, Date date) {
        this.description = new SimpleStringProperty(description);
        this.date = new SimpleStringProperty(date.toString());
    }

    // Getters and Setters
    public void setDescription(String description) {
        this.description.set(description);
    }
    public StringProperty descriptionProperty() {
        return description;
    }
    public String getDescription() {
        return description.get();
    }

    public void setDate(Date date) {
        this.date.set(date.toString());
    }
    public StringProperty dateProperty() {
        return date;
    }
    public String getDate() {
        return date.get();
    }
}
