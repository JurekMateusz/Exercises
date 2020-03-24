package exce.socket.client;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.PrintWriter;

public class WindowListene implements WindowListener {
    PrintWriter printWriter;
    public WindowListene(PrintWriter printWriter){
        this.printWriter = printWriter;
    }
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("CLIENT EXIT");
        printWriter.println("CLIENT EXIT");
        try {
            wait(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
