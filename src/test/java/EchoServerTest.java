import org.junit.*;
import java.io.*;
import static org.junit.Assert.*;

public class EchoServerTest {
    private EchoServer server;
    private int port = 3000;
    private FakeServerSocket fakeServerSocket;
    private ServerMessageHandler serverMessageHandler;
    private StringWriter outputStream = new StringWriter();

    @Before
    public void setUp() throws IOException {
        PrintWriter output = new PrintWriter(outputStream);
        serverMessageHandler = new ServerMessageHandler(output, port);
        fakeServerSocket = new FakeServerSocket(port);
        server = new EchoServer(port, serverMessageHandler, fakeServerSocket);
    }

    @After
    public void closeServerSocket() {
        server.stop();
    }

    @Test
    public void openServerSocketAcceptsAConnection() {
        server.listen();
        assertTrue(fakeServerSocket.wasAcceptCalled());
    }

    @Test
    public void echoesAMessageFromTheClientBackToTheClient() {
    }

    @Test
    public void receivesARequestFromAClientSocket() {
        MockClientSocket mockClientSocket = new MockClientSocket();
        String request = server.acceptRequestFromAClient(mockClientSocket);
        assertEquals("GET / HTTP/1.1", request);
    }
}
