package org.yarkopro.activities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.yarkopro.coords.Coords;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class FacilityActivity extends Activity {
    Integer facilityId;
    Coords coords;

    public FacilityActivity(Integer id, String name, Integer facilityId) {
        this.id = id;
        this.name = name;
        this.facilityId = facilityId;
    }
}
