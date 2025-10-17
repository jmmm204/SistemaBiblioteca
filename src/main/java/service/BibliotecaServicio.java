package service;

import config.JPAUtil;
import entities.Autor;
import entities.Categoria;
import entities.Libro;
import jakarta.persistence.EntityManager;
import repository.dao.AutorDao;
import repository.dao.CategoriaDao;
import repository.dao.LibroDao;

public class BibliotecaServicio {
    private final AutorDao autorDao = new AutorDao();
    private final CategoriaDao categoriaDao = new CategoriaDao();
    private final LibroDao libroDao = new LibroDao();

    public void crearDatosIniciales(Autor autor1, Autor autor2, Categoria cat1, Categoria cat2, Libro lib1, Libro lib2, Libro lib3) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            autorDao.crear(autor1, em);
            autorDao.crear(autor2, em);

            categoriaDao.crear(cat1, em);
            categoriaDao.crear(cat2, em);

            libroDao.crear(lib1, em);
            libroDao.crear(lib2, em);
            libroDao.crear(lib3, em);

            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}