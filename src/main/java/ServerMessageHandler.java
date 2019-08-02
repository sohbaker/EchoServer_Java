import java.io.PrintWriter;

public class ServerMessageHandler {
    private PrintWriter output;
    private int port;

    public ServerMessageHandler(PrintWriter output, int port){
        this.output = output;
        this.port = port;
    }

    public void confirmServerStarted() {
        output.println("--Server started on port: " + port + "--");
    }

    public void confirmAcceptClientConnection() {
        output.println("--Accepted connection--");
    }
}
