import java.io.*;
import java.net.ServerSocket;

public class EchoServer {
    int port;
    PrintStream output;
    ServerSocket server;

    public EchoServer(int portNumber, PrintStream output) {
        this.port = portNumber;
        this.output = output;
    }

    public void start(int port) {
        try {
            this.server = new ServerSocket(port);
            output.println("--Server started: port " + port + "--");
        } catch (IOException ex) {
            output.println(ex);
        }
    }
}
