package com.jpd.shop.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

import com.jpd.shop.server.source.Server;

public class ServerEntryPoint {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(55555)) {
            Server server = new Server(serverSocket);
            new Thread(server).start();

            checkIfServerShouldNowClose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkIfServerShouldNowClose() {
        Scanner scan = new Scanner(System.in);
        String status = "";

        do {
            System.out.print("Enter 0 to close the server: ");
            status = scan.nextLine();
        } while (!status.equals("0"));
        scan.close();
        System.exit(0);
    }
}
