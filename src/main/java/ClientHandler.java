import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private Messages serverMessages;
    private BufferedReader clientInputStream;
    private PrintWriter clientOutputStream;
    private int clientId;
    private boolean keepRunning = true;

    public ClientHandler(Socket clientSocket, Messages messages, int clientId) {
        this.clientSocket = clientSocket;
        this.serverMessages = messages;
        this.clientId = clientId;
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
            serverMessages.declareAcceptedClientConnection(clientId);
        } catch (IOException ex) {
            serverMessages.showIOException(ex);
        }
    }

    private void communicate() {
        while (keepRunning) {
            String inputLine = receiveData();
            if (noMoreData(inputLine)) {
                keepRunning = false;
                break;
            }
            clientOutputStream.println("[[echo]] " + inputLine);
        }
        closeClient();
    }

    private String receiveData() {
        try {
            return clientInputStream.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean noMoreData(String message) {
        return message == null || message.equalsIgnoreCase("exit");
    }

    private void closeClient() {
        try {
            clientInputStream.close();
            clientOutputStream.close();
            serverMessages.declareClosingClientConnection(clientId);
            clientSocket.close();
        } catch (IOException ex) {
            serverMessages.showIOException(ex);
        }
    }
}