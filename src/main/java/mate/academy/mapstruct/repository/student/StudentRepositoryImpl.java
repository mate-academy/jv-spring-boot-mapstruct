package mate.academy.mapstruct.repository.student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.mapstruct.model.Student;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private final EntityManagerFactory entityManagerFactory;

    @Override
    public Student save(Student student) {
        EntityTransaction transaction = null;
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(student);
            transaction.commit();
            return student;
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public Optional<Student> findById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            TypedQuery<Student> query = entityManager.createQuery(
                    "SELECT s FROM Student s LEFT JOIN FETCH s.subjects WHERE s.id = :id",
                    Student.class
            );
            query.setParameter("id", id);
            try {
                Student student = query.getSingleResult();
                return Optional.of(student);
            } catch (NoResultException ex) {
                return Optional.empty();
            }
        }
    }

    @Override
    public List<Student> findAll() {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            return entityManager.createQuery("SELECT s FROM Student s JOIN FETCH s.subjects",
                            Student.class)
                    .getResultList();
        }
    }
}
