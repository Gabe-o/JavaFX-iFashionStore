package se2203b.assignments.adminapp;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * Hold information about the shopping cart
 * @author Gabriel Olivotto
 */
public class ShoppingCart {

    // Properties
    private final DoubleProperty itemSubTotal;
    private final DoubleProperty itemTotal;

    // Constructor
    public ShoppingCart(double itemSubTotal, double itemTotal) {
        this.itemSubTotal = new SimpleDoubleProperty(itemSubTotal);
        this.itemTotal = new SimpleDoubleProperty(itemTotal);
    }

    // Getters and Setters
    public void setItemSubTotal(double itemSubTotal) {
        this.itemSubTotal.set(itemSubTotal);
    }
    public DoubleProperty itemSubTotalProperty() {
        return itemSubTotal;
    }
    public double getItemSubTotal() {
        return itemSubTotal.get();
    }

    public void setItemTotal(double itemTotal) {
        this.itemTotal.set(itemTotal);
    }
    public DoubleProperty itemTotalProperty() {
        return itemTotal;
    }
    public double getItemTotal() {
        return itemTotal.get();
    }
}
