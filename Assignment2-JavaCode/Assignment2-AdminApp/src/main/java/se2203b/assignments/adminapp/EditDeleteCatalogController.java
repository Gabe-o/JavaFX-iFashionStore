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

/**
 * Manages functionality of the edit/delete catalog window
 *
 * @author Gabriel Olivotto
 */
public class EditDeleteCatalogController implements Initializable {

    // FXML Controls
    @FXML private Button cancelBtn;
    @FXML private ComboBox catalogBox;
    @FXML private TextField nameField;

    // To reference the models inside the controller
    CatalogAdapter catalogAdapter;
    ProductAdapter productAdapter;

    // The data variable is used to populate the ComboBox
    final ObservableList<String> data = FXCollections.observableArrayList();

    /**
     * Sets model
     * @param catalogs
     * @param products
     */
    public void setModel(CatalogAdapter catalogs, ProductAdapter products) {
        catalogAdapter = catalogs;
        productAdapter = products;
        buildComboBoxData();
    }

    /**
     * Gets catalog combobox data from the catalog database
     */
    public void buildComboBoxData() {
        try {
            data.addAll(catalogAdapter.getNamesList());
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }

    /**
     * Trys to delete selected catalog
     */
    public void delete() {
        try {
            if (!productAdapter.isCatalogInUse(catalogBox.getValue().toString())) {
                catalogAdapter.deleteCatalog(catalogBox.getValue().toString());
            }
            else {
                displayAlert("Could not delete catalog because a product uses it");
            }
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
     * Trys to edit the name of a catalog
     */
    public void edit() {
        try {
            catalogAdapter.updateCatalog(catalogBox.getValue().toString(),nameField.getText());
            productAdapter.updateCatalogNames(catalogBox.getValue().toString(),nameField.getText());
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
        catalogBox.setItems(data);
    }
}
