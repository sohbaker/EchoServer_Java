import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class EchoServer {
    private ServerSocket serverSocket;
    private Messages messages;
    private Executor executor;
    private int clientId = 0;

    public EchoServer(ServerSocket serverSocket, Messages messages, Executor executor) {
        this.serverSocket = serverSocket;
        this.messages = messages;
        this.executor = executor;
    }

    public void start() {
        while (true) listenForConnections();
    }

    public void listenForConnections() {
        try {
            Socket clientSocket = serverSocket.accept();
            clientId++;
            executor.execute(new ClientHandler(clientSocket, messages, clientId));
        } catch (IOException ex) {
            messages.showIOException(ex);
        }
    }
}