import org.junit.*;
import java.io.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class EchoServerTest {
    private EchoServer server;
    private MessageHandler messageHandler;
    private String fakeInput = "hello";
    private ByteArrayInputStream clientInputStream = new ByteArrayInputStream((fakeInput).getBytes());
    private ByteArrayOutputStream clientOutputStream = new ByteArrayOutputStream();
    private String exitWord = "bye";
    private FakeServerSocket fakeServerSocket;

    @Before
    public void setUp() throws IOException {
        int port = 3000;
        PrintWriter serverOutput = new PrintWriter(new StringWriter());
        messageHandler = new MessageHandler(serverOutput, port);
        FakeClientSocket fakeClientSocket = new FakeClientSocket(clientInputStream, clientOutputStream);
        fakeServerSocket = new FakeServerSocket(fakeClientSocket);
        server = new EchoServer(messageHandler, fakeServerSocket, exitWord);
    }

    @Test
    public void openServerSocketAcceptsAConnection() {
        server.listen();
        assertTrue(fakeServerSocket.wasAcceptCalled());
    }

    @Test
    public void echoesAMessageFromTheClientBackToTheClient() {
        server.listen();
        assertThat(clientOutputStream.toString(), containsString("hello"));
    }

    @Test
    public void socketClosesWhenClientEntersExitWord() throws IOException {
        ByteArrayInputStream clientInput = new ByteArrayInputStream(("hello\nbye").getBytes());
        FakeClientSocket fakeClientSocket = new FakeClientSocket(clientInput, clientOutputStream);
        FakeServerSocket fakeServerSocket = new FakeServerSocket(fakeClientSocket);
        server = new EchoServer(messageHandler, fakeServerSocket, exitWord);

        server.listen();
        assertTrue(fakeServerSocket.isClosed());
    }
}
