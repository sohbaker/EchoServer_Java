import org.junit.Test;
import java.io.*;
import java.net.Socket;
import java.util.*;
import static org.junit.Assert.assertEquals;

public class FakeServerSocketTest {
    private EchoClient echoClient = new EchoClient();
    private Socket clientOne = echoClient.createWithInput("Testing Client One\n");
    private Socket clientTwo = echoClient.createWithInput("Testing Client Two\n");
    private List<Socket> multipleFakeClients = new ArrayList<>(Arrays.asList(clientOne, clientTwo));

    @Test
    public void returnsAClientSocketFromListOfFakeClients() throws IOException {
        FakeServerSocket fakeServerSocket = new FakeServerSocket(multipleFakeClients);

        assertEquals(fakeServerSocket.accept(), clientOne);
        assertEquals(fakeServerSocket.accept(), clientTwo);
    }
}
