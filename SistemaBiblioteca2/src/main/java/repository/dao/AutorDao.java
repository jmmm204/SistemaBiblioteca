package repository.dao;

import config.JPAUtil;
import entities.Autor;
import jakarta.persistence.EntityManager;
import repository.ICRUD;
import java.util.List;
import java.util.Optional;

public class AutorDao implements ICRUD<Autor, Long> {
    private EntityManager em = JPAUtil.getEntityManager();

    @Override
    public void crear(Autor autor) {
        try {
            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Autor> obtenerPorId(Long id) {
        return Optional.ofNullable(em.find(Autor.class, id));
    }

    @Override
    public List<Autor> obtenerTodos() {
        return em.createQuery("SELECT a FROM Autor a", Autor.class).getResultList();
    }

    @Override
    public void actualizar(Autor autor) {
        try {
            em.getTransaction().begin();
            em.merge(autor);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            em.getTransaction().begin();
            Autor autor = em.find(Autor.class, id);
            if (autor != null) {
                em.remove(autor);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
}