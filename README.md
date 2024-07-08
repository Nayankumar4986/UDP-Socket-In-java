Below is the Java code for a UDP client that performs similar functionality to the provided C code. The Java client will prompt the user for input, send it to a server using UDP, and print the number of bytes sent and the server's IP address.

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
