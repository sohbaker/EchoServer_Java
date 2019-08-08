import org.junit.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.Executor;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class EchoServerTest {
    private EchoClient echoClient = new EchoClient();
    private MessageHandler messageHandler = new MessageHandler(new PrintWriter(new StringWriter()));
    private Executor executor = new SynchronousExecutor();

    @Test
    public void echoesInputForMultipleClients() throws IOException {
        String expectedOutputOne = "Client One says hello";
        String expectedOutputTwo = "Client Two says hello";
        String expectedOutputThree = "Client Three says hello";

        Socket clientOne = echoClient.createWithInput(expectedOutputOne);
        Socket clientTwo = echoClient.createWithInput(expectedOutputTwo);
        Socket clientThree = echoClient.createWithInput(expectedOutputThree);

        List<Socket> multipleFakeClients = new ArrayList<>(Arrays.asList(clientOne, clientTwo, clientThree));

        ServerSocket fakeServerSocket = new FakeServerSocket(multipleFakeClients);
        EchoServer server = new EchoServer(fakeServerSocket, messageHandler, executor, "bye");

        server.listenForConnections();
        server.listenForConnections();
        server.listenForConnections();

        assertThat(clientOne.getOutputStream().toString(), containsString(expectedOutputOne));
        assertThat(clientTwo.getOutputStream().toString(), containsString(expectedOutputTwo));
        assertThat(clientThree.getOutputStream().toString(), containsString(expectedOutputThree));
    }
}