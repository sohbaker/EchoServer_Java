import java.io.*;

public class MessageHandler {
    private PrintWriter output;

    public MessageHandler(PrintWriter output){
        this.output = output;
    }

    public void confirmServerStarted(int port) {
        output.println("--Server started on port: " + port + "--");
    }

    public void confirmAcceptClientConnection(int id) {
        output.println("--Accepted new connection: " + id + "--");
    }

    public void confirmCloseClientConnection(int id) {
        output.println("--Closing connection with client: " + id + "--");
    }

    public void printExceptionError(IOException errorMessage) {
        output.println(errorMessage);
    }
}
