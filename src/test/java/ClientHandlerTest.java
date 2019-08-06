import org.junit.*;
import java.io.*;
import java.net.Socket;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class ClientHandlerTest {
    private ByteArrayInputStream inputStream = new ByteArrayInputStream(("testing 123").getBytes());
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private Runnable clientHandler;

    @Before
    public void setUpHandler() {
        PrintWriter serverOutput = new PrintWriter(new StringWriter());
        MessageHandler messageHandler = new MessageHandler(serverOutput, 2000);
        String exitWord = "bye";
        Socket fakeClientSocket = new FakeClientSocket(inputStream, outputStream);
        clientHandler = new ClientHandler(fakeClientSocket, messageHandler, exitWord);
    }


    @Test
    public void receivesAnEchoResponse() {
        clientHandler.run();
        assertThat(outputStream.toString(), containsString("testing 123"));
    }
}