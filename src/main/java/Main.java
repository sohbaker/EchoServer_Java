import java.io.*;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = 5000;
        String exitWord = "bye";
        ServerSocket server = new ServerSocket(port);
        MessageHandler messageHandler = new MessageHandler(new PrintWriter(System.out, true), port);
        messageHandler.confirmServerStarted();

        EchoServer echoServer = new EchoServer(messageHandler, server, exitWord);
        echoServer.listen();
    }
}