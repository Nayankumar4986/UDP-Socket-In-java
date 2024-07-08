import java.io.*;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(8080);
            byte[] buffer = new byte[1024];

            System.out.println("Server listening on port 8080");

            while (true) {
                // Receive packet from client
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Client: " + received);

                // Get client address and port
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();
                System.out.println("The client port is " + clientPort);

                // Send response to client
                String response = "Hello from server";
                byte[] responseBytes = response.getBytes();
                DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length, clientAddress, clientPort);
                socket.send(responsePacket);

                System.out.println("Message sent to client.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
