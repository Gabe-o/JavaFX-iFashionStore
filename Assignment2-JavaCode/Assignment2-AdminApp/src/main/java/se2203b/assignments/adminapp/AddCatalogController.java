package se2203b.assignments.adminapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Manages functionality of the add catalog window
 *
 * @author Gabriel Olivotto
 */
public class AddCatalogController {

    // FXML Controls
    @FXML private Button cancelBtn;
    @FXML private TextField nameField;

    // To reference the models inside the controller
    CatalogAdapter catalogAdapter;

    /**
     * Sets model
     * @param catalogs adapter
     */
    public void setModel(CatalogAdapter catalogs) {
        catalogAdapter = catalogs;
    }

    /**
     * Adds the catalog to the database
     */
    public void save() {
        try {
            catalogAdapter.insertCatalog(nameField.getText());
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        } catch (Exception ex) {
            // Will catch when input is null
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
}
