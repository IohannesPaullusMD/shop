package com.jpd.shop.common_files.data_types;

public record TransactionDetails(int ID, String PRODUCT_NAME,
        float PRICE, int QUANTITY) {

    public Object[] toObjectArray() {
        Object[] objectToReturn = new Object[4];
        objectToReturn[0] = ID;
        objectToReturn[1] = PRODUCT_NAME;
        objectToReturn[2] = PRICE;
        objectToReturn[3] = QUANTITY;
        return objectToReturn;
    }

}
