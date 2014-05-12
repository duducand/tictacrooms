package es.duducand.tic.tac.rooms.persistance.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import es.duducand.tic.tac.rooms.persistance.dao.util.PagedQuery;

public interface GenericDAO<T, PK extends Serializable> extends Serializable {
	T getById(PK id, boolean lock);
    List<T> getAll();
    List<T> getByExample(T exampleInstance);
    T save(T entity);
    T merge(T entity);
    void delete(T entity);
    
    List<T> findByNamedQueryAndNamedParam(Class<T> entityClass, String queryName, Map<String, ?> params);
    List<T> findByNamedQueryAndNamedParam(Class<T> entityClass, String queryName, String[] paramNames, Object[] values);
    List<T> findByPagedQuery(PagedQuery query);
    int countByPagedQuery(PagedQuery query);
}
