package org.yarkopro.facilities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.NonNull;
import org.yarkopro.activities.FacilityActivity;
import org.yarkopro.coords.Coords;

import java.util.List;

@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(staticName = "of")
public class Facility {

    @NonNull
    Integer id;
    @NonNull
    FacilityType type;
    @NonNull
    String name;
    @NonNull
    String description;
    @NonNull
    Coords coords;

    List<FacilityActivity> activities;
}
