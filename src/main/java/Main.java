import java.io.*;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = 6543;
        String exitWord = "bye";
        MessageHandler messageHandler = new MessageHandler(new PrintWriter(System.out, true), port);
        ServerSocket server = new ServerSocket(port);
        messageHandler.confirmServerStarted();

        EchoServer echoServer = new EchoServer(port, messageHandler, server, exitWord);
        echoServer.listen();
    }
}