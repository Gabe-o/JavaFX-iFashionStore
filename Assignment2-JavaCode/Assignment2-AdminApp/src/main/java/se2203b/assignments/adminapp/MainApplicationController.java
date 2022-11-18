package se2203b.assignments.adminapp;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ResourceBundle;

/**
 *
 * @author Abdelkader Ouda
 */
public class MainApplicationController implements Initializable {

    private Connection conn;

    // Database adapters
    private CatalogAdapter catalogs;
    private ProductAdapter products;

    @FXML
    private MenuBar mainMenu;

    public void showAbout() throws Exception {
        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationController.class.getResource("About-view.fxml"));
        // create the root node
        Parent About =  fxmlLoader.load();
        // create new stage
        Stage stage = new Stage();
        // add the about's UI elements to the stage
        stage.setScene(new Scene(About));
        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.setTitle("About Us");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    /**
     * Shows the window for adding a catalog
     * @throws Exception
     *
     * @author Gabriel Olivotto
     */
    public void showAddCatalog() throws Exception {
        // Create models
        catalogs = new CatalogAdapter(conn, false);

        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationController.class.getResource("AddCatalog-view.fxml"));
        // create the root node
        Parent addCatalog = (Parent) fxmlLoader.load();

        AddCatalogController addCatalogController = (AddCatalogController) fxmlLoader.getController();
        addCatalogController.setModel(catalogs);

        // create new stage
        Stage stage = new Stage();
        // add the addCatalog's UI elements to the stage
        stage.setScene(new Scene(addCatalog));

        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.setTitle("Add Catalog");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    /**
     * Shows the window for editing/deleting a catalog
     * @throws Exception
     *
     * @author Gabriel Olivotto
     */
    public void showEditDeleteCatalog() throws Exception {
        // Create models
        catalogs = new CatalogAdapter(conn, false);
        products = new ProductAdapter(conn, false);

        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationController.class.getResource("EditDeleteCatalog-view.fxml"));
        // create the root node
        Parent editDeleteCatalog = (Parent) fxmlLoader.load();
        EditDeleteCatalogController editDeleteCatalogController = (EditDeleteCatalogController) fxmlLoader.getController();
        editDeleteCatalogController.setModel(catalogs, products);

        // create new stage
        Stage stage = new Stage();
        // add the addCatalog's UI elements to the stage
        stage.setScene(new Scene(editDeleteCatalog));

        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.setTitle("Edit/Delete Catalog");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    /**
     * Shows the window for adding a product
     * @throws Exception
     *
     * @author Gabriel Olivotto
     */
    public void showAddProduct() throws Exception {
        // Create models
        catalogs = new CatalogAdapter(conn, false);
        products = new ProductAdapter(conn, false);

        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationController.class.getResource("AddProduct-view.fxml"));
        // create the root node
        Parent addProduct = (Parent) fxmlLoader.load();

        AddProductController addProductController = (AddProductController) fxmlLoader.getController();
        addProductController.setModel(catalogs, products);

        // create new stage
        Stage stage = new Stage();
        // add the addCatalog's UI elements to the stage
        stage.setScene(new Scene(addProduct));

        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.setTitle("Add Product");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    /**
     * Shows the window for editing/deleting product
     * @throws Exception
     *
     * @author Gabriel Olivotto
     */
    public void showEditDeleteProduct() throws Exception {
        // Create models
        catalogs = new CatalogAdapter(conn, false);
        products = new ProductAdapter(conn, false);

        // load the fxml file (the UI elements)
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationController.class.getResource("EditDeleteProduct-view.fxml"));
        // create the root node
        Parent editDeleteProduct = (Parent) fxmlLoader.load();

        EditDeleteProductController editDeleteProductController = (EditDeleteProductController) fxmlLoader.getController();
        editDeleteProductController.setModel(catalogs, products);

        // create new stage
        Stage stage = new Stage();
        // add the addCatalog's UI elements to the stage
        stage.setScene(new Scene(editDeleteProduct));

        // add icon to the About window
        stage.getIcons().add(new Image("file:src/main/resources/se2203b/assignments/adminapp/WesternLogo.png"));
        stage.setTitle("Edit/Delete Product");
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.show();
    }

    @FXML
    /**
     * Resets the databases
     *
     * @author Gabriel Olivotto
     */
    public void resetDB() {
        // recreate catalogs model
        try {
            catalogs = new CatalogAdapter(conn, true);
            displayAlert("Reset Catalog Database");
        } catch (SQLException e) {
            displayAlert("ERROR: " + e.getMessage());
        }

        // recreate products model
        try {
            products = new ProductAdapter(conn, true);
            displayAlert("Reset Product Database");
        } catch (SQLException e) {
            displayAlert("ERROR: " + e.getMessage());
        }
    }

    public void exit() {
        // Get current stage reference
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        // Close stage
        stage.close();
    }

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
    public void initialize(URL url, ResourceBundle rb) {
        // Initializes database
        try {
            // Create a named constant for the URL
            // NOTE: This value is specific for Java DB
            String DB_URL = "jdbc:derby:iFashionStoreDB;create=true";
            // Create a connection to the database
            conn = DriverManager.getConnection(DB_URL);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
