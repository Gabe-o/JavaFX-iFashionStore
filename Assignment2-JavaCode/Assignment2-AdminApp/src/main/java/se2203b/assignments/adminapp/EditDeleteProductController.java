package se2203b.assignments.adminapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditDeleteProductController implements Initializable {

    // FXML Controls
    @FXML private Button cancelBtn;
    @FXML private ComboBox selectCatalogBox;
    @FXML private ComboBox selectNameBox;
    @FXML private TextField nameField;
    @FXML private TextField brandField;
    @FXML private TextField priceField;
    @FXML private TextField stockField;
    @FXML private ComboBox catalogBox;

    // To reference the models inside the controller
    CatalogAdapter catalogAdapter;
    ProductAdapter productAdapter;

    // The data variable is used to populate the ComboBox
    final ObservableList<String> catalogData = FXCollections.observableArrayList();
    final ObservableList<String> nameData = FXCollections.observableArrayList();

    /**
     * Sets model
     * @param catalogs
     * @param products
     */
    public void setModel(CatalogAdapter catalogs, ProductAdapter products) {
        catalogAdapter = catalogs;
        productAdapter = products;
        buildCatalogComboBoxData();
    }

    /**
     * Gets catalog combobox data from the catalog database
     */
    public void buildCatalogComboBoxData() {
        try {
            catalogData.addAll(catalogAdapter.getNamesList());
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    /**
     * Clears the fields and sets up the name combobox
     */
    public void updateNameComboBoxData() {
        buildNameComboBoxData();
        selectNameBox.setItems(nameData);
        nameField.clear();
        brandField.clear();
        priceField.clear();
        stockField.clear();
    }

    /**
     * Builds name combo box based off the selected catalog
     */
    public void buildNameComboBoxData() {
        try {
            if(selectCatalogBox.getValue() != null) {
                nameData.setAll(productAdapter.getNamesWithCatalogList(selectCatalogBox.getValue().toString()));
            }
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    /**
     * Sets up edit fields with the data the selected item currently has
     */
    public void setUpEditFields() {
        try {
            if(selectNameBox.getValue() != null) {
                Product product = productAdapter.getProduct(selectNameBox.getValue().toString(), selectCatalogBox.getValue().toString());
                nameField.setText(product.getName());
                brandField.setText(product.getBrand());
                priceField.setText(String.valueOf(product.getPrice()));
                stockField.setText(String.valueOf(product.getStock()));
            }

        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    /**
     * Deletes the selected product
     */
    public void delete() {
        try {
            productAdapter.deleteProduct(selectNameBox.getValue().toString(),selectCatalogBox.getValue().toString());
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        } catch (Exception ex) {
            // Will catch invalid input
            displayAlert("Invalid Input");
        }

        // Close window
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    /**
     * Updates the selected product
     */
    public void edit() {
        try {
            productAdapter.updateProduct(
                    selectNameBox.getValue().toString(),
                    selectCatalogBox.getValue().toString(),
                    nameField.getText(),
                    brandField.getText(),
                    Double.parseDouble(priceField.getText()),
                    Integer.parseInt(stockField.getText()),
                    new Catalog(catalogBox.getValue().toString())
                    );
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        } catch (Exception ex) {
            // Will catch invalid input
            displayAlert("Invalid Input");
        }

        // Close window
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    /**
     * Closes the window
     */
    public void cancel() {
        // Close window
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }

    // Displays alert
    private void displayAlert(String msg) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Alert.fxml"));
            Parent ERROR = loader.load();
            AlertController controller = (AlertController) loader.getController();

            Scene scene = new Scene(ERROR);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
            controller.setAlertText(msg);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException ex1) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectCatalogBox.setItems(catalogData);
        catalogBox.setItems(catalogData);
    }

}
