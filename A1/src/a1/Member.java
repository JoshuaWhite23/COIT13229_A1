package a1;

import java.io.Serializable;

/**
 *
 * @author Joshua
 */
public class Member implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private int phone;

    public Member() {

    }

    public Member(int id, String firstName, String lastName, String address, int phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String viewDetails() {
        return firstName + ":" + lastName + ":" + address + ":" + phone;
    }
}