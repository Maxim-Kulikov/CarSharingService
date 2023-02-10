package org.example.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T>{
    Optional<T> get(long id);
    List<T> getAll() throws SQLException;
    void save(T t);
    void update(T t, String[] params);
    void delete(T t);
    default <T> List<T> loadAllData(Class<T> type, Session session){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        return data;
    }
}
