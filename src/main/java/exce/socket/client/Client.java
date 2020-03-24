package exce.socket.client;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

/**
 * i took pattern form book Sierra-k Java
 *   change ip address to yours
 */
public class Client {
    JTextArea messageReceivedArea;
    JTextField messageSendField;
    PrintWriter messagePrintWriter;
    Socket socket;
    BufferedReader messageReader;

    public void start() {
        JFrame frame = new JFrame("Simple Client");
        JPanel panel = new JPanel();
        configurateConection();

        messageReceivedArea = new JTextArea(15, 50);
        messageReceivedArea.setLineWrap(true);
        messageReceivedArea.setWrapStyleWord(true);
        messageReceivedArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(messageReceivedArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        messageSendField = new JTextField(20);

        JButton messageButton = new JButton("SEND");
        messageButton.addActionListener(new ButtonSendListener(messagePrintWriter, messageSendField));

        panel.add(messageReceivedArea);
        panel.add(messageSendField);
        panel.add(messageButton);

        Thread threadMessageReceived = new Thread(new MessageReceivedThread(messageReceivedArea, messageReader));
        threadMessageReceived.start();

//        WindowListene windowListene = new WindowListene(messagePrintWriter);
//        frame.addWindowListener(windowListene);
//        frame.addKeyListener();
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(550, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void configurateConection() {
        try {
            socket = new Socket("---", 3333);

            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            messageReader = new BufferedReader(inputStreamReader);

            messagePrintWriter = new PrintWriter(socket.getOutputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }


    public static void main(String[] args) {
        new Client().start();
    }
}
