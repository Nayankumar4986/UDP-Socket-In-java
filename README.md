Below is the Java code for a UDP client that performs similar functionality to the provided C code. The Java client will prompt the user for input, send it to a server using UDP, and print the number of bytes sent and the server's IP address.
** CLIENT SIDE 
```java
import java.net.*;
import java.io.*;

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
```

### Explanation:

1. **Import Statements:**
   ```java
   import java.net.*;
   import java.io.*;
   ```

2. **Socket Initialization:**
   ```java
   DatagramSocket socket = null;
   try {
       socket = new DatagramSocket();
       InetAddress address = InetAddress.getByName("127.0.0.1");
       int port = 8080;
   ```

3. **Reading User Input:**
   ```java
   BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
   System.out.println("Enter a message:");
   String message = userInput.readLine();
   ```

4. **Sending Data:**
   ```java
   byte[] buffer = message.getBytes();
   DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, port);
   socket.send(packet);
   ```

5. **Printing Output:**
   ```java
   System.out.println("Sent " + buffer.length + " bytes to " + address.getHostAddress());
   ```

6. **Exception Handling and Closing Socket:**
   ```java
   } catch (IOException e) {
       e.printStackTrace();
   } finally {
       if (socket != null && !socket.isClosed()) {
           socket.close();
       }
   }
   ```

### Running the Application:
1. **Compile the Java code:**
   ```sh
   javac UDPClient.java
   ```

2. **Run the Java client:**
   ```sh
   java UDPClient
   ```

3. **Input a message when prompted and observe the output.**

This code mimics the functionality of your C code for a UDP client, allowing you to send a message to a server and print the details of the transmission.








** SERVER SIDE 
```java
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
```

### Explanation:

1. **Import Statements:**
   ```java
   import java.net.*;
   ```

2. **Socket Initialization:**
   ```java
   DatagramSocket socket = null;
   try {
       socket = new DatagramSocket(8080);
       byte[] buffer = new byte[1024];

       System.out.println("Server listening on port 8080");
   ```

3. **Receiving Data from Client:**
   ```java
   while (true) {
       DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
       socket.receive(packet);
       String received = new String(packet.getData(), 0, packet.getLength());
       System.out.println("Client: " + received);
   ```

4. **Getting Client Address and Port:**
   ```java
   InetAddress clientAddress = packet.getAddress();
   int clientPort = packet.getPort();
   System.out.println("The client port is " + clientPort);
   ```

5. **Sending Response to Client:**
   ```java
   String response = "Hello from server";
   byte[] responseBytes = response.getBytes();
   DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length, clientAddress, clientPort);
   socket.send(responsePacket);

   System.out.println("Message sent to client.");
   ```

6. **Exception Handling and Closing Socket:**
   ```java
   } catch (IOException e) {
       e.printStackTrace();
   } finally {
       if (socket != null && !socket.isClosed()) {
           socket.close();
       }
   }
   ```

### Running the Application:
1. **Compile the Java code:**
   ```sh
   javac UDPServer.java
   ```

2. **Run the Java server:**
   ```sh
   java UDPServer
   ```

The server will listen for incoming UDP packets from clients, print the received message, and send a response back to the client. The server also prints the client's port number. The server runs indefinitely, handling multiple client requests in a loop.

### Note:
To test this server, you can use the UDP client code provided in a previous message, or any other UDP client that sends messages to the server on port 8080.

