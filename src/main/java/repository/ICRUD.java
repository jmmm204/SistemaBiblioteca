package repository;

import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public interface ICRUD<T, ID> {

    void crear(T entidad, EntityManager em);

    Optional<T> obtenerPorId(ID id, EntityManager em);

    List<T> obtenerTodos(EntityManager em);

    void actualizar(T entidad, EntityManager em);

    void eliminar(ID id, EntityManager em);
}