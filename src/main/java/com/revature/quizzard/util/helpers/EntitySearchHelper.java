package com.revature.quizzard.util.helpers;

import com.revature.quizzard.util.exceptions.InvalidRequestException;
import com.revature.quizzard.util.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Field;
import java.util.*;

@Repository
public class EntitySearchHelper {

    private final EntityManager entityManager;

    @Autowired
    public EntitySearchHelper(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public <T> Set<T> searchForEntity(Map<String, String> searchCriteria, Class<T> entityClass) {

        if (entityClass.getAnnotation(Entity.class) == null) {
            throw new InvalidRequestException("Provided class is not an Entity!");
        }

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(entityClass);
        Root<T> root = query.from(entityClass);
        Predicate predicate = cb.conjunction();
        for(Map.Entry<String, String> searchEntry : searchCriteria.entrySet()) {

            String searchKey = searchEntry.getKey();
            String searchVal = searchEntry.getValue();

            try {
                Field searchField = entityClass.getDeclaredField(searchKey);
                if (searchField.getType().isEnum()) {
                    try {
                        @SuppressWarnings("unchecked")
                        Enum enumVal = Enum.valueOf((Class<Enum>) searchField.getType(), searchVal.toUpperCase());
                        predicate = cb.and(predicate, cb.equal(root.get(searchKey), enumVal));
                    } catch (IllegalArgumentException e) {
                        throw new ResourceNotFoundException();
                    }
                } else {
                    predicate = cb.and(predicate, cb.equal(root.get(searchKey), searchVal));
                }

            } catch (NoSuchFieldException e) {
                throw new InvalidRequestException(String.format("No attribute with name: %s found on entity: %s", searchKey, entityClass.getName()));
            }

        }
        query.where(predicate);
        return new HashSet<>(entityManager.createQuery(query).getResultList());
    }
}
