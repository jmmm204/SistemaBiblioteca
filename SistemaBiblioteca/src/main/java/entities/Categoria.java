package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToMany(mappedBy = "categorias")
    private Set<Libro> libros = new HashSet<Libro>();

    public Categoria() {
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }
}