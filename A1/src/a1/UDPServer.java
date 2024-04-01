package a1;

import java.net.*;
import java.io.*;
import java.util.HashSet;

/**
 *
 * @author Joshua
 */
public class UDPServer {

    public static void main(String args[]) {
        DatagramSocket aSocket = null;
        int port = 2275;
        try {
            aSocket = new DatagramSocket(port);
            byte[] buffer = new byte[1000];
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                System.out.println("Request from Client: " + new String(request.getData(), 0, request.getLength()));
                String response = constructTable();
                byte[] r = response.getBytes();
                DatagramPacket reply = new DatagramPacket(r, response.length(), request.getAddress(), request.getPort());
                aSocket.send(reply);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null) {
                aSocket.close();
            }
        }
    }

    // Helper method to construct the member table being sent
    private static String constructTable() {
        String table = "";
        table += String.format("%-15s", "|First Name");
        table += String.format("%-15s", "|Last Name");
        table += String.format("%-20s", "|Address");
        table += String.format("%-15s", "|Phone Number");
        table += "|\n------------------------------------------------------------------ \n";

        // Read and add each object from the memberlistObject file
        try {
            FileInputStream fis = new FileInputStream("memberlistObject");
            ObjectInputStream in = new ObjectInputStream(fis);

            // Grab hash set of members from file
            HashSet<Member> members = (HashSet<Member>) in.readObject();

            // Format table for each member
            for (Member m : members) {
                table += String.format("%-15s", "|" + m.getFirstName());
                table += String.format("%-15s", "|" + m.getLastName());
                table += String.format("%-20s", "|" + m.getAddress());
                table += String.format("%-15s", "|" + m.getPhone());
                table += "| \n";
            }
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return table;
    }
}
