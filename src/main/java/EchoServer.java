import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class EchoServer {
    private Executor executor;
    private MessageHandler messageHandler;
    private ServerSocket serverSocket;
    private String exitWord;

    public EchoServer(MessageHandler messageHandler, ServerSocket serverSocket, String exitWord, Executor executor) {
        this.messageHandler = messageHandler;
        this.serverSocket = serverSocket;
        this.exitWord = exitWord;
        this.executor = executor;
    }

    public void start() {
        while (true) {
            listenForConnections();
        }
    }

    public void listenForConnections() {
        try {
            Socket clientSocket = serverSocket.accept();
            executor.execute(new ClientHandler(clientSocket, messageHandler, exitWord));
        } catch (IOException ex) {
            messageHandler.printExceptionError(ex);
        }
    }
}
