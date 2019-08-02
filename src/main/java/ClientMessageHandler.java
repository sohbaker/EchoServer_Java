public class ClientMessageHandler {
    private String exitWord;

    public ClientMessageHandler(String exitWord) {
        this.exitWord = exitWord;
    }

    public boolean noMoreMessagesFromClient(String message) {
       return message.equalsIgnoreCase(this.exitWord) || message == null;
    }

    public String response(String incomingMessage) {
        return incomingMessage;
    }
}
