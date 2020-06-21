package org.yarkopro.activities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.yarkopro.user.User;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor()
public class Activity {
    Integer id;
    String name;
    String description;
    Timestamp time;
    User author;
    Boolean deleted;
    Boolean hidden;
}
