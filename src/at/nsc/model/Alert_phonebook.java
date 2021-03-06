package at.nsc.model;

import javafx.scene.control.Alert;

public class Alert_phonebook
{
    public static void showAlert(String message)
    {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Wrong input");
        alert.setResizable(true);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
