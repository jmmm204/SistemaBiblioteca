package run;

import config.JPAUtil;
import entities.Autor;
import entities.Categoria;
import entities.Libro;
import repository.dao.AutorDao;
import repository.dao.CategoriaDao;
import repository.dao.LibroDao;
import service.BibliotecaServicio;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        BibliotecaServicio bibliotecaServicio = new BibliotecaServicio();

        Autor autor1 = new Autor("Gabriel Garcia Marquez", "Colombiano", LocalDate.of(1927, 3, 6));
        Autor autor2 = new Autor("J.K. Rowling", "Britanica", LocalDate.of(1965, 7, 31));

        Categoria catFiccion = new Categoria("Ficcion");
        Categoria catFantasia = new Categoria("Fantasia");

        Libro libro1 = new Libro("Cien años de soledad", 1967, autor1);
        libro1.addCategoria(catFiccion);

        Libro libro2 = new Libro("El amor en los tiempos del colera", 1985, autor1);
        libro2.addCategoria(catFiccion);

        Libro libro3 = new Libro("Harry Potter y la piedra filosofal", 1997, autor2);
        libro3.addCategoria(catFiccion);
        libro3.addCategoria(catFantasia);

        bibliotecaServicio.crearDatosIniciales(autor1, autor2, catFiccion, catFantasia, libro1, libro2, libro3);

        System.out.println("Datos insertados correctamente.");

        System.out.println("Realizando consulta.");
        LibroDao libroDao = new LibroDao();
        List<Libro> libros = libroDao.obtenerTodosConRelaciones();

        System.out.println("Listado de Libros");
        for (Libro libro : libros) {
            String nombresCategorias = libro.getCategorias().stream()
                    .map(Categoria::getNombre).collect(Collectors.joining(", "));

            System.out.printf("ID: %d | Titulo: %s | Autor: %s | Categorias: [%s]\n",
                    libro.getId(), libro.getTitulo(), libro.getAutor().getNombre(), nombresCategorias);
        }
        JPAUtil.shutdown();
    }
}