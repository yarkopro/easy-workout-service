package org.yarkopro.activities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.yarkopro.coords.Coords;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class StandaloneActivity extends Activity {
    Coords coords;

    StandaloneActivity(Integer id, Coords coords, String description) {
        this.id = id;
        this.coords = coords;
        this.description = description;
    }
}
