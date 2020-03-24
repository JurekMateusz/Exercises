package exce.socket.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ArrayList<PrintWriter> outputStream;
    private ServerSocket serverSocket;

    private synchronized void sayHi(PrintWriter printWriter) {
        printWriter.println("Welcome on Mat server");
        printWriter.flush();
    }

    public synchronized void sendToAll(String message) {
        for (PrintWriter writer : outputStream) {
            writer.println(message);
            writer.flush();
        }
    }

    public void start() {
        outputStream = new ArrayList<>();
        try {
            serverSocket = new ServerSocket(3333);
            while (true) {
                Socket socket = serverSocket.accept();

                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                outputStream.add(printWriter);

                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                sayHi(printWriter);

                Thread inputService = new Thread(new ClientService(this, bufferedReader));
                inputService.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static void main(String[] args) {
        new Server().start();
    }
}