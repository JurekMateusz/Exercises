package exce.socket.server;

import java.io.BufferedReader;


public class ClientService implements Runnable {
    private BufferedReader reader;
    private Server server;

    public ClientService(Server server, BufferedReader reader) {
        this.server = server;
        this.reader = reader;
    }

    @Override
    public void run() {
        String message;
        try {
            while ((message = reader.readLine()) != null) {
                server.sendToAll(message);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(-2);
        }
    }
}
