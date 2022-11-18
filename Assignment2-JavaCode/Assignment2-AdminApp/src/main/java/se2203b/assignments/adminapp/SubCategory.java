package se2203b.assignments.adminapp;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Holds information about a sub-category
 * @author Gabriel Olivotto
 */
public class SubCategory {

    // Properties
    private final StringProperty name;
    private final ObjectProperty<Category> category;

    // Constructor
    public SubCategory(String name, Category category) {
        this.name = new SimpleStringProperty(name);
        this.category = new SimpleObjectProperty<Category>(category);
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

    public void setCategory(Category category) {
        this.category.set(category);
    }
    public ObjectProperty<Category> categoryProperty() {
        return category;
    }
    public Category getCategory() {
        return category.get();
    }
}
