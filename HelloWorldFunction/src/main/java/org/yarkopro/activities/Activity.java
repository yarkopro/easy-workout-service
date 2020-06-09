package org.yarkopro.activities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class Activity {
    Integer id;
    String name;
    String description;
    Timestamp timestamp;
}
