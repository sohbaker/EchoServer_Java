import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class EchoServer {
    private ServerSocket serverSocket;
    private MessageHandler messageHandler;
    private Executor executor;
    private String exitWord;
    private int clientId = 0;

    public EchoServer(ServerSocket serverSocket, MessageHandler messageHandler, Executor executor, String exitWord) {
        this.serverSocket = serverSocket;
        this.messageHandler = messageHandler;
        this.executor = executor;
        this.exitWord = exitWord;
    }

    public void start() {
        while (true) listenForConnections();
    }

    public void listenForConnections() {
        try {
            Socket clientSocket = serverSocket.accept();
            clientId++;
            executor.execute(new ClientHandler(clientSocket, messageHandler, exitWord, clientId));
        } catch (IOException ex) {
            messageHandler.printExceptionError(ex);
        }
    }
}