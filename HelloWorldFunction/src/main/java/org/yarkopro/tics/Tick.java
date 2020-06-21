package org.yarkopro.tics;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Employee class/pojo.
 */
@Value
@AllArgsConstructor(staticName = "of")
public class Tick {

    Integer entityId;
    String latitude;
    String longitude;
    Integer type;
}
