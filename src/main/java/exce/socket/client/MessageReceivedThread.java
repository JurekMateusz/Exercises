package exce.socket.client;

import javax.swing.*;
import java.io.BufferedReader;

public class MessageReceivedThread implements Runnable {
    private JTextArea messageReceivedArea;
    private BufferedReader bufferedReader;

    MessageReceivedThread(JTextArea messageReceivedArea, BufferedReader bufferedReader) {
        this.messageReceivedArea = messageReceivedArea;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run() {
        String message;
        try {
            while ((message = bufferedReader.readLine()) != null) {
                messageReceivedArea.append(message + "\n");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }
}
