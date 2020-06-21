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
                     "SELECT * FROM facilities")) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Facility facility = getFacility(rs);

                facilities.add(facility);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facilities;
    }

    private Facility getFacility(ResultSet rs) throws SQLException {
        Coords coords = Coords.of(rs.getString("latitude"), rs.getString("longitude"));
        return Facility.of(
                rs.getInt("id"),
                rs.getInt("type"),
                rs.getString("name"),
                rs.getString("description"),
                coords
                );
    }

    @Override
    public Facility findOne(Integer id) {
        Facility facility = new Facility();

        try (Connection conn = Database.connection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM facilities where id=" + id)) {

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                facility = getFacility(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn = Database.connection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM facility_activities " +
                             "join activities on activities.id=facility_activities.activity_id " +
                            "where facility_activities.facility_id=" + id)) {
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

            facility.activities = facilityActivities;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facility;
    }

    List<Facility> findNearest(String latitude, String longitude, double distance) {
        double lon = Math.toRadians(Double.parseDouble(longitude));
        double lat = Math.toRadians(Double.parseDouble(latitude));
        final int EARTH_RADIUS = 6371000;
        String query = "SELECT * FROM facilities " +
            "WHERE acos(sin("+lat+") * sin(facilities.latitude) + cos("+lat+") * cos(facilities.latitude) * " +
            "cos(facilities.longitude - ("+lon+ "))) * " + EARTH_RADIUS + " <= " + distance + ";";

        return null;
    }
}
