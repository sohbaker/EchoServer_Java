import org.junit.*;
import java.io.*;
import java.net.Socket;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class EchoServerTest {
    private EchoServer server;
    private int port = 3000;
    private final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();

    @Before
    public void setUpServer() {
        server = new EchoServer(port, new PrintStream(outputContent));
    }

    @After
    public void closeServerSocket() {
        server.stop();
    }

    @Test
    public void startsAServerSocket() {
        server.start();
        assertThat(outputContent.toString(), containsString("" + port));
    }

    @Test
    public void openServerSocketAcceptsAConnection() {
        server.start();
        try (Socket ableToConnect = new Socket("localhost", port)) {
            assertTrue("Accepts connection when server socket is listening", ableToConnect.isConnected());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    public void closedServerSocketDoesNotAllowAConnection() {
        server.start();
        server.stop();
        try {
            new Socket("localhost", port);
            fail("Cannot connect if server socket is not listening");
        } catch (Exception ex) {
            assertThat(ex.getMessage(), containsString("Connection refused"));
        }
    }
}
