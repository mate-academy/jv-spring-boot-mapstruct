package mate.academy.mapstruct.repository.group;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.mapstruct.exception.EntityNotFoundException;
import mate.academy.mapstruct.model.Group;
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

    @Override
    public Group findById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<Group> query = entityManager
                    .createQuery("FROM Group g WHERE g.id = :id", Group.class);
            query.setParameter("id", id);
            try {
                return query.getSingleResult();
            } catch (NoResultException e) {
                throw new EntityNotFoundException("Group " + id + " not found");
            }
        }
    }
}
