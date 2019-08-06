import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class EchoServer {
    private Executor executor = Executors.newCachedThreadPool();
    private MessageHandler messageHandler;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private String exitWord;

    public EchoServer(MessageHandler messageHandler, ServerSocket serverSocket, String exitWord) {
        this.messageHandler = messageHandler;
        this.serverSocket = serverSocket;
        this.exitWord = exitWord;
    }

    public void listenForConnections() {
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                executor.execute(new ClientHandler(clientSocket, messageHandler, exitWord));
            } catch (IOException ex) {
                messageHandler.printExceptionError(ex);
            }
        }
    }
}
