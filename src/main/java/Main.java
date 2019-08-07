import java.io.*;
import java.net.ServerSocket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(args[0]);
        String exitWord = args[1];
        ServerSocket server = new ServerSocket(port);
        MessageHandler messageHandler = new MessageHandler(new PrintWriter(System.out, true));
        messageHandler.confirmServerStarted(port);
        Executor executor = Executors.newCachedThreadPool();

        EchoServer echoServer = new EchoServer(messageHandler, server, exitWord, executor);
        echoServer.start();
    }
}