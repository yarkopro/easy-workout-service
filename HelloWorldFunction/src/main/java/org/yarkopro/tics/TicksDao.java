package org.yarkopro.tics;


import java.util.List;

/**
 * Employee DAO interface.
 */
public interface TicksDao {

    List<Tick> findAll();
}
