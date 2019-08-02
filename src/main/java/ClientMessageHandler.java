import java.io.*;

public class ClientMessageHandler {
    private BufferedReader input;
    private PrintWriter output;

    public ClientMessageHandler(BufferedReader input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }

    public String readInput() {
        try {
            return input.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
