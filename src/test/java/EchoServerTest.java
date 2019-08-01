import org.junit.*;
import java.io.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class EchoServerTest {
    private EchoServer server;
    private int port = 3000;
    private final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();

    @Before
    public void setUpServer() {
        server = new EchoServer(port, new PrintStream(outputContent));
    }

    @Test
    public void startsAServerSocket() {
        server.start(port);
        assertThat(outputContent.toString(), containsString("" + port));
    }

}
