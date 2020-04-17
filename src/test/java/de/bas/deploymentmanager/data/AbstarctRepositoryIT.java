package de.bas.deploymentmanager.data;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

@Testcontainers
public class AbstarctRepositoryIT {

   public EntityManager em;
    EntityTransaction tx;

    @Container
    static final PostgreSQLContainer postgres = new PostgreSQLContainer()
            .withDatabaseName("postgres")
            .withPassword("docker")
            .withUsername("docker");

    @BeforeEach
    public void init() {
        Map<String, String> configuration = new HashMap();
        configuration.put("javax.persistence.jdbc.url", postgres.getJdbcUrl());
        configuration.put("javax.persistence.jdbc.user", postgres.getUsername());
        configuration.put("javax.persistence.jdbc.password", postgres.getPassword());


        this.em = Persistence.
                createEntityManagerFactory("integration-test", configuration).
                createEntityManager();
        this.tx = this.em.getTransaction();

        Flyway flyway = Flyway.configure().dataSource(postgres.getJdbcUrl()
                , postgres.getUsername()
                , postgres.getPassword()).locations("db/migration/postgres").load();

        flyway.clean();
        flyway.migrate();
    }

    public <S extends AbstractRepository> S injectEntityManager(S repository) {
        repository.entityManager = em;
        return repository;
    }

    public void executeNativQuery(String sql) {
        tx.begin();
        em.createNativeQuery(sql).executeUpdate();
        tx.commit();
    }


}
