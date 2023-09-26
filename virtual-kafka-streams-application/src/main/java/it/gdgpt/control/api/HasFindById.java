package it.gdgpt.control.api;

import java.util.Optional;

public interface HasFindById<T, K> extends HasPersistence<T> {

    default Optional<T> findById(K id){
        return doFindById(id);
    }

    default Optional<T> doFindById(K id) {
        return Optional.ofNullable(getEm().find(getEntityType(), id));
    }
}