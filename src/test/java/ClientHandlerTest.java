import org.junit.*;
import java.io.*;
import java.net.Socket;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class ClientHandlerTest {
    private ByteArrayInputStream inputStream = new ByteArrayInputStream(("testing 123").getBytes());
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private Runnable clientHandler;

    @Test
    public void receivesAnEchoResponse() {
        PrintWriter serverOutput = new PrintWriter(new StringWriter());
        MessageHandler messageHandler = new MessageHandler(serverOutput);
        Socket fakeClientSocket = new FakeClientSocket(inputStream, outputStream);
        String exitWord = "bye";
        int clientId = 1;
        clientHandler = new ClientHandler(fakeClientSocket, messageHandler, exitWord, clientId);

        clientHandler.run();
        assertThat(outputStream.toString(), containsString("testing 123"));
    }
}