package org.yarkopro.activities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.yarkopro.coords.Coords;
import org.yarkopro.user.User;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class StandaloneActivity extends Activity {
    Coords coords;

    StandaloneActivity(Integer id, Coords coords, String description) {
        this.id = id;
        this.coords = coords;
        this.description = description;
    }

    public StandaloneActivity(Integer id,
                              String name,
                              String description,
                              Timestamp time,
                              User author,
                              Boolean deleted,
                              Boolean hidden,
                              Coords coords) {
        super(id, name, description,time, author, deleted, hidden);
        this.coords = coords;
    }
}
