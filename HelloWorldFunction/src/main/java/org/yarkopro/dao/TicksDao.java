package org.yarkopro.dao;


import org.yarkopro.pojo.Tick;

import java.util.List;

/**
 * Employee DAO interface.
 */
public interface TicksDao {

    List<Tick> findAll();
}
