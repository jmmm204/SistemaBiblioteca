package repository.dao;

import config.JPAUtil;
import entities.Categoria;
import jakarta.persistence.EntityManager;
import repository.ICRUD;

import java.util.List;
import java.util.Optional;

public class CategoriaDao implements ICRUD<Categoria, Long> {
    private EntityManager em = JPAUtil.getEntityManager();

    @Override
    public void crear(Categoria categoria) {
        try {
            em.getTransaction().begin();
            em.persist(categoria);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Categoria> obtenerPorId(Long id) {
        return Optional.ofNullable(em.find(Categoria.class, id));
    }

    @Override
    public List<Categoria> obtenerTodos() {
        return em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
    }

    @Override
    public void actualizar(Categoria categoria) {
        try {
            em.getTransaction().begin();
            em.merge(categoria);
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
            Categoria categoria = em.find(Categoria.class, id);
            if (categoria != null) {
                em.remove(categoria);
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