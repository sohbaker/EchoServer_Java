import java.io.*;
import java.net.*;

public class EchoServer {
    int port;
    PrintStream output;
    ServerSocket serverSocket;

    public EchoServer(int portNumber, PrintStream output) {
        this.port = portNumber;
        this.output = output;
    }

    public void start() {
        try {
            this.serverSocket = new ServerSocket(port);
            output.println("--Server started. Listening on port " + port + "--");
        } catch (IOException ex) {
            output.println(ex);
        }
    }

    public String acceptRequestFromAClient(Socket clientSocket) {
        String request = "";
        try {
          request = new BufferedReader((new InputStreamReader(clientSocket.getInputStream()))).readLine();
        } catch (Exception ex) {
            output.println(ex);
        }
        return request;
    }

    public void stop() {
        try {
            this.serverSocket.close();
            output.println("--Server successfully shutdown--");
        } catch (IOException ex) {
            output.println(ex);
        }
    }
}
