import org.junit.Test;
import java.io.*;
import java.net.Socket;
import java.util.*;
import static org.junit.Assert.assertEquals;

public class FakeServerSocketTest {
    private EchoClient echoClient = new EchoClient();

    private String expectedOutputOne = "Tgitesting Client One\n";
    private String expectedOutputTwo = "Testing Client Twon";

    private Socket clientOne = echoClient.createWithInput(expectedOutputOne);
    private Socket clientTwo = echoClient.createWithInput(expectedOutputTwo);

    private List<Socket> multipleFakeClients = new ArrayList<>(Arrays.asList(clientOne, clientTwo));

    @Test
    public void returnsClientSocket() throws IOException {
        FakeServerSocket fakeServerSocket = new FakeServerSocket(multipleFakeClients);

        assertEquals(fakeServerSocket.accept(), clientOne);
        assertEquals(fakeServerSocket.accept(), clientTwo);
    }
}
