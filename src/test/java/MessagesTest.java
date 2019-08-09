import org.junit.*;
import java.io.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class MessageHandlerTest {
    private StringWriter outputStream = new StringWriter();
    private MessageHandler messageHandler;
    private int clientId = 1;

    @Before
    public void createHandler() {
        PrintWriter output = new PrintWriter(outputStream);
        messageHandler = new MessageHandler(output);
    }

    @Test
    public void declaresServerHasStartedOnGivenPort() {
        int port = 1000;
        messageHandler.declareServerHasStarted(port);

        assertThat(outputStream.toString(), containsString("" + port));
    }

    @Test
    public void declaresClientConnectionIsAccepted() {
        messageHandler.declareAcceptedClientConnection(clientId);

        assertThat(outputStream.toString(), containsString(Integer.toString(clientId)));
    }

    @Test
    public void declaresConnectionWithClientIsClosed() {
        messageHandler.declareClosingClientConnection(clientId);

        assertThat(outputStream.toString(), containsString(Integer.toString(clientId)));
    }

    @Test
    public void showsIOExceptionErrors() {
        String exceptionMessage = "exception thrown";
        IOException ioException = new IOException(exceptionMessage);
        messageHandler.showIOException(ioException);

        assertThat(outputStream.toString(), containsString(exceptionMessage));
    }
}