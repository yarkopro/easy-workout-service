package org.yarkopro.facilities;

import org.yarkopro.activities.FacilityActivity;
import org.yarkopro.coords.Coords;
import org.yarkopro.db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum DefaultFacilityDao implements FacilityDao {
    INSTANCE;

    @Override
    public List<Facility> findAll() {
        final List<Facility> facilities = new ArrayList<>();

        try (Connection conn = Database.connection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT * FROM facility")) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Coords coords = Coords.of(rs.getString("latitude"), rs.getString("longitude"));
                Facility facility = Facility.of(
                        rs.getInt("id"),
                        FacilityType.getByValue(rs.getString("type")),
                        rs.getString("name"),
                        rs.getString("description"),
                        coords
                        );

                facilities.add(facility);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facilities;
    }

    @Override
    public Facility findOne(Integer id) {
        Facility facility = new Facility();

        try (Connection conn = Database.connection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM facility where id=" + id)) {

            ResultSet rs = ps.executeQuery();

                Coords coords = Coords.of(rs.getString("latitude"), rs.getString("longitude"));
                facility = Facility.of(
                        rs.getInt("id"),
                        FacilityType.getByValue(rs.getString("type")),
                        rs.getString("name"),
                        rs.getString("description"),
                        coords
                );

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = Database.connection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM facility_activity " +
                             "join activity on activity.id=facility_activity.activity_id " +
                            "where facility_activity.facility_id=" + id)) {
            List<FacilityActivity> facilityActivities = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                FacilityActivity activity = new FacilityActivity(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("facility_id")
                );

                facilityActivities.add(activity);
            }

            facility.activities.addAll(facilityActivities);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facility;
    }
}
