package SE2203b.Assignments;

import java.net.URL;
import java.util.ResourceBundle;

import com.jpro.webapi.JProApplication;
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


/**
 * @author Abdelkader Ouda
 */
public class MainApplicationController implements Initializable {

    @FXML
    private MenuBar mainMenu;

    @FXML
    BorderPane mainPane;

    protected JProApplication mainApplication;

    public void showAbout() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplicationController.class.getResource("fxml/about-view.fxml"));
        // get the root node of the About screen
        Parent about = fxmlLoader.load();
        // show this node in the main application view
        mainPane.setCenter(about);

        // get reference to the About screen controller
        AboutController controller = fxmlLoader.getController();
        // send the main layout pane and the about's node to the controller
        // to help remove this node when we click close
        controller.init(mainPane, about);


        // Here is some code you can use in the future assignments
        /*
        Stage stage = new Stage();
        stage.setScene(new Scene(about));
        stage.initModality(Modality.APPLICATION_MODAL);
        // open Stage as Popup Window
        mainApplication.getWebAPI().openStageAsPopup(stage);
        // or open Stage as Tab
        mainApplication.getWebAPI().openStageAsTab(stage);
        */

    }

    public void init(JProApplication jProApplication) {
        mainApplication = jProApplication;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
