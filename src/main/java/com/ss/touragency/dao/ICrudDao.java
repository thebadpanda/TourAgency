package com.ss.touragency.dao;

import java.sql.SQLException;
import java.util.List;

public interface ICrudDao<SomeEntity> {

    /**
     * Query for insert entity to database
     *
     * @param entity Object from package entity
     */
    //create
    void insert(SomeEntity entity) throws SQLException;


    /**
     * Query for select all entities from database
     *
     * @return List of entities
     * @throws SQLException
     */
    //read
    List<SomeEntity> selectAll();

    /**
     * Search entity by Id from database
     *
     * @param id Id entity in database
     * @return some entity found by Id
     * @throws SQLException
     */
    SomeEntity selectById(Long id) throws SQLException;

//    /**
//     * Search entity by name from database
//     *
//     * @param name name of entity in database
//     * @return some entity found by Id
//     * @throws SQLException
//     */
//    SomeEntity selectByName(String name) throws SQLException;

    /**
     *
     * Update entity by Id
     *
     * @param entity Entity to update
     * @param id entity id in database
     */
    // update
    void updateById(SomeEntity entity, Long id);

    /**
     *
     * Delete entity by id
     *
     *
     * @param id entity id in database
     */
    // delete
    void deleteById(Long id);

}
