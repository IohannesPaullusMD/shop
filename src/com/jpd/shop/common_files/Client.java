package com.jpd.shop.common_files;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;

@SuppressWarnings("finally")
public class Client implements Runnable {

    public static final int GET_BURGER_PRODUCTS = 11; // TODO: change to byte or short
    public static final int GET_FRIES_PRODUCTS = 12;
    public static final int GET_DRINKS_PRODUCTS = 13;

    private static Client client;

    public static final boolean createInstance() {
        try {
            client = new Client();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.gc();
            return (client != null);
        }
    }

    public static final Client getInstance() {
        if (client == null) {
            createInstance();
        }

        return client;
    }

    public static final void close() {
        if (client != null) {
            return;
        }

        MainFrame.getInstance().getTransparentPanel().setVisible(true);
        MainFrame.getInstance().getLayeredPane().repaint();

        MainFrame.getInstance().getConnectionButton().setIcon(
                MainFrame.getInstance().getConnectionConfigPanel().getRedWifiIcon());
        MainFrame.getInstance().getConnectionConfigPanel().getButton().setText("Connect");

        if (client.SOCKET != null) {
            try {
                client.SOCKET.close();
            } catch (IOException e) {
            }
        }

        client = null;
    }

    private final Socket SOCKET;
    private final ObjectInputStream INPUT_STREAM;
    private final ObjectOutputStream OUTPUT_STREAM;

    private Client() throws UnknownHostException, IOException {
        SOCKET = new Socket(
                MainFrame.getInstance().getConnectionConfigPanel().getIpv4Address(),
                MainFrame.getInstance().getConnectionConfigPanel().getPortNumber());

        OUTPUT_STREAM = new ObjectOutputStream(SOCKET.getOutputStream());
        INPUT_STREAM = new ObjectInputStream(SOCKET.getInputStream());

        OUTPUT_STREAM.writeBoolean(MainFrame.getInstance().IS_ADMIN_APP);
        OUTPUT_STREAM.flush();

        MainFrame.getInstance().getTransparentPanel().setVisible(false);

        if (!LoginPanel.hasNoInstance()) {
            LoginPanel.getInstance().enableUsernameAndPasswordField(true);
        }

        new Thread(this).start();
    }

    public Object makeARequestToServer(Object object) {
        try {
            OUTPUT_STREAM.writeObject(object);
            object = INPUT_STREAM.readObject();
        } catch (IOException | ClassNotFoundException e) {
            close();
        } finally {
            return object;
        }
    }

    // private void close() {
    // MAIN_FRAME_REF.setClient(null);
    // MAIN_FRAME_REF.getTransparentPanel().setVisible(true);

    // if (MAIN_FRAME_REF.getLoginPanel().isVisible()) {
    // MAIN_FRAME_REF.getLoginPanel().enableUsernameAndPasswordField(false);
    // }

    // MAIN_FRAME_REF.getConnectionButton().setIcon(
    // MAIN_FRAME_REF.getConnectionConfigPanel().getRedWifiIcon());
    // MAIN_FRAME_REF.getConnectionConfigPanel().getButton().setText("Connect");
    // // TODO: add code
    // }

    @Override
    public void run() {
        try {
            INPUT_STREAM.readBoolean();
        } catch (IOException e) {
            close();
        }
    }
}
