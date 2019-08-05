import java.io.IOException;
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

    public void confirmCloseClientConnection() {
        output.println("--Closing connection with client--");
    }

    public void confirmCloseServer() {
        output.println("--Closing down server--");
    }

    public void printExceptionError(IOException errorMessage) {
        output.println(errorMessage);
    }
}
