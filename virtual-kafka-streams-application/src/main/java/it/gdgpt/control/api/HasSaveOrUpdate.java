package it.gdgpt.control.api;


import jakarta.persistence.EntityManager;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.PersistenceUnitUtil;
import jakarta.ws.rs.WebApplicationException;

import static jakarta.ws.rs.core.Response.Status.CONFLICT;

public interface HasSaveOrUpdate<T> extends HasPersistence<T> {

    default T saveOrUpdate(T entity){
        return doSaveOrUpdate(entity);
    }

    default T doSaveOrUpdate(T entity) {

        final EntityManager em = getEm();
        final PersistenceUnitUtil persistenceUnitUtil = em.getEntityManagerFactory().getPersistenceUnitUtil();
        final Object id = persistenceUnitUtil.getIdentifier(entity);

        if (id == null) {
            em.persist(entity);
        } else {
            try {
                entity = em.merge(entity);
                em.flush();
            } catch (OptimisticLockException e) {
                String msg = String.format(Constants.OPT_LOCK, entity.getClass().getSimpleName(), id);
                throw new WebApplicationException(msg, CONFLICT);
            }
        }

        return entity;
    }
}