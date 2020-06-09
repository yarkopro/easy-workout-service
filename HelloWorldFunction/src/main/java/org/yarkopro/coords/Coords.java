package org.yarkopro.coords;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Coords {
    String latitude;
    String longitude;
}
