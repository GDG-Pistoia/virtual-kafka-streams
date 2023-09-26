package it.gdgpt.control.api;

import jakarta.persistence.Query;
import java.util.List;

public interface HasFindAll<T> extends HasPersistence<T> {

    default List<T> findAll(){
        return getEm().createQuery("SELECT E FROM " + getEntityType().getSimpleName() + " E").getResultList();
    };
}
