package com.jpd.shop.common_files;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

@SuppressWarnings("finally")
public class Client {

    // public static final

    private final Socket SOCKET;
    private final MainFrame MAIN_FRAME_REF;
    private final ObjectInputStream INPUT_STREAM;
    private final ObjectOutputStream OUTPUT_STREAM;

    public Client(MainFrame mainFrameRef) throws UnknownHostException, IOException {
        SOCKET = new Socket(
                mainFrameRef.getConnectionConfigPanel().getIpv4Address(),
                mainFrameRef.getConnectionConfigPanel().getPortNumber());

        OUTPUT_STREAM = new ObjectOutputStream(SOCKET.getOutputStream());
        INPUT_STREAM = new ObjectInputStream(SOCKET.getInputStream());

        OUTPUT_STREAM.writeBoolean(mainFrameRef.isAdminApp());

        mainFrameRef.getTransparentPanel().setVisible(false);
        mainFrameRef.getLoginPanel().enableUsernameAndPasswordField(true);
        MAIN_FRAME_REF = mainFrameRef;
    }

    public Object makeARequestToServer(Object object) {
        try {
            OUTPUT_STREAM.writeObject(object);
            object = INPUT_STREAM.readObject();
        } catch (IOException | ClassNotFoundException e) {
            closeEverything();
        } finally {
            return object;
        }
    }

    private void closeEverything() {
        MAIN_FRAME_REF.setClient(null);
        MAIN_FRAME_REF.getTransparentPanel().setVisible(true);

        if (MAIN_FRAME_REF.getLoginPanel().isVisible()) {
            MAIN_FRAME_REF.getLoginPanel().enableUsernameAndPasswordField(false);
        }
        // TODO: add code
    }
}
