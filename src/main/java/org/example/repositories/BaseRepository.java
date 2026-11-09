package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class BaseRepository<T> {
    @PersistenceContext
    private EntityManager entityManager;

    public T get(UUID id) {
        T entity = (T) entityManager.find(this.getClass(), id);
        if (entity == null) {
            throw new NotFoundException("Entity with id " + id + " not found");
        }
        return entity;
    }

    @Transactional
    public void create(T entity) {
        entityManager.persist(entity);
    }

    @Transactional
    public void update(T entity) {
        entityManager.merge(entity);
    }

    @Transactional
    public void delete(UUID id) {
        T entity = get(id);
        entityManager.remove(entity);
    }

}
