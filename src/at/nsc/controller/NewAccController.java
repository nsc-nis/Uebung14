package at.nsc.controller;

import at.nsc.model.Phonebook;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

/**Übung 14 - Controller
 * @author Niklas Schachl
 * @version 1.0, 20.2.2021
 */
public class NewAccController implements Initializable
{
    private Stage stage;
    private static Phonebook phonebook;
    @FXML
    private TextField textField_name;
    @FXML
    private TextField textField_address;
    @FXML
    private TextField textField_phone;
    @FXML
    private Button button_cancel;
    @FXML
    private Button button_save;

    public static void show(Stage stage, Phonebook pb)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(NewAccController.class.getResource("/at/nsc/view/newAccView.fxml"));
            Parent root = fxmlLoader.load();

            //get controller which is connected to this fxml file
            NewAccController ctrl = fxmlLoader.getController();
            ctrl.stage = stage;

            stage.setTitle("Account Creation Wizard");
            stage.getIcons().add(new Image("/at/nsc/images/icon_add.png"));
            stage.setScene(new Scene(root));
            stage.show();
            phonebook = pb;
        }
        catch (Exception exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Internal Error");
            alert.setResizable(true);
            alert.setContentText(String.format("An internal Error occurred. Please restart the program%nor contact the developer on GitHub%n%nError message: %s", exception.getMessage()));
            alert.showAndWait();
            System.err.println(exception.getMessage());
            exception.printStackTrace(System.err);
        }
    }

    @FXML
    private void action_cancel()
    {
        stage.close();
    }

    @FXML
    private void action_save()
    {
        phonebook.addPerson(textField_name.getText(), textField_address.getText(), textField_phone.getText());
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        button_cancel.setGraphic(new ImageView(new Image("at/nsc/images/icon_delete.png")));
        button_save.setGraphic(new ImageView(new Image("/at/nsc/images/icon_save.png")));
    }
}
