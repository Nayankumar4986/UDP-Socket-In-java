import java.io.*;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName("127.0.0.1");
            int port = 8080;

            // Read input from the user
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a message:");
            String message = userInput.readLine();

            // Convert the message to bytes
            byte[] buffer = message.getBytes();

            // Create a datagram packet and send it
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet);

            System.out.println("Sent " + buffer.length + " bytes to " + address.getHostAddress());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
