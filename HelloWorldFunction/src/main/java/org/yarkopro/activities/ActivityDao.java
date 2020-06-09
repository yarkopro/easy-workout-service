package org.yarkopro.activities;

import java.util.List;

public interface ActivityDao {
    List<FacilityActivity> findAllFacilityActivityByFacilityId(Integer id);
    StandaloneActivity findStandaloneActivityById(Integer id);
}
