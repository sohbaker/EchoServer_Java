import java.io.*;
import java.net.*;

public class EchoServer {
    int port;
    ServerSocket serverSocket;
    ServerMessageHandler serverMessageHandler;
    ClientMessageHandler clientMessageHandler;

    public EchoServer(int portNumber, ServerMessageHandler serverMessageHandler, ServerSocket serverSocket) {
        this.port = portNumber;
        this.serverMessageHandler = serverMessageHandler;
        this.serverSocket = serverSocket;
    }

    public void listen() {
        try {
            Socket clientSocket = this.serverSocket.accept();
            BufferedReader clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter clientOut = new PrintWriter(clientSocket.getOutputStream());
            clientMessageHandler = new ClientMessageHandler(clientIn, clientOut, "bye");
            clientMessageHandler.run();
        } catch (IOException ex) {
            serverMessageHandler.printExceptionError(ex);
        }
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
