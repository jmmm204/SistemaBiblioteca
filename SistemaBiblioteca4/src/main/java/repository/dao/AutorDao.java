package repository.dao;

import config.JPAUtil;
import entities.Autor;
import entities.Categoria;
import jakarta.persistence.EntityManager;
import repository.ICRUD;
import java.util.List;
import java.util.Optional;

public class AutorDao implements ICRUD<Autor, Long> {

    @Override
    public void crear(Autor autor, EntityManager em) {
        em.persist(autor);
    }

    @Override
    public Optional<Autor> obtenerPorId(Long id, EntityManager em) {
        return Optional.ofNullable(em.find(Autor.class, id));
    }

    @Override
    public List<Autor> obtenerTodos(EntityManager em) {
        return em.createQuery("SELECT a FROM Autor a", Autor.class).getResultList();
    }

    @Override
    public void actualizar(Autor autor, EntityManager em) {
        em.merge(autor);
    }

    @Override
    public void eliminar(Long id, EntityManager em) {
        obtenerPorId(id, em).ifPresent(em::remove);
    }
}