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
public class FileController implements Initializable
{
    private static boolean isExport;
    private static Phonebook phonebookF;
    private Stage stage;
    @FXML
    private TextField textField_fileName;
    @FXML
    private Button button_cancel;
    @FXML
    private Button button_next;

    public static void show(Stage stage, boolean export, Phonebook phonebook)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(FileController.class.getResource("/at/nsc/view/fileView.fxml"));
            Parent root = fxmlLoader.load();

            //get controller which is connected to this fxml file
            FileController ctrl = fxmlLoader.getController();
            ctrl.stage = stage;

            stage.setTitle("File Path selector");
            stage.getIcons().add(new Image("/at/nsc/images/icon_export.png"));
            stage.setScene(new Scene(root));
            stage.show();
            isExport = export;
            phonebookF = phonebook;
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
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        button_cancel.setGraphic(new ImageView(new Image("at/nsc/images/icon_delete.png")));
        button_next.setGraphic(new ImageView(new Image("at/nsc/images/icon_next.png")));
    }

    @FXML
    private void action_cancel()
    {
        stage.close();
    }

    @FXML
    private void action_next()
    {
        String fileName = textField_fileName.getText();
        stage.close();

        if (isExport)
            phonebookF.save(fileName);
        else {
            phonebookF.load(fileName);
        }
    }
}
