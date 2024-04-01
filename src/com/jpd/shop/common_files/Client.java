package com.jpd.shop.common_files;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private final Socket SOCKET;
    private final MainFrame MAIN_FRAME_REF;

    public Client(MainFrame mainFrameRef) throws UnknownHostException, IOException {
        SOCKET = new Socket(
                "", 0);
        MAIN_FRAME_REF = mainFrameRef;
    }
}
