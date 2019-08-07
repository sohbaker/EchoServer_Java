import java.io.IOException;
import java.net.*;
import java.util.*;

public class FakeServerSocket extends ServerSocket {
    private LinkedList<Socket> fakeClientSockets;

    FakeServerSocket(List<Socket> fakeClientSockets) throws IOException {
        this.fakeClientSockets = new LinkedList<>(fakeClientSockets);
    }

    @Override
    public Socket accept() {
        return fakeClientSockets.pollFirst();
    }
}
