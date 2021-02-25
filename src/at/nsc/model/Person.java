package at.nsc.model;

/**Übung 14 - Model
 * @author Niklas Schachl
 * @version 1.0, 25.2.2021
 */
public class Person
{
    private String name;
    private String Address;
    private String phone;

    public Person(String name, String address, String phone)
    {
        this.name = name;
        Address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
