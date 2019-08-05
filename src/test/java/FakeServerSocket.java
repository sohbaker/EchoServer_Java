import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FakeServerSocket extends ServerSocket {
    private boolean acceptHasBeenCalled = false;

    FakeServerSocket() throws IOException {
    }

    @Override
    public Socket accept() {
        this.acceptHasBeenCalled = true;
        return new FakeClientSocket();
    }

    public boolean wasAcceptCalled() {
        return this.acceptHasBeenCalled;
    }
}
