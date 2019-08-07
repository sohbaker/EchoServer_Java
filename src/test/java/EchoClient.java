import java.io.*;
import java.net.Socket;

public class EchoClient {
    public Socket createWithInput(String fakeInput) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream((fakeInput).getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        return new FakeClientSocket(inputStream, outputStream);
    }
}
q