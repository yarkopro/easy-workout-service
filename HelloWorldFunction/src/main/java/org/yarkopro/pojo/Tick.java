package org.yarkopro.pojo;

import lombok.AllArgsConstructor;
import lombok.Value;

/**
 * Employee class/pojo.
 */
@Value
@AllArgsConstructor(staticName = "of")
public class Tick {

    Integer id;
    Integer entityId;
    String latitude;
    String longitude;
    Short type;
}
