package org.yarkopro.activities;

import org.yarkopro.coords.Coords;
import org.yarkopro.db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum DefaultActivityDao implements ActivityDao {
    INSTANCE;

    @Override
    public List<FacilityActivity> findAllFacilityActivityByFacilityId(Integer id) {
        final List<FacilityActivity> activities = new ArrayList<>();

        try (Connection conn = Database.connection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM facility_activity join activity on activity.id=facility_activity.activity_id where facility_id="+id)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                FacilityActivity activity = new FacilityActivity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("facility_id")
                );

                activities.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }

    @Override
    public StandaloneActivity findStandaloneActivityById(Integer id) {
        StandaloneActivity activity = new StandaloneActivity();

        try (Connection conn = Database.connection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM standalone_activity join activity on activity.id=standalone_activity.activity_id where activity_id="+id)) {

            ResultSet rs = ps.executeQuery();
            Coords coords = Coords.of(rs.getString("latitude"), rs.getString("longitude"));
                activity = new StandaloneActivity(
                        rs.getInt("id"),
                        coords,
                        rs.getString("description"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activity;
    }
}
