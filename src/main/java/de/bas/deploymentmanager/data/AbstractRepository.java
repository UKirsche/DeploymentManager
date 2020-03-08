package de.bas.deploymentmanager.data;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AbstractRepository {

    @PersistenceContext
    EntityManager entityManager;
}
