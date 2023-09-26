package it.gdgpt.control;

import it.gdgpt.control.api.HasFindAll;
import it.gdgpt.control.api.HasSaveOrUpdate;
import it.gdgpt.entities.MyCheckpoint;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.Optional;

@Dependent
public class MyCheckpointControl implements HasFindAll<MyCheckpoint>, HasSaveOrUpdate<MyCheckpoint> {

    @Inject
    EntityManager entityManager;

    @Override
    public EntityManager getEm() {
        return entityManager;
    }

    @Override
    public Class<MyCheckpoint> getEntityType() {
        return MyCheckpoint.class;
    }

    public Optional<MyCheckpoint> pickOldestCheckpoint(){
        LocalDateTime localDateTime = LocalDateTime.now();
        try{
            return Optional.of(
                    getEm().createQuery("SELECT C FROM MyCheckpoint C WHERE C.updated < :now ORDER BY C.updated ASC", MyCheckpoint.class)
                            .setParameter("now", localDateTime)
                            .getResultList().get(0)
            );
        }catch (IndexOutOfBoundsException  nre){
            //log.warn("MyCheckpoint Not Found time={}", localDateTime);
            return Optional.empty();
        }
    }


}