package at.nsc.controller;

import at.nsc.model.Phonebook;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**Übung 14 - Controller
 * @author Niklas Schachl
 * @version 1.1, 6.3.2021
 */
public class controller_ExitWindow
{
    private Stage stage;
    private static Phonebook phonebook;

    public static void show(Stage stage, Phonebook pb)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(controller_ExitWindow.class.getResource("/at/nsc/view/view_ExitWindow.fxml"));
            Parent root = fxmlLoader.load();

            //get controller which is connected to this fxml file
            controller_ExitWindow ctrl = fxmlLoader.getController();
            ctrl.stage = stage;

            stage.setTitle("Unsaved Changes");
            stage.getIcons().add(new Image("/at/nsc/images/icon_save.png"));
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
    private void action_dontSave()
    {
        stage.close();
    }

    @FXML
    private void action_save()
    {
        phonebook.save();
        stage.close();
    }
}
