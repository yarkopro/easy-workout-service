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
                     "SELECT * FROM facility_activities join activities on activities.id=facility_activities.activity_id where facility_id="+id)) {

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
                     "SELECT * FROM standalone_activities join activities on activities.id=standalone_activities.activity_id where activity_id="+id)) {

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Coords coords = Coords.of(rs.getString("latitude"), rs.getString("longitude"));
                activity = new StandaloneActivity(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("decription"),
                    rs.getTimestamp("time"),
                    null,
                    rs.getBoolean("deleted"),
                    rs.getBoolean("hidden"),
                    coords
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activity;
    }
}
