package at.nsc.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**Übung 14 - Controller
 * @author Niklas Schachl
 * @version 1.0, 18.2.2021
 */
public class MainController implements Initializable
{
    private Stage stage;

    @FXML
    private Label label_indicator;
    @FXML
    private TextField textField_name;
    @FXML
    private TextField textField_address;
    @FXML
    private TextField textField_phone;

    public static void show(Stage stage)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/at/nsc/view/mainView.fxml"));
            Parent root = fxmlLoader.load();

            //get controller which is connected to this fxml file
            MainController ctrl = fxmlLoader.getController();
            ctrl.stage = stage;

            stage.setTitle("People");
            //stage.getIcons().add(new Image("/at/nsc/images/icon_logo.png"));
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Internal Error");
            alert.setContentText(String.format("An internal Error occurred. Please restart the program%nor contact the developer on GitHub%n%nError message: %s", exception.getMessage()));
            alert.showAndWait();
            System.err.println(exception.getMessage());
            exception.printStackTrace(System.err);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void action_back()
    {

    }

    @FXML
    private void action_add()
    {

    }

    @FXML
    private void action_export()
    {

    }

    @FXML
    private void action_delete()
    {

    }

    @FXML
    private void action_next()
    {

    }

    @FXML
    private void action_save()
    {

    }

    @FXML
    private void action_import()
    {

    }
}
