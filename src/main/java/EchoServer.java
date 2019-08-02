import java.io.*;
import java.net.*;

public class EchoServer {
    int port;
    ServerSocket serverSocket;
    ServerMessageHandler serverMessageHandler;
    BufferedReader clientIn;
    PrintWriter clientOut;
    String exitWord;

    public EchoServer(int portNumber, ServerMessageHandler serverMessageHandler, ServerSocket serverSocket, String exitWord) {
        this.port = portNumber;
        this.serverMessageHandler = serverMessageHandler;
        this.serverSocket = serverSocket;
        this.exitWord = exitWord;
    }

    public void listen() {
        try {
            Socket clientSocket = this.serverSocket.accept();
            serverMessageHandler.confirmAcceptClientConnection();
            clientIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            clientOut = new PrintWriter(clientSocket.getOutputStream(), true);
            run();
        } catch (IOException ex) {
            serverMessageHandler.printExceptionError(ex);
        }
    }

    public void run() {
        while (true) {
            String message = readInput();
            if (noMoreMessagesFromClient(message)) {
                stop();
                break;
            }
            clientOut.println(response(message));
        }
    }

    public void stop() {
        try {
            serverMessageHandler.confirmCloseServer();
            this.serverSocket.close();
        } catch (IOException ex) {
            serverMessageHandler.printExceptionError(ex);
        }
    }

    private String readInput() {
        try {
            return clientIn.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean noMoreMessagesFromClient(String message) {
        return message.equalsIgnoreCase(this.exitWord) || message == null;
    }

    private String response(String incomingMessage) {
        return incomingMessage;
    }
}
