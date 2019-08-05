import org.junit.*;
import java.io.*;
import static org.junit.Assert.*;

public class EchoServerTest {
    private EchoServer server;
    private int port = 3000;
    private FakeServerSocket fakeServerSocket;
    private MessageHandler messageHandler;
    private StringWriter outputStream = new StringWriter();
    private String exitWord = "bye";

    @Before
    public void setUp() throws IOException {
        PrintWriter output = new PrintWriter(outputStream);
        messageHandler = new MessageHandler(output, port);
        fakeServerSocket = new FakeServerSocket();
        server = new EchoServer(messageHandler, fakeServerSocket, exitWord);
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
