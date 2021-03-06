package at.nsc.controller;

import at.nsc.model.Person;
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
import java.util.ResourceBundle;

/**Übung 14 - Controller
 * @author Niklas Schachl
 * @version 1.1, 5.3.2021
 */
public class controller_MainWindow implements Initializable
{
    private Phonebook phonebook;
    private Person currentPerson;
    private int currentIndex;
    public static controller_MainWindow ctrl;

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

    public controller_MainWindow()
    {

    }

    public static void show(Stage stage)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(controller_MainWindow.class.getResource("/at/nsc/view/view_MainWindow.fxml"));
            Parent root = fxmlLoader.load();

            //get controller which is connected to this fxml file
            ctrl = fxmlLoader.getController();

            stage.setTitle("People");
            stage.getIcons().add(new Image("/at/nsc/images/icon_people.png"));
            stage.setScene(new Scene(root));

            ctrl.startup(stage);

            stage.show();
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

    private void startup(Stage stage)
    {
        phonebook = new Phonebook();

        // Database will be saved on closing
        stage.setOnCloseRequest(windowEvent -> {
            if (phonebook.isNotSaved())
                controller_ExitWindow.show(new Stage(), phonebook);
        });

        // display first entry
        currentPerson = new Person(phonebook,"Max Mustermann", "Musterweg 1", "0664/656565");
        phonebook.addPerson(currentPerson);
        currentPerson = phonebook.firstPerson();
        currentIndex = currentPerson.getIndex();
        displayIndicator();
        displayPerson();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        button_back.setGraphic(new ImageView(new Image("at/nsc/images/icon_back.png")));
        button_next.setGraphic(new ImageView(new Image("at/nsc/images/icon_next.png")));
        button_add.setGraphic(new ImageView(new Image("at/nsc/images/icon_add.png")));
        button_delete.setGraphic(new ImageView(new Image("at/nsc/images/icon_delete.png")));
        button_save.setGraphic(new ImageView(new Image("/at/nsc/images/icon_save.png")));
    }

    @FXML
    private void action_back()
    {
        try
        {
            action_save();
            Person person = currentPerson.prev();
            if (person != null)
            {
                currentPerson = person;
                currentIndex = currentPerson.getIndex();
            }
            //currentIndex = currentPerson.getIndex();
            displayPerson();
            displayIndicator();

        }
        catch (Exception exception)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No more contacts");
            alert.setContentText("No more contacts!");
            alert.setResizable(true);
            alert.showAndWait();
        }
    }

    @FXML
    private void action_add()
    {
        try
        {
            action_save();
            currentPerson = new Person(phonebook, "", "", "");
            displayPerson();
            phonebook.sort();
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
        currentIndex = currentPerson.getIndex();
        displayIndicator();
    }

    @FXML
    private void action_delete()
    {
        try
        {
            Person deletedPerson = currentPerson;
            Person nextPerson = deletedPerson.next();
            Person previousPerson = deletedPerson.prev();

            phonebook.deletePerson(deletedPerson);

            if (nextPerson != null)
            {
                currentPerson = nextPerson;
            }
            else if (previousPerson != null)
            {
                currentPerson = previousPerson;
            }
            else
                currentPerson = new Person(phonebook, "", "", "");

            currentIndex = currentPerson.getIndex();
            displayIndicator();
            displayPerson();
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
        currentIndex = currentPerson.getIndex();
        displayIndicator();
    }

    @FXML
    private void action_next()
    {
        try
        {
            action_save();
            Person person = currentPerson.next();
            if (person != null)
            {
                currentPerson = person;
            }
            currentIndex = currentPerson.getIndex();
            displayPerson();
            displayIndicator();
        }
        catch (Exception exception)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No more contacts");
            alert.setContentText("No more contacts!");
            alert.showAndWait();
        }
        currentIndex = currentPerson.getIndex();
        displayPerson();
        displayIndicator();
    }

    @FXML
    private void action_save()
    {
        currentPerson.setName(textField_name.getText());
        currentPerson.setAddress(textField_address.getText());
        currentPerson.setPhone(textField_phone.getText());

        phonebook.sort();
    }

    private void displayPerson()
    {
        if (currentPerson != null)
        {
            textField_name.setText(currentPerson.getName());
            textField_address.setText(currentPerson.getAddress());
            textField_phone.setText(currentPerson.getPhone());
        }
    }


    private String build_indicator()
    {
        currentIndex = currentPerson.getIndex();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i <= phonebook.getSize(); i++)
        {
            if (i == currentIndex)
                stringBuilder.append("|");
            else
                stringBuilder.append(".");
        }

        return stringBuilder.toString();
    }

    public void displayIndicator()
    {
        label_indicator.setText(build_indicator());
    }
}
