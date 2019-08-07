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
    public void confirmsServerHasStartedOnGivenPort() {
        int port = 1000;
        messageHandler.confirmServerStarted(port);
        assertThat(outputStream.toString(), containsString("" + port));
    }

    @Test
    public void confirmsClientConnectionIsAccepted() {
        messageHandler.confirmAcceptClientConnection(clientId);
        assertThat(outputStream.toString(), containsString("1"));
    }

    @Test
    public void confirmsConnectionWithClientIsClosed() {
        messageHandler.confirmCloseClientConnection(clientId);
        assertThat(outputStream.toString(), containsString("1"));
    }

    @Test
    public void printsIOExceptionErrors() {
        String exceptionMessage = "exception thrown";
        IOException ioException = new IOException(exceptionMessage);
        messageHandler.printExceptionError(ioException);
        assertThat(outputStream.toString(), containsString(exceptionMessage));
    }
}