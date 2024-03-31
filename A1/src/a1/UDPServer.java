package a1;

import java.net.*;
import java.io.*;

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
                System.out.println("Client Request: " + new String(request.getData(), 0, request.getLength()));
                String response = "|First Name \t |Last Name \t |Address \t |Phone Number \t | \n==================================================================";
                byte[] r = response.getBytes();
                DatagramPacket reply = new DatagramPacket(r, response.length(),request.getAddress(), request.getPort());
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
}
