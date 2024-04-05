package com.jpd.shop.common_files.functional_interfaces;

import javax.swing.JLabel;

@FunctionalInterface
public interface FunctionWithFourIntegerParametersButReturnsJLabel {
    JLabel call(int x, int y, int w, int h);
}
