package org.yarkopro.tics;

import org.yarkopro.db.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum DefaultTicksDao implements TicksDao {
    INSTANCE;

    @Override
    public List<Tick> findAll() {
        final List<Tick> ticks = new ArrayList<>();

        try (Connection conn = Database.connection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM ticks")) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tick tick = Tick.of(
                    rs.getInt("entity_id"),
                    rs.getString("latitude"),
                    rs.getString("longitude"),
                    rs.getInt("tick_type"));

                ticks.add(tick);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticks;
    }
}
