package a1;

import java.net.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author Joshua
 */
public class UDPClient {

    public static void main(String args[]) {

        //The first argument is the message to send to the server.
        //The second argument is the name of the server.
        DatagramSocket aSocket = null;
        try {

            //Create a UDP socket
            aSocket = new DatagramSocket();

            String msg = "memberlistObject";
            //Prepare the message to send to the server
            byte[] m = msg.getBytes();
            InetAddress aHost = InetAddress.getByName("localhost");

            //Agreed port
            int serverPort = 2275;

            //Create a UDP datagram
            DatagramPacket request = new DatagramPacket(m, msg.length(), aHost, serverPort);

            //Send the request
            aSocket.send(request);

            //Prepare a buffer to receive the reply from the server
            byte[] buffer = new byte[1000];

            //Waiting for reply
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);

            //Display the reply
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
