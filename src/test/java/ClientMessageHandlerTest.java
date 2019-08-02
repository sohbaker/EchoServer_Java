import org.junit.*;
import java.io.*;
import static org.junit.Assert.*;

public class ClientMessageHandlerTest {
    private String fakeInput = "hello";
    private StringReader inputStream = new StringReader(fakeInput);
    private StringWriter outputStream = new StringWriter();
    private ClientMessageHandler messageHandler;

    @Before
    public void createHandler() {
        BufferedReader input = new BufferedReader(inputStream);
        PrintWriter output = new PrintWriter(outputStream);
        messageHandler = new ClientMessageHandler(input, output);
    }

    @Test
    public void receivesAnInput() {
        assertEquals(messageHandler.readInput(), fakeInput);
    }

    @Test
    public void outputsTheClientMessage() {
        messageHandler.sendOutput(fakeInput);
        assertEquals(outputStream.toString(), fakeInput+"\n");
    }
}
