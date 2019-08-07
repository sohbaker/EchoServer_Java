import java.io.*;
import java.net.ServerSocket;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(args[0]);
        String exitWord = args[1];

        ServerSocket server = new ServerSocket(port);
        MessageHandler messageHandler = new MessageHandler(new PrintWriter(System.out, true));
        Executor executor = Executors.newCachedThreadPool();

        messageHandler.confirmServerStarted(port);

        EchoServer echoServer = new EchoServer(server, messageHandler, executor, exitWord);
        echoServer.start();
    }
}