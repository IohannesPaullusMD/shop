package com.jpd.shop.server.source;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        MainFrame.jLabel1.setText(Boolean.toString(this.serverSocket.isClosed()));
    }

    //
    @Override
    public void run() {
        // MainFrame.jLabel1.setText(Boolean.toString(this.serverSocket.isClosed()));
        while (!serverSocket.isClosed()) {
            try {
                MainFrame.jLabel1.setText("running");
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                Thread thread = new Thread(clientHandler);
                thread.start();
            } catch (IOException e) {
                MainFrame.jLabel1.setText(e.getMessage());
                closeServerSocket();
            }
        }
        // MainFrame.jLabel1.setText("End");
    }

    void closeServerSocket() {

        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            
            MainFrame.jLabel1.setText("server closed");
        } catch (IOException e) {
        } finally {
            MainFrame.jLabel1.setText("error");
        }

    }
}
