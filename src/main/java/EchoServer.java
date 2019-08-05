import java.io.*;
import java.net.*;

public class EchoServer {
    private MessageHandler messageHandler;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private BufferedReader clientInputStream;
    private PrintWriter clientOutputStream;
    private String exitWord;

    public EchoServer(MessageHandler messageHandler, ServerSocket serverSocket, String exitWord) {
        this.messageHandler = messageHandler;
        this.serverSocket = serverSocket;
        this.exitWord = exitWord;
    }

    public void listen() {
        try {
            clientSocket = serverSocket.accept();
            messageHandler.confirmAcceptClientConnection();
            clientInputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            clientOutputStream = new PrintWriter(clientSocket.getOutputStream(), true);
            run();
        } catch (IOException ex) {
            messageHandler.printExceptionError(ex);
        }
    }

    private void run() {
        while (true) {
            String message = readInput();
            if (noMoreMessagesFromClient(message)) {
                stop();
                break;
            }
            clientOutputStream.println(message);
        }
    }

    private String readInput() {
        try {
            return clientInputStream.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean noMoreMessagesFromClient(String message) {
        return message == null || message.equalsIgnoreCase(exitWord);
    }

    private void stop() {
        try {
            clientInputStream.close();
            clientOutputStream.close();
            clientSocket.close();
            messageHandler.confirmCloseClientConnection();
            messageHandler.confirmCloseServer();
            serverSocket.close();
        } catch (IOException ex) {
            messageHandler.printExceptionError(ex);
        }
    }
}
