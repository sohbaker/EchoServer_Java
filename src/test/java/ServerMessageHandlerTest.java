import org.junit.*;
import java.io.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class ServerMessageHandlerTest {
    private int port = 4321;
    private StringWriter outputStream = new StringWriter();
    private ServerMessageHandler messageHandler;

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

    @Test
    public void confirmsClientConnectionIsAccepted() {
        messageHandler.confirmAcceptClientConnection();
        assertThat(outputStream.toString(), containsString("Accepted connection"));
    }

    @Test
    public void confirmsConnectionWithClientIsClosed() {
        messageHandler.confirmCloseClientConnection();
        assertThat(outputStream.toString(), containsString("Closing connection"));
    }

    @Test
    public void confirmServerClosed() {
        messageHandler.confirmCloseServer();
        assertThat(outputStream.toString(), containsString("Closing down server"));
    }

    @Test
    public void printsExceptionErrors() {
        String exceptionMessage = "exception thrown";
        IOException ioException = new IOException(exceptionMessage);
        messageHandler.printExceptionError(ioException);
        assertThat(outputStream.toString(), containsString(exceptionMessage));
    }
}