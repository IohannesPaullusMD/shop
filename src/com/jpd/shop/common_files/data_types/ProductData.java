package com.jpd.shop.common_files.data_types;

import java.io.Serializable;

/**
 * <p>
 * iton price kay naka x100(e.g., 10025).
 * </p>
 * need pa ini ig divide by 100.0
 * <p>
 * para makuha an actual price(100.25).
 * </p>
 */
public record ProductData(String name, int price, int stock,
        int category, byte[] image, int id) implements Serializable {

    public static final int NO_ID_YET = -1;

    public static final String[] categories = { "", "BURGER", "FRIES", "DRINKS" };
}
