package org.yarkopro.tics;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.yarkopro.coords.Coords;

@Value
@AllArgsConstructor(staticName = "of")
public class Tick {
    Integer entityId;
    Coords coords;
    Integer type;
}
