package exce.socket.client;

import javax.swing.*;
import java.awt.event.*;
import java.io.PrintWriter;

public class ButtonSendListener implements ActionListener {
    private PrintWriter messagePrintWriter;
    private JTextField messageSendField;

    ButtonSendListener(PrintWriter messagePrintWriter, JTextField messageSendField) {
        this.messagePrintWriter = messagePrintWriter;
        this.messageSendField = messageSendField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String message;
        try {
            message = messageSendField.getText();
            if(!message.isEmpty()){
                messagePrintWriter.println(message);
                messagePrintWriter.flush();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
        messageSendField.setText("");
        messageSendField.requestFocus();
    }
}