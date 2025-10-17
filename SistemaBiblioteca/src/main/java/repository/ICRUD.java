package repository;

import java.util.List;
import java.util.Optional;

public interface ICRUD<T, ID> {
    void crear(T entidad);

    Optional<T> obtenerPorId(ID id);

    List<T> obtenerTodos();

    void actualizar(T entidad);

    void eliminar(ID id);
}