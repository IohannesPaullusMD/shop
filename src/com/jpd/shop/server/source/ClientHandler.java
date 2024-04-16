package com.jpd.shop.server.source;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.jpd.shop.common_files.data_types.Client;
import com.jpd.shop.common_files.data_types.EmployeeLoginInfo;
import com.jpd.shop.common_files.data_types.ProductData;

public class ClientHandler implements Runnable {
    private final Socket SOCKET;
    private final ObjectOutputStream OUTPUT_STREAM;
    private final ObjectInputStream INPUT_STREAM;
    private final boolean IS_ADMIN_APP;

    public ClientHandler(Socket socket) throws IOException {
        SOCKET = socket;
        OUTPUT_STREAM = new ObjectOutputStream(socket.getOutputStream());
        INPUT_STREAM = new ObjectInputStream(socket.getInputStream());

        IS_ADMIN_APP = INPUT_STREAM.readBoolean();
    }

    @Override
    public void run() {

        while (!SOCKET.isClosed()) {
            try {
                Object object = INPUT_STREAM.readObject();
                handleRequest(object);
            } catch (IOException | ClassNotFoundException e) {
                closeEverything();
            }
        }
    }

    private void handleRequest(Object object) throws IOException {
        if (object instanceof Integer number) {
            readReceivedInt(number);
        } else if (object instanceof EmployeeLoginInfo employeeLoginInfo) {
            handleEmployeeLoginInfoRequest(employeeLoginInfo);
        } else if (object instanceof ProductData productData) {
            handleProductDataRequest(productData);
        } else if (object instanceof Object[] data) {
            handleNewTransaction(data);
        }
    }

    private void handleNewTransaction(Object[] data) throws IOException {
        int result = DatabaseQueries.newTransaction(data);
        OUTPUT_STREAM.writeObject(result);
    }

    private void handleEmployeeLoginInfoRequest(EmployeeLoginInfo employeeLoginInfo) throws IOException {
        if (employeeLoginInfo.type().equals("")) { // waray type it gin se-send kun na login la... basta...
            employeeLoginInfo = DatabaseQueries.checkIfEmployeeLoginInfoExists(employeeLoginInfo);

            if (employeeLoginInfo == null) {
                OUTPUT_STREAM.writeObject(-1); // wrong username or password
            } else if (!employeeLoginInfo.type().equals("CASHIER") && IS_ADMIN_APP) {
                OUTPUT_STREAM.writeObject(1); // has access
            } else {
                OUTPUT_STREAM.writeObject(0); // no access
            }

        } else
            switch (employeeLoginInfo.id()) {
                case EmployeeLoginInfo.NO_ID_YET:
                    break;

                case 0: // check kun may access

                    break;

                default:
                    DatabaseQueries.updateEmployeeLoginInfo(employeeLoginInfo);
                    OUTPUT_STREAM.writeObject(0);
                    break;
            }
    }

    private void handleProductDataRequest(ProductData productData) throws IOException {
        if (productData.id() == ProductData.NO_ID_YET) {
            DatabaseQueries.addNewProduct(productData);
        } else if (!productData.name().equals("")) {
            DatabaseQueries.updateProductDetails(productData);
        } else {
            DatabaseQueries.deleteProduct(productData.id());
        }

        readReceivedInt(11); // TODO: tanggala ini
    }

    private void readReceivedInt(int number) throws IOException {

        if (number < 0) {
            OUTPUT_STREAM.writeObject(DatabaseQueries.getTransactionDetails(-number));
            return;
        }

        switch (number / 10) {
            case 1: // get Products it request kun 1
                OUTPUT_STREAM.writeObject(DatabaseQueries.getProducts(number));
                break;

            case 2:
                OUTPUT_STREAM.writeObject(DatabaseQueries.getTransactionHistory());
                break;

            case 3:
                OUTPUT_STREAM.writeObject(DatabaseQueries.getEmployeeInfoList());
                break;

            // TODO: add code

            default:

                break;
        }
    }

    private void closeEverything() {
        try {
            if (!SOCKET.isClosed()) {
                SOCKET.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            System.gc();
        }
    }
}
