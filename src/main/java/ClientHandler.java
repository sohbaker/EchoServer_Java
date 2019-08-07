import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private MessageHandler messageHandler;
    private BufferedReader clientInputStream;
    private PrintWriter clientOutputStream;
    private String exitWord;
    private int id;

    public ClientHandler(Socket clientSocket, MessageHandler messageHandler, String exitWord, int id) {
        this.clientSocket = clientSocket;
        this.messageHandler = messageHandler;
        this.exitWord = exitWord;
        this.id = id;
    }

    @Override
    public void run() {
        setUpClientIOStreams();
        communicate();
    }

    private void setUpClientIOStreams() {
        try {
            clientInputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            clientOutputStream = new PrintWriter(clientSocket.getOutputStream(), true);
            messageHandler.confirmAcceptClientConnection(id);
        } catch (IOException ex) {
            messageHandler.printExceptionError(ex);
        }
    }

    private void communicate() {
        while (true) {
            String inputLine = receiveData();
            if (noMoreData(inputLine)) {
                closeClient();
                break;
            }
            clientOutputStream.println("[[echo]] " + inputLine);
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

    private void closeClient() {
        try {
            clientInputStream.close();
            clientOutputStream.close();
            messageHandler.confirmCloseClientConnection(id);
            clientSocket.close();
        } catch (IOException ex) {
            messageHandler.printExceptionError(ex);
        }
    }
}