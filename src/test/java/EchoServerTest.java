import org.junit.*;
import java.io.*;
import java.net.Socket;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
    public void serverSocketAcceptsAConnection() {
        server.start();
        try (Socket ableToConnect = new Socket("localhost", port)) {
            assertTrue("Accepts connection when server is listening", ableToConnect.isConnected());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
