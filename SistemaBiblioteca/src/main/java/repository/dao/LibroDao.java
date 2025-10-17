package repository.dao;

import config.JPAUtil;
import entities.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.ICRUD;

import java.util.List;
import java.util.Optional;

public class LibroDao implements ICRUD<Libro, Long> {
    private final EntityManager em = JPAUtil.getEntityManager();

    @Override
    public void crear(Libro libro) {
        try {
            em.getTransaction().begin();
            if (!em.contains(libro.getAutor())) {
                libro.setAutor(em.merge(libro.getAutor()));
            }
            libro.getCategorias().forEach(cat -> {
                if (!em.contains(cat)) {
                    em.merge(cat);
                }
            });
            em.persist(libro);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Libro> obtenerPorId(Long id) {
        return Optional.ofNullable(em.find(Libro.class, id));
    }

    @Override
    public List<Libro> obtenerTodos() {
        return em.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
    }

    public List<Libro> obtenerTodosConRelaciones() {
        String jpql = "SELECT DISTINCT l FROM Libro l JOIN FETCH l.autor JOIN FETCH l.categorias";
        TypedQuery<Libro> query = em.createQuery(jpql, Libro.class);
        return query.getResultList();
    }

    @Override
    public void actualizar(Libro libro) {
        try {
            em.getTransaction().begin();
            em.merge(libro);
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
            Libro libro = em.find(Libro.class, id);
            if (libro != null) {
                em.remove(libro);
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