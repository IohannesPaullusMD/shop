package com.jpd.shop.common_files;

import java.io.Serializable;

public record EmployeeLoginInfo(int id, String type, String username, String password) implements Serializable {

    /**
     * <p>
     * [1] = CASHIER
     * </p>
     * <p>
     * [2] = MANAGER
     * </p>
     * <p>
     * [3] = ADMIN
     * </p>
     */
    public static final String[] TYPES_OF_EMPLOYEE = { "", "CASHIER", "MANAGER", "ADMIN" };

    public static final int NO_ID_YET = -1;
}
