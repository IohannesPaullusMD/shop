package com.jpd.shop.common_files.data_types;

public record TransactionDetails(int ID_PK, int TRANSACTION_ID, String PRODUCT_NAME,
        float PRICE, int QUANTITY) {

    public Object[] toObjectArray() {
        Object[] objectToReturn = new Object[5];
        objectToReturn[0] = ID_PK;
        objectToReturn[1] = TRANSACTION_ID;
        objectToReturn[2] = PRODUCT_NAME;
        objectToReturn[3] = PRICE;
        objectToReturn[4] = QUANTITY;
        return objectToReturn;
    }

}
