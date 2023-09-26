package it.gdgpt.control.api;

import jakarta.persistence.EntityManager;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import java.sql.SQLIntegrityConstraintViolationException;

import static jakarta.ws.rs.core.Response.Status.CONFLICT;
import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

public interface HasDelete<T, K> extends HasPersistence<T> {

    Response delete(K id);

    default T doDelete(K id) {
        EntityManager em = getEm();
        final T entity = em.find(getEntityType(), id);
        if (entity == null) {
            throw new WebApplicationException(String.format(Constants.ENTITY_NOT_FOUND, getEntityType().getSimpleName(), id), NOT_FOUND);
        }

        try {
            em.remove(entity);
            em.flush();
        } catch (Exception e) {
            if (e.getCause() != null &&  e.getCause().getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                throw new WebApplicationException(String.format(Constants.ENTITY_NOT_REMOVEABLE, id), CONFLICT);
            } else {
                throw e;
            }
        }

        return entity;
    }
}