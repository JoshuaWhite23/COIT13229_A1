package a1;

import java.net.*;
import java.io.*;

/**
 * Runs the Client for the UCP side of the application.
 * Requests the member object list from server
 * Displays the list once returned
 * 
 * @author Joshua
 */
public class UDPClient {

    public static void main(String args[]) {
        // Setup Socket and Port data
        DatagramSocket aSocket = null;
        int serverPort = 2275;
        
        try {
            // Prepare client to send message to server
            aSocket = new DatagramSocket();
            String fileName = "memberlistObject";
            byte[] f = fileName.getBytes();
            InetAddress aHost = InetAddress.getByName("localhost");
            
            // Create and Send Packet
            DatagramPacket request = new DatagramPacket(f, fileName.length(), aHost, serverPort);
            aSocket.send(request);

            //Prepare for Server Reply
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);

            // Display server reply
            String response = new String(reply.getData(), 0, reply.getLength());
            System.out.println("Response from Server: \n" + response);
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
}
