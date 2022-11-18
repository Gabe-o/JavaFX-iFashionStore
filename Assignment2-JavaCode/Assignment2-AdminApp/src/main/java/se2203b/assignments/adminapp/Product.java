package se2203b.assignments.adminapp;

import javafx.beans.property.*;


/**
 * Holds information about a product
 * @author Gabriel Olivotto
 */
public class Product {

    // Properties
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty brand;
    private final DoubleProperty price;
    private final IntegerProperty stock;
    private final ObjectProperty<Catalog> catalog;


    // Constructor
    public Product(int id, String name, String brand, double price, int stock, Catalog catalog) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.brand = new SimpleStringProperty(brand);
        this.price = new SimpleDoubleProperty(price);
        this.stock = new SimpleIntegerProperty(stock);
        this.catalog = new SimpleObjectProperty<Catalog>(catalog);
    }

    // Getters and Setters
    public void setId(int id) {
        this.id.set(id);
    }
    public IntegerProperty idProperty() {
        return id;
    }
    public int getId() {
        return id.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }
    public StringProperty nameProperty() {
        return name;
    }
    public String getName() {
        return name.get();
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
    }
    public StringProperty brandProperty() {
        return brand;
    }
    public String getBrand() {
        return brand.get();
    }

    public void setPrice(Double price) {
        this.price.set(price);
    }
    public DoubleProperty priceProperty() {
        return price;
    }
    public double getPrice() {
        return price.get();
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }
    public IntegerProperty stockProperty() {
        return stock;
    }
    public int getStock() {
        return stock.get();
    }

    public void setCatalog(Catalog catalog) {
        this.catalog.set(catalog);
    }
    public ObjectProperty<Catalog> catalogProperty() {
        return catalog;
    }
    public Catalog getCatalog() {
        return catalog.get();
    }
}
