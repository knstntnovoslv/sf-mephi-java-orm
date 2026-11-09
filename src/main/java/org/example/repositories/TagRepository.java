package org.example.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.entities.Tag;
import org.example.exceptions.NotFoundException;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TagRepository {
    @PersistenceContext
    private final EntityManager entityManager;

    public Tag get(UUID id) {
        Tag tag = entityManager.find(Tag.class, id);
        if (tag == null) {
            throw new NotFoundException("Tag with id " + id + " not found");
        }
        return tag;
    }

    @Transactional
    public void create(Tag tag) {
        entityManager.persist(tag);
    }

    @Transactional
    public void update(Tag tag) {
        entityManager.merge(tag);
    }

    @Transactional
    public void delete(UUID id) {
        Tag tag = get(id);
        entityManager.remove(tag);
    }
}
