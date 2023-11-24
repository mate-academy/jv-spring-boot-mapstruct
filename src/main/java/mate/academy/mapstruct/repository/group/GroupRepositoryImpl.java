package mate.academy.mapstruct.repository.group;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.mapstruct.dto.model.Group;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class GroupRepositoryImpl implements GroupRepository {
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Group save(Group group) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(group);
            transaction.commit();
            return group;
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public List<Group> findAll() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery("SELECT g FROM Group g", Group.class)
                    .getResultList();
        }
    }
}
