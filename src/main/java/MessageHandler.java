import java.io.IOException;
import java.io.PrintWriter;

public class MessageHandler {
    private PrintWriter output;

    public MessageHandler(PrintWriter output){
        this.output = output;
    }

    public void confirmServerStarted(int port) {
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
