package mate.academy.mapstruct.repository.subject;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.mapstruct.model.Subject;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class SubjectRepositoryImpl implements SubjectRepository {
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Optional<Subject> findById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<Subject> query = entityManager.createQuery(
                    "FROM Subject s WHERE s.id = :id",
                    Subject.class
            );
            query.setParameter("id", id);
            try {
                Subject subject = query.getSingleResult();
                return Optional.of(subject);
            } catch (NoResultException ex) {
                return Optional.empty();
            }
        }
    }

    @Override
    public Subject save(Subject subject) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(subject);
            transaction.commit();
            return subject;
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public List<Subject> saveAll(List<Subject> subjects) {
        List<Subject> savedSubjectsArrayList = new ArrayList<>();
        for (Subject subject : subjects) {
            savedSubjectsArrayList.add(save(subject));
        }
        return savedSubjectsArrayList;
    }

    @Override
    public List<Subject> findAll() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery("SELECT s FROM Subject s", Subject.class)
                    .getResultList();
        }
    }
}
