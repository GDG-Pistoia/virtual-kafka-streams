package it.gdgpt.control.api;

import jakarta.persistence.EntityManager;

public interface HasPersistence<T> {
    EntityManager getEm();
    Class<T> getEntityType();
}