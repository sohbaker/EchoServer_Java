import org.junit.*;
import java.io.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ServerMessageHandlerTest {
    private ServerMessageHandler messageHandler;
    private int port = 4321;
    private StringWriter outputStream = new StringWriter();

    @Before
    public void createHandler() {
        PrintWriter output = new PrintWriter(outputStream);
        messageHandler = new ServerMessageHandler(output, port);
    }

    @Test
    public void confirmsServerHasStartedOnGivenPort() {
        messageHandler.confirmServerStarted();
        assertThat(outputStream.toString(), containsString("" + port));
    }

}