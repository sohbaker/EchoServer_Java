import org.junit.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.Executor;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class EchoServerTest {
    private FakeClientCreator fakeClientCreator = new FakeClientCreator();
    private Messages messages = new Messages(new PrintWriter(new StringWriter()));
    private Executor executor = new SynchronousExecutor();

    @Test
    public void echoesInputForMultipleClients() throws IOException {
        String expectedOutputOne = "Client One says hello";
        String expectedOutputTwo = "Client Two says hello";

        Socket clientOne = fakeClientCreator.createWithInput(expectedOutputOne);
        Socket clientTwo = fakeClientCreator.createWithInput(expectedOutputTwo);

        LinkedList<Socket> multipleFakeClients = new LinkedList<>(Arrays.asList(clientOne, clientTwo));

        ServerSocket fakeServerSocket = new FakeServerSocket(multipleFakeClients);
        EchoServer server = new EchoServer(fakeServerSocket, messages, executor);

        server.listenForConnections();
        server.listenForConnections();

        assertThat(clientOne.getOutputStream().toString(), containsString(expectedOutputOne));
        assertThat(clientTwo.getOutputStream().toString(), containsString(expectedOutputTwo));
    }
}