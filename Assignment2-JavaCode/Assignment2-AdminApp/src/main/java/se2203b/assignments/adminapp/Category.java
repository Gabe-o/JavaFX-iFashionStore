package se2203b.assignments.adminapp;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Holds information about a category
 * @author Gabriel Olivotto
 */
public class Category {

    // Properties
    private final StringProperty name;
    private final ObjectProperty<Department> department;

    // Constructor
    public Category(String name, Department department) {
        this.name = new SimpleStringProperty(name);
        this.department = new SimpleObjectProperty<Department>(department);
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

    public void setDepartment(Department department) {
        this.department.set(department);
    }
    public ObjectProperty<Department> departmentProperty() {
        return department;
    }
    public Department getDepartment() {
        return department.get();
    }
}
