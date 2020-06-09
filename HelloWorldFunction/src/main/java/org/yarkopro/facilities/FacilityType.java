package org.yarkopro.facilities;

import java.util.Arrays;

public enum FacilityType {
    GYM,
    PLAYGROUND,
    TRACK,
    BAR,
    FIELD;

    public static FacilityType getByValue(final String value) {
        return Arrays.stream(values()).filter(v -> v.toString().equals(value)).findFirst().orElseThrow(Error::new);
    }
}
