package at.nsc.main;

import at.nsc.controller.controller_MainWindow;
import javafx.application.Application;
import javafx.stage.Stage;

/**Übung 14 - Main
 * @author Niklas Schachl
 * @version 1.0, 18.2.2021
 */
public class Main extends Application
{
    public static void main (String[] args)
    {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception
    {
        controller_MainWindow.show(stage);
    }
}
