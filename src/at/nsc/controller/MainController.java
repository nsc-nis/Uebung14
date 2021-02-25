package at.nsc.controller;

import at.nsc.model.Phonebook;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**Übung 14 - Controller
 * @author Niklas Schachl
 * @version 1.0, 25.2.2021
 */
public class MainController implements Initializable
{
    private Phonebook phonebook = new Phonebook();
    private int currentIndex = 0;
    private final List<String> indicator = new ArrayList<>();
    public static MainController ctrl;

    @FXML
    private Label label_indicator;
    @FXML
    private TextField textField_name;
    @FXML
    private TextField textField_address;
    @FXML
    private TextField textField_phone;
    @FXML
    private Button button_back;
    @FXML
    private Button button_next;
    @FXML
    private Button button_add;
    @FXML
    private Button button_delete;
    @FXML
    private Button button_save;
    @FXML
    private Button button_export;
    @FXML
    private Button button_import;

    public static void show(Stage stage)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/at/nsc/view/mainView.fxml"));
            Parent root = fxmlLoader.load();

            //get controller which is connected to this fxml file
            ctrl = fxmlLoader.getController();

            stage.setTitle("People");
            stage.getIcons().add(new Image("/at/nsc/images/icon_people.png"));
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
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        button_back.setGraphic(new ImageView(new Image("at/nsc/images/icon_back.png")));
        button_next.setGraphic(new ImageView(new Image("at/nsc/images/icon_next.png")));
        button_add.setGraphic(new ImageView(new Image("at/nsc/images/icon_add.png")));
        button_delete.setGraphic(new ImageView(new Image("at/nsc/images/icon_delete.png")));
        button_save.setGraphic(new ImageView(new Image("/at/nsc/images/icon_save.png")));
        button_export.setGraphic(new ImageView(new Image("at/nsc/images/icon_export.png")));
        button_import.setGraphic(new ImageView(new Image("at/nsc/images/icon_import.png")));

        phonebook.addPerson("Max Mustermann", "Musterweg 1", "+43 664 656565");
        displayPerson(currentIndex);
    }

    @FXML
    private void action_back()
    {
        try
        {
            displayPerson(currentIndex - 1);
            currentIndex--;
            //label_indicator.setText(build_indicator());

        }
        catch (IndexOutOfBoundsException exception)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No more contacts");
            alert.setContentText("No more contacts!");
            alert.showAndWait();
        }
    }

    @FXML
    private void action_add()
    {
        NewAccController.show(new Stage(), phonebook, ctrl);
    }

    @FXML
    private void action_export()
    {
        FileController.show(new Stage(), true, phonebook);
    }

    @FXML
    private void action_delete()
    {
        phonebook.deletePerson(currentIndex);
        currentIndex--;
        displayPerson(currentIndex);
        //label_indicator.setText(build_indicator());
    }

    @FXML
    private void action_next()
    {
        try
        {
            displayPerson(currentIndex + 1);
            currentIndex++;
            //label_indicator.setText(build_indicator());
        }
        catch (IndexOutOfBoundsException exception)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No more contacts");
            alert.setContentText("No more contacts!");
            alert.showAndWait();
        }
    }

    @FXML
    private void action_save()
    {
        phonebook.update(currentIndex, textField_name.getText(), textField_address.getText(), textField_phone.getText());
    }

    @FXML
    private void action_import()
    {
        FileController.show(new Stage(), false, phonebook);
        //label_indicator.setText(build_indicator());
    }

    private void displayPerson(int index)
    {
            textField_name.setText(phonebook.getCurrentPerson(index).getName());
            textField_address.setText(phonebook.getCurrentPerson(index).getAddress());
            textField_phone.setText(phonebook.getCurrentPerson(index).getPhone());
    }

    /*
    private String build_indicator()
    {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < phonebook.getSize(); i++)
        {
            indicator.set(i, ".");
        }
        indicator.set(currentIndex, "|");
        for (String s : indicator) stringBuilder.append(s);
        return stringBuilder.toString();
    }

    public void displayIndicator()
    {
        label_indicator.setText(build_indicator());
    }

     */

}
