import java.io.*;
import java.net.*;

public class EchoServer {
    private int port;
    private ServerSocket serverSocket;
    private MessageHandler messageHandler;
    private BufferedReader clientInputStream;
    private PrintWriter clientOutputStream;
    private String exitWord;

    public EchoServer(int portNumber, MessageHandler messageHandler, ServerSocket serverSocket, String exitWord) {
        this.port = portNumber;
        this.messageHandler = messageHandler;
        this.serverSocket = serverSocket;
        this.exitWord = exitWord;
    }

    public void listen() {
        try {
            Socket clientSocket = this.serverSocket.accept();
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

    public String acceptRequestFromAClient(Socket clientSocket) {
        String request = "";
        try {
          request = new BufferedReader((new InputStreamReader(clientSocket.getInputStream()))).readLine();
        } catch (Exception ex) {
            output.println(ex);
        }
        return request;
    }

    public void stop() {
        try {

            messageHandler.confirmCloseServer();
            this.serverSocket.close();
        } catch (IOException ex) {
            messageHandler.printExceptionError(ex);
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
        return message.equalsIgnoreCase(this.exitWord) || message == null;
    }
}
