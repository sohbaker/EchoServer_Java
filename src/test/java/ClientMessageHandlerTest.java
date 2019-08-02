import org.junit.*;
import java.io.*;
import static org.junit.Assert.*;

public class ClientMessageHandlerTest {
    private String fakeInput = "hello";
    private StringReader inputStream = new StringReader(fakeInput);
    private StringWriter outputStream = new StringWriter();
    private String exitWord = "bye";
    private ClientMessageHandler messageHandler;

    @Before
    public void createHandler() {
        BufferedReader input = new BufferedReader(inputStream);
        PrintWriter output = new PrintWriter(outputStream);
        messageHandler = new ClientMessageHandler(input, output, exitWord);
    }

    @Test
    public void receivesAnInput() {
        assertEquals(fakeInput, messageHandler.readInput());
    }

    @Test
    public void outputsTheClientMessage() {
        messageHandler.sendOutput(fakeInput);
        assertEquals(fakeInput+"\n", outputStream.toString());
    }

    @Test
    public void readsInputUntilClientSaysBye() {
        String multipleFakeInput = "hello\nhey\nhi\nbye";
        BufferedReader input = new BufferedReader(new StringReader(multipleFakeInput));
        StringWriter outputStream = new StringWriter();
        PrintWriter output = new PrintWriter(outputStream);
        messageHandler = new ClientMessageHandler(input, output, exitWord);

        messageHandler.run();
        assertEquals("hello\nhey\nhi\n", outputStream.toString());
    }
}
