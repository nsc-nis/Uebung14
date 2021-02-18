package at.nsc.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**Übung 14 - Controller
 * @author Niklas Schachl
 * @version 1.0, 18.2.2021
 */
public class NewAccController
{
    private Stage stage;
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
            FXMLLoader fxmlLoader = new FXMLLoader(NewAccController.class.getResource("/at/nsc/view/newAccView.fxml"));
            Parent root = fxmlLoader.load();

            //get controller which is connected to this fxml file
            NewAccController ctrl = fxmlLoader.getController();
            ctrl.stage = stage;

            stage.setTitle("Account creation wizard");
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

    @FXML
    private void action_cancel()
    {

    }

    @FXML
    private void action_save()
    {

    }
}
