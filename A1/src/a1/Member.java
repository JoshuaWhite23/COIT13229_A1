package a1;

import java.io.Serializable;

/**
 * Model class for the member object used in the project
 *
 * @author Joshua
 */
public class Member implements Serializable {

    private String firstName;
    private String lastName;
    private String address;
    private String phone;

    /**
     * Simple constructor for the member class
     */
    public Member() {

    }

    /**
     * Simple constructor with parameters
     *
     * @param firstName first name of the member
     * @param lastName last name of the member
     * @param address address of the member
     * @param phone phone number of the member
     */
    public Member(String firstName, String lastName, String address, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    /**
     * Getter for member first name
     *
     * @return the first name of the member
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for member first name
     *
     * @param firstName the first name of the member
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for member last name
     *
     * @return the last name of the member
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for member last name
     *
     * @param lastName the last name of the member
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for member address
     *
     * @return the address of the member
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter for member address
     *
     * @param address the address of the member
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter for member phone number
     *
     * @return the phone number of the member
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Setter for member phone number
     *
     * @param phone the phone number of the member
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Getter for details of the member
     *
     * @return the member details in a common format method
     */
    public String viewDetails() {
        return firstName + ":" + lastName + ":" + address + ":" + phone;
    }
}
