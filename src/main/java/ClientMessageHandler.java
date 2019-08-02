import java.io.*;

public class ClientMessageHandler {
    private BufferedReader input;
    private PrintWriter output;
    private String exitWord;

    public ClientMessageHandler(BufferedReader input, PrintWriter output, String exitWord) {
        this.input = input;
        this.output = output;
        this.exitWord = exitWord;
    }

    public void run() {
        while (true) {
            String message = readInput();
            System.out.println(message);
            if (noMoreMessagesFromClient(message)) {
                break;
            }
            sendOutput(message);
        }
    }

    private boolean noMoreMessagesFromClient(String message) {
       return message.equalsIgnoreCase(this.exitWord) || message == null;
    }

    public String readInput() {
        try {
            return input.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendOutput(String input) {
        output.println(input);
    }
}
