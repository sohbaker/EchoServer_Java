import java.io.IOException;
import java.net.*;

public class FakeServerSocket extends ServerSocket {
    private Socket fakeClientSocket;
    private boolean wasAcceptCalled = false;

    FakeServerSocket(Socket fakeClientSocket) throws IOException {
        this.fakeClientSocket = fakeClientSocket;
    }

    @Override
    public Socket accept() {
        this.wasAcceptCalled = true;
        return fakeClientSocket;
    }

    public boolean wasAcceptCalled() {
        return this.wasAcceptCalled;
    }
}
