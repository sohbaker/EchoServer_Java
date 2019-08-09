import java.io.*;
import java.net.ServerSocket;
import java.util.concurrent.*;

public class Main {
    private static int port;

    public static void main(String[] args) throws IOException {
        getPort(args);
        ServerSocket server = new ServerSocket(port);
        Messages messages = new Messages(new PrintWriter(System.out, true));
        messages.declareServerHasStarted(port);
        Executor executor = Executors.newCachedThreadPool();
        EchoServer echoServer = new EchoServer(server, messages, executor);

        echoServer.start();
    }

    private static void getPort(String... args) {
        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 1122;
        }
    }
}