package org.yarkopro.tics;

import java.util.Arrays;

public enum TickType {
    FACILITY,
    STANDALONE_ACTIVITY;

    public static TickType getByValue(final String value) {
        return Arrays.stream(values()).filter(v -> v.toString().equals(value)).findFirst().orElseThrow(Error::new);
    }
}
