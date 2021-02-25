package at.nsc.model;

import javafx.scene.control.Alert;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**Übung 14 - MOdel
 * @author Niklas Schachl
 * @version 1.0, 25.2.2021
 */
public class Phonebook
{
    private final List<Person> list_persons = new LinkedList<>();

    public void save(String fileName)
    {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(String.format("saves/%s.csv", fileName))))
        {
            bufferedWriter.write("Name;Address;Phone");
            bufferedWriter.newLine();

            for (Person list_person : list_persons) {
                bufferedWriter.write(list_person.getName() + ";" + list_person.getAddress() + ";" + list_person.getPhone());
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save successful");
            alert.setContentText("File successfully saved!");
            alert.showAndWait();
        }
        catch (IOException exception)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Save Error");
            alert.setContentText(String.format("Could not save file!%n%nError message: %s", exception.getMessage()));
            alert.showAndWait();
            System.err.println(exception.getMessage());
            exception.printStackTrace(System.err);
        }
    }

    public void load(String fileName)
    {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(String.format("saves/%s.csv", fileName))))
        {
            if (fileReader.readLine().toUpperCase(Locale.ROOT).equals("NAME;ADDRESS;PHONE"))
            {
                String line;
                while(((line = fileReader.readLine()) != null))
                {
                    String s;
                    s = fileReader.readLine();
                    String[] row = s.split(";");
                    list_persons.add(new Person(row[0], row[1], row[2]));
                }

            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Load Error");
                alert.setContentText("Not a valid contact file!" + "\n" + "First row needs to be Name;Address;Phone");
                alert.showAndWait();
            }
        }
        catch (Exception exception)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Load Error");
            alert.setContentText(String.format("Could not load file!%n%nError message: %s", exception.getMessage()));
            alert.showAndWait();
            System.err.println(exception.getMessage());
            exception.printStackTrace(System.err);
        }
    }

    public void addPerson(String name, String address, String phoneNumber)
    {
        list_persons.add(new Person(name, address, phoneNumber));
    }

    public Person getCurrentPerson(int index)
    {
        return list_persons.get(index);
    }

    public void deletePerson(int index)
    {
        list_persons.remove(index);
    }

    public void update(int index, String name, String address, String phone)
    {
        list_persons.get(index).setName(name);
        list_persons.get(index).setAddress(address);
        list_persons.get(index).setPhone(phone);
    }

    public int getSize()
    {
        return list_persons.size();
    }
}
