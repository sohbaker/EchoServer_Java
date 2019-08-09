import org.junit.*;
import java.io.*;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class MessagesTest {
    private StringWriter outputStream = new StringWriter();
    private Messages messages;
    private int clientId = 1;

    @Before
    public void createHandler() {
        PrintWriter output = new PrintWriter(outputStream);
        messages = new Messages(output);
    }

    @Test
    public void declaresServerHasStartedOnGivenPort() {
        int port = 1000;
        messages.declareServerHasStarted(port);

        assertThat(outputStream.toString(), containsString("" + port));
    }

    @Test
    public void declaresClientConnectionIsAccepted() {
        messages.declareAcceptedClientConnection(clientId);

        assertThat(outputStream.toString(), containsString(Integer.toString(clientId)));
    }

    @Test
    public void declaresConnectionWithClientIsClosed() {
        messages.declareClosingClientConnection(clientId);

        assertThat(outputStream.toString(), containsString(Integer.toString(clientId)));
    }

    @Test
    public void showsIOExceptionErrors() {
        String exceptionMessage = "exception thrown";
        IOException ioException = new IOException(exceptionMessage);
        messages.showIOException(ioException);

        assertThat(outputStream.toString(), containsString(exceptionMessage));
    }
}