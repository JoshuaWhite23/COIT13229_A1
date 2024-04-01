package a1;import java.net.*;import java.io.*;/** * * @author Joshua */public class TCPServer {    public static void main(String args[]) {        try {            int serverPort = 1175;            ServerSocket listenSocket = new ServerSocket(serverPort);            while (true) {                Socket clientSocket = listenSocket.accept();                Connection c = new Connection(clientSocket);            }        } catch (IOException e) {            System.out.println("Listen socket:" + e.getMessage());        }    }}class Connection extends Thread {    DataInputStream in;    DataOutputStream out;    Socket clientSocket;    public Connection(Socket aClientSocket) {        try {            clientSocket = aClientSocket;            in = new DataInputStream(clientSocket.getInputStream());            out = new DataOutputStream(clientSocket.getOutputStream());            this.start();        } catch (IOException e) {            System.out.println("Connection:" + e.getMessage());        }    }    public void run() {        try {            // Read data            String data = in.readUTF();            String[] input = data.split(":");            int id = Integer.parseInt(input[0]);            String firstName = input[1];            String lastName = input[2];            String address = input[3];            int phone = Integer.parseInt(input[4]);            Member m = new Member(firstName, lastName, address, phone);            System.out.println("Received data from client: 1");            out.writeUTF("Save Data of the member number: " + id);        } catch (EOFException e) {            System.out.println("EOF:" + e.getMessage());        } catch (IOException e) {            System.out.println("IO:" + e.getMessage());        } finally {            if (clientSocket != null) {                try {                    clientSocket.close();                } catch (IOException e) {                    System.out.println("close:" + e.getMessage());                }            }        }    }}