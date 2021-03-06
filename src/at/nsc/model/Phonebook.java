package at.nsc.model;

import javafx.scene.control.Alert;

import java.io.*;
import java.util.*;

/**Übung 14 - MOdel
 * @author Niklas Schachl
 * @version 1.1, 6.3.2021
 */
public class Phonebook
{
    private TreeSet<Person> list_persons;
    private final String fileName = "phonebook.csv";

    public Phonebook()
    {
        list_persons = new TreeSet<>();

        load();
    }

    public void save()
    {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(String.format("saves/%s", fileName))))
        {
            bufferedWriter.write("Name;Address;Phone");
            bufferedWriter.newLine();

            for (Person person : list_persons) {
                bufferedWriter.write(person.toString());
                bufferedWriter.newLine();
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Save successful");
            alert.setContentText("File successfully saved!");
            alert.showAndWait();
        }
        catch (IOException exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Save Error");
            alert.setResizable(true);
            alert.setContentText(String.format("Could not save file!%n%nError message: %s", exception.getMessage()));
            alert.showAndWait();
            System.err.println(exception.getMessage());
            exception.printStackTrace(System.err);
        }
    }

    public void load()
    {
        list_persons.clear();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(String.format("saves/%s", fileName))))
        {
            if (fileReader.readLine().toUpperCase(Locale.ROOT).equals("NAME;ADDRESS;PHONE"))
            {
                String line;
                while(((line = fileReader.readLine()) != null))
                {
                    list_persons.add(Person.fromString(line, this));
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
        catch (FileNotFoundException exception)
        {
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(String.format("saves/%s", fileName))))
            {
                bufferedWriter.write("Name;Address;Phone");
                bufferedWriter.newLine();
            }
            catch (IOException ex)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Internal Error");
                alert.setResizable(true);
                alert.setContentText(String.format("An internal Error occurred. Please restart the program%nor contact the developer on GitHub%n%nError message: %s", ex.getMessage()));
                alert.showAndWait();
                System.err.println(ex.getMessage());
                ex.printStackTrace(System.err);
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No file");
            alert.setContentText("There was no file, so a new one was created");
            alert.showAndWait();
        }
        catch (IOException exception)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Load Error");
            alert.setResizable(true);
            alert.setContentText(String.format("Could not load file!%n%nError message: %s", exception.getMessage()));
            alert.showAndWait();
            System.err.println(exception.getMessage());
            exception.printStackTrace(System.err);
        }
    }

    public Person firstPerson() {
        Person first;
        try {
            first = list_persons.first();
        }
        catch (NoSuchElementException ex) {
            first = null;
        }
        return first;
    }

    public void addPerson(Person person)
    {
        list_persons.add(person);
        person.setIndex(getIndexOfPerson(person));
    }

    public void deletePerson(Person person)
    {
        list_persons.remove(person);
    }

    public int getSize()
    {
        return list_persons.size();
    }

    public Person getPrevious(Person person)
    {
        return list_persons.lower(person);
    }

    public Person getNext(Person person)
    {
        return list_persons.higher(person);
    }

    public int getIndexOfPerson(Person person) {
        int index = -1;
        int i = 0;

        for (Person p : list_persons) {
            i++;
            if (p == person) {
                index = i;
            }
        }
        return index;
    }

    public void sort()
    {
        TreeSet<Person> neu = new TreeSet<>();
        for (Person p: list_persons) {
            neu.add(p);
        }
        list_persons = neu;

        for (Person person : list_persons) {
            person.setIndex(getIndexOfPerson(person));
        }
    }

    public boolean isNotSaved()
    {
        TreeSet<Person> file = new TreeSet<>();

        Iterator<Person> iterator = list_persons.iterator();
        Iterator<Person> fileIterator = file.iterator();

        try (BufferedReader fileReader = new BufferedReader(new FileReader(String.format("saves/%s", fileName))))
        {
            fileReader.readLine();
            String line;
            while(((line = fileReader.readLine()) != null))
            {
                file.add(Person.fromString(line, this));
            }
        }
        catch (Exception ignored)
        {

        }

        while (fileIterator.hasNext())
        {
            if (iterator.hasNext())
            {
                if (fileIterator.next() != iterator.next())
                    return false;
            }
            else
                return false;
        }
        return true;
    }
}
