package repository.dao;

import config.JPAUtil;
import entities.Categoria;
import jakarta.persistence.EntityManager;
import repository.ICRUD;

import java.util.List;
import java.util.Optional;

public class CategoriaDao implements ICRUD<Categoria, Long> {

    @Override
    public void crear(Categoria categoria, EntityManager em) {
        em.persist(categoria);
    }

    @Override
    public Optional<Categoria> obtenerPorId(Long id, EntityManager em) {
        return Optional.ofNullable(em.find(Categoria.class, id));
    }

    @Override
    public List<Categoria> obtenerTodos(EntityManager em) {
        return em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
    }

    @Override
    public void actualizar(Categoria categoria, EntityManager em) {
        em.merge(categoria);
    }

    @Override
    public void eliminar(Long id, EntityManager em) {
        obtenerPorId(id, em).ifPresent(em::remove);
    }
}