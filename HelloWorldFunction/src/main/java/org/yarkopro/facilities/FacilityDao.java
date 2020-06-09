package org.yarkopro.facilities;

import java.util.List;

public interface FacilityDao {
    List<Facility> findAll();

    Facility findOne(Integer id);
}
