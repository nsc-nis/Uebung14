package at.nsc.model;

/**Übung 14 - Model
 * @author Niklas Schachl
 * @version 1.1, 4.3.2021
 */
public class Person implements Comparable
{
    private String name;
    private String address;
    private String phone;
    private int index;
    private Phonebook phonebook;

    public Person(Phonebook phonebook, String name, String address, String phone)
    {
        setPhonebook(phonebook);
        setName(name);
        setAddress(address);
        setPhone(phone);

        phonebook.addPerson(this);
    }

    public void setPhonebook(Phonebook phonebook) {
        this.phonebook = phonebook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        /*
        Wieso ist if(name.equals(null)) immer false?

        if (name.equals(null))
            Alert_phonebook.showAlert("There has to be a name for the contact");
        else
            this.name = name;

         */
        if (name == null)
            Alert_phonebook.showAlert("There has to be a name for the contact");
        else
            this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone)
    {
        /*
        Hier ist das selbe Problem wie oben, wenn ich if(phone == null) schreibe, funktioniert es nicht, und wenn ich if(phone.equals(null)) schreibe, sagt IntelliJ dass das immer "false" ist?
         */
        if (phone ==null)
        {
            Alert_phonebook.showAlert("There has to be a phone number for the contact");
        }
        else
        {
            boolean isCorrect = true;
            char[] number = phone.toCharArray();

            for (char c : number) {
                try {
                    Integer.parseInt(String.valueOf(c));
                } catch (Exception exception) {
                    if (c == '/')
                        isCorrect = true;
                    else if (number[0] == '+')
                        isCorrect = true;
                    else if (c == ' ')
                        isCorrect = true;
                    else
                        isCorrect = false;
                }
            }

            if (isCorrect)
                this.phone = phone;
            else
                Alert_phonebook.showAlert("The phone number is only allowed to contain numbers, spaces, \"/\" and \"+\"");
        }
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static Person fromString(String input, Phonebook phonebook)
    {
        String[] row = input.split(";");

        return new Person(phonebook, row[0], row[1], row[2]);
    }

    @Override
    public String toString()
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name);
        stringBuilder.append(";");
        stringBuilder.append(address);
        stringBuilder.append(";");
        stringBuilder.append(phone);

        return stringBuilder.toString();
    }

    @Override
    public int compareTo(Object o) {
        Person other = (Person) o;

        return name.compareTo(other.name);
    }
    public Person prev() {
        return phonebook.getPrevious(this);
    }

    public Person next() {
        return phonebook.getNext(this);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        Person person = (Person) object;

        if (!name.equals(person.name)) {
            return false;
        }
        if (address != null ? !address.equals(person.address) : person.address != null) {
            return false;
        }
        return phone.equals(person.phone);
    }
}
