package a1;import java.net.*;import java.io.*;import java.util.HashSet;import java.util.Scanner;import java.util.TimerTask;/** * Runs the Server for the TCP side of the application. * Uses the Connection class to read client requests * Every 2 seconds, requests Serialization of member objects *  * @author Joshua */public class TCPServer {    // Recording of next client number    static int clientNumber = 1;    public static void main(String args[]) {        // Setup timer for writing to file        int interval = 2000;        java.util.Timer tm = new java.util.Timer();        tm.schedule(new WriteToFile(), interval, interval);        try {            int serverPort = 1175;            ServerSocket listenSocket = new ServerSocket(serverPort);            // Wait for and enable connections            while (true) {                Socket clientSocket = listenSocket.accept();                Connection c = new Connection(clientSocket);                // Assign client number                c.clientID = clientNumber;                clientNumber++;            }        } catch (IOException e) {            System.out.println("Listen socket:" + e.getMessage());        }    }}/** * Reads new member details from clients * Saves members to memberlist.txt file *  * @author Joshua */class Connection extends Thread {    // Setup Variables    int clientID;    DataInputStream in;    DataOutputStream out;    Socket clientSocket;    /**     * Constructs a connection and starts it     */    public Connection(Socket aClientSocket) {        try {            clientSocket = aClientSocket;            in = new DataInputStream(clientSocket.getInputStream());            out = new DataOutputStream(clientSocket.getOutputStream());            this.start();        } catch (IOException e) {            System.out.println("Connection:" + e.getMessage());        }    }    /**     * Runs when the project starts     */    public void run() {        try {            while (true) {                // Read data                String data = in.readUTF();                System.out.println("Received data from client: " + clientID);                String[] input = data.split(":");                int id = Integer.parseInt(input[0]);                String firstName = input[1];                String lastName = input[2];                String address = input[3];                int phone = Integer.parseInt(input[4]);                // Create member and write to file                Member m = new Member(firstName, lastName, address, phone);                FileWriter fw = new FileWriter("memberlist.txt", true);                fw.write(m.viewDetails() + "\n");                fw.close();                // Respond to client                out.writeUTF("Member number " + id + " saved successfully!");            }        } catch (EOFException e) {            System.out.println("EOF: " + e.getMessage());        } catch (IOException e) {            System.out.println("IO - Client " + clientID + ": " + e.getMessage());        } finally {            if (clientSocket != null) {                try {                    clientSocket.close();                } catch (IOException e) {                    System.out.println("close: " + e.getMessage());                }            }        }    }}/** * Completes Serialization of member objects into memberlistObject file *  * @author Joshua */class WriteToFile extends TimerTask {    /**     * Runs when the project starts     */    public void run() {        try {            // Setup FileOutputStreams and  Scanner            FileOutputStream fos = new FileOutputStream("memberlistObject");            ObjectOutputStream out = new ObjectOutputStream(fos);            Scanner sa = new Scanner(new File("memberlist.txt"));                        // Using hash set to store all members and provide easier reading            HashSet<Member> members = new HashSet();            // Read memberlist file            while (sa.hasNextLine()) {                String line = sa.nextLine();                String[] data = line.split(":");                String firstName = data[0];                String lastName = data[1];                String address = data[2];                int phone = Integer.parseInt(data[3]);                // Create member and add to hash set                Member m = new Member(firstName, lastName, address, phone);                members.add(m);            }            // Write memberlistObject file            out.writeObject(members);                        // Close Scanner and Output Stream            sa.close();            out.close();        } catch (IOException ex) {            System.out.println("IO: " + ex.getMessage());        }    }}