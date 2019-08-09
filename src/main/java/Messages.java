import java.io.*;

public class MessageHandler {
    private PrintWriter output;

    public MessageHandler(PrintWriter output){
        this.output = output;
    }

    public void declareServerHasStarted(int port) {
        output.println("--Server started on port: " + port + "--");
    }

    public void declareAcceptedClientConnection(int id) {
        output.println("--Accepted new connection: " + id + "--");
    }

    public void declareClosingClientConnection(int id) {
        output.println("--Closing connection with client: " + id + "--");
    }

    public void showIOException(IOException errorMessage) {
        output.println(errorMessage);
    }
}