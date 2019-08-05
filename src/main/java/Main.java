import java.io.*;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = 6543;
        String exitWord = "bye";
        ServerMessageHandler serverMessageHandler = new ServerMessageHandler(new PrintWriter(System.out, true), port);
        ServerSocket server = new ServerSocket(port);
        serverMessageHandler.confirmServerStarted();

        EchoServer echoServer = new EchoServer(port, serverMessageHandler, server, exitWord);
        echoServer.listen();
    }
}