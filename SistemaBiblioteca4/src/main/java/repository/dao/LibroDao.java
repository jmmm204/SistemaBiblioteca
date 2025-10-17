package repository.dao;

import config.JPAUtil;
import entities.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import repository.ICRUD;
import java.util.List;
import java.util.Optional;

public class LibroDao implements ICRUD<Libro, Long> {

    @Override
    public void crear(Libro libro, EntityManager em) {
        em.persist(libro);
    }

    @Override
    public Optional<Libro> obtenerPorId(Long id, EntityManager em) {
        return Optional.ofNullable(em.find(Libro.class, id));
    }

    @Override
    public List<Libro> obtenerTodos(EntityManager em) {
        return em.createQuery("SELECT l FROM Libro l", Libro.class).getResultList();
    }

    public List<Libro> obtenerTodosConRelaciones() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            String jpql = "SELECT DISTINCT l FROM Libro l JOIN FETCH l.autor JOIN FETCH l.categorias";
            TypedQuery<Libro> query = em.createQuery(jpql, Libro.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void actualizar(Libro libro, EntityManager em) {
        em.merge(libro);
    }

    @Override
    public void eliminar(Long id, EntityManager em) {
        obtenerPorId(id, em).ifPresent(em::remove);
    }
}