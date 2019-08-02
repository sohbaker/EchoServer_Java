import java.io.*;
import java.net.*;

public class EchoServer {
    int port;
    ServerSocket serverSocket;
    ServerMessageHandler serverMessageHandler;

    public EchoServer(int portNumber, ServerMessageHandler serverMessageHandler, ServerSocket serverSocket) {
        this.port = portNumber;
        this.serverMessageHandler = serverMessageHandler;
        this.serverSocket = serverSocket;
    }

    public void stop() {
        try {
            this.serverSocket.close();
            serverMessageHandler.confirmCloseServer();
        } catch (IOException ex) {
            serverMessageHandler.printExceptionError(ex);
        }
    }
}
q