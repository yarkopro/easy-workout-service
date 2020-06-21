package org.yarkopro.facilities;

import java.util.Arrays;
import java.util.Optional;

public enum FacilityType {
    PLAYGROUND,
    TRACK,
    GYM,
    FIELD,
    OUTLET;

    public static FacilityType getByValue(final String value) {
        return Arrays.stream(values()).filter(v -> v.toString().equals(value)).findFirst().orElseThrow(Error::new);
    }

    public static FacilityType getByIndex(final Integer index) {
        return Optional.ofNullable(values()[index]).orElseThrow(Error::new);
    }
}
