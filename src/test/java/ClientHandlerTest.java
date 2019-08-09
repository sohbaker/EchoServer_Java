import org.junit.*;
import java.io.*;
import java.net.Socket;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class ClientHandlerTest {
    private String fakeInput = "testing 123";
    private ByteArrayInputStream inputStream = new ByteArrayInputStream((fakeInput).getBytes());
    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @Test
    public void receivesAnEchoResponse() {
        PrintWriter serverOutput = new PrintWriter(new StringWriter());
        Messages messages = new Messages(serverOutput);
        Socket fakeClientSocket = new FakeClientSocket(inputStream, outputStream);
        Runnable clientHandler = new ClientHandler(fakeClientSocket, messages, 1);

        clientHandler.run();

        assertThat(outputStream.toString(), containsString(fakeInput));
    }
}