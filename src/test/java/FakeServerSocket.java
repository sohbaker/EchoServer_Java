import java.io.IOException;
import java.net.*;
import java.util.*;

public class FakeServerSocket extends ServerSocket {
    private LinkedList<Socket> fakeClientSockets;
    private int acceptCalledCount = 0;

    FakeServerSocket(List<Socket> fakeClientSockets) throws IOException {
        this.fakeClientSockets = new LinkedList<>(fakeClientSockets);
    }

    @Override
    public Socket accept() {
        this.acceptCalledCount += 1;
        return fakeClientSockets.pollFirst();
    }

    public int noOfTimesAcceptWasCalled() {
        return this.acceptCalledCount;
    }
}
