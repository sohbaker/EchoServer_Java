import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.Socket;

public class MockClientSocket extends Socket {
    public MockClientSocket() {}

    public InputStream getInputStream() {
        return new ByteArrayInputStream("GET / HTTP/1.1\nHost: loccalhost".getBytes());
    }
}