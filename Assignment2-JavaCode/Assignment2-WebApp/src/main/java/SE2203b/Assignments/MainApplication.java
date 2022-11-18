package SE2203b.Assignments;

import com.jpro.webapi.JProApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
/**
 *
 * @author Abdelkader Ouda
 */
public class MainApplication extends JProApplication
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        //load user interface as FXML file
        FXMLLoader loader = new FXMLLoader(MainApplication.class.getResource("fxml/Main-view.fxml"));
        Parent root = loader.load();
        // get reference to the main controller
        MainApplicationController controller = loader.getController();
        // send the main application object to its controller
        // to be used later to display pop windows (not used in assignment 2)
        controller.init(this);
        //create JavaFX scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        //open JavaFX window
        stage.show();
    }
}
