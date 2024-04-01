package a1;import java.net.*;import java.io.*;import java.util.*;/** * Runs the Client for the TCP side of the application. * Queries the user for member details. * Handles error checking for data. * Sends data to the server. *  * @author Joshua */public class TCPClient {    public static void main(String args[]) {        Socket s = null;        try {            // Setup Socket, IOStreams and Scanner            int serverPort = 1175;            s = new Socket("localhost", serverPort);            DataInputStream in = new DataInputStream(s.getInputStream());            DataOutputStream out = new DataOutputStream(s.getOutputStream());            Scanner sa = new Scanner(System.in);            // Asks user for member details, keeps track of member number            int n = 1;            while (true) {                System.out.println("Enter Detail for Member Number: " + n + " (Note: Fields cannot contain ':')");                                // First Name                System.out.println("Enter your First Name:");                String firstName = sa.nextLine();                while (firstName.isBlank() || firstName.indexOf(':') != -1) {                    System.out.println("First Name invalid, please try again:");                    firstName = sa.nextLine();                }                                // Last Name                System.out.println("Enter your Last Name:");                String lastName = sa.nextLine();                while (lastName.isBlank() || lastName.indexOf(':') != -1) {                    System.out.println("Last Name invalid, please try again:");                    lastName = sa.nextLine();                }                                // Address                System.out.println("Enter your Address:");                String address = sa.nextLine();                while (address.isBlank() || address.indexOf(':') != -1) {                    System.out.println("Address invalid, please try again:");                    address = sa.nextLine();                }                                // Phone Number                System.out.println("Enter your Phone Number:");                String phone = sa.nextLine();                while (address.isBlank() || address.indexOf(':') != -1) {                    System.out.println("Phone Number invalid, please try again:");                    address = sa.nextLine();                }                                String message = firstName + ":" + lastName + ":" + address + ":" + phone;                // Send inputs to server                out.writeUTF(n + ":" + message);                System.out.println("Sending Data to Server...");                System.out.println(message);                // Read response from server                String response = in.readUTF();                System.out.println("Response from Server: " + response);                System.out.println("-------------------------------------------");                n++;            }        } catch (UnknownHostException e) {            System.out.println("Socket: " + e.getMessage());        } catch (EOFException e) {            System.out.println("EOF: " + e.getMessage());        } catch (IOException e) {            System.out.println("IO: " + e.getMessage());        } finally {            if (s != null) {                try {                    s.close();                } catch (IOException e) {                    System.out.println("close: " + e.getMessage());                }            }        }    }}