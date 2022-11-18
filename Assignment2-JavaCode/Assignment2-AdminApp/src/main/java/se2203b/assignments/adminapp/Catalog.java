package se2203b.assignments.adminapp;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Holds information about the catalog
 * @author Gabriel Olivotto
 */
public class Catalog {

    // Properties
    private final StringProperty name;

    // Constructor
    public Catalog(String name) {
        this.name = new SimpleStringProperty(name);
    }

    // Getters and Setters
    public void setName(String name) {
        this.name.set(name);
    }
    public StringProperty nameProperty() {
        return name;
    }
    public String getName() {
        return name.get();
    }
}
