package org.yarkopro.activities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.yarkopro.coords.Coords;
import org.yarkopro.user.User;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor()
public class FacilityActivity extends Activity {
    Integer facilityId;

    public FacilityActivity(Integer id, String name, Integer facilityId) {
        this.id = id;
        this.name = name;
        this.facilityId = facilityId;
    }

    public FacilityActivity(Integer id,
                            String name,
                            String description,
                            Timestamp time,
                            User author,
                            Boolean deleted,
                            Boolean hidden) {
        super(id, name, description, time, author, deleted, hidden);
    }
}
