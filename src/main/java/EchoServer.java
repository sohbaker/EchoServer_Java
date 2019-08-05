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

    public void listenForConnections() {
        try {
            clientSocket = serverSocket.accept();
            messageHandler.confirmAcceptClientConnection();
            setUpClientIOStreams();
            echo();
        } catch (IOException ex) {
            messageHandler.printExceptionError(ex);
        }
    }

    private void echo() {
        while (true) {
            String inputLine = receiveData();
            if (noMoreData(inputLine)) {
                close();
                break;
            }
            clientOutputStream.println(">> " + inputLine);
        }
    }

    private void close() {
        try {
            closeClientConnection();
            messageHandler.confirmCloseClientConnection();
            messageHandler.confirmCloseServer();
            serverSocket.close();
        } catch (IOException ex) {
            messageHandler.printExceptionError(ex);
        }
    }

    private String receiveData() {
        try {
            return clientInputStream.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean noMoreData(String message) {
        return message == null || message.equalsIgnoreCase(exitWord);
    }

    private void setUpClientIOStreams() {
        try {
            clientInputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            clientOutputStream = new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException ex) {
            messageHandler.printExceptionError(ex);
        }
    }

    private void closeClientConnection() {
        try {
            clientInputStream.close();
            clientOutputStream.close();
            clientSocket.close();
        } catch (IOException ex) {
            messageHandler.printExceptionError(ex);
        }
    }
}
