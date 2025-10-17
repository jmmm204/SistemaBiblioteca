Caso de Estudio: Sistema de Biblioteca

Una pequeña biblioteca desea registrar información sobre libros y autores.
Cada libro puede tener un solo autor, pero un autor puede haber escrito varios libros.
Además, cada libro puede estar asociado a una o varias categorías (por ejemplo: Ficción, Educativo, Ciencia), y una misma categoría puede aplicarse a muchos libros.

Tu tarea será modelar estas relaciones usando JPA.

Indicaciones
Crea un proyecto JPA con tres entidades:

Autor (id, nombre, nacionalidad, fecha de nacimiento)
Libro (id, titulo, anioPublicacion, autor)
Categoria (id, nombre)

Establece las relaciones:

Autor → Libro: @OneToMany (un autor tiene muchos libros)
Libro → Autor: @ManyToOne (cada libro tiene un autor)
Libro ↔ Categoria: @ManyToMany (libros pueden tener varias categorías y viceversa)

Crea una clase Main o App que:

Inserte al menos dos autores, tres libros y dos categorías.
Relacione correctamente los datos (por ejemplo, dos libros del mismo autor).
Realice una consulta simple para listar los libros con su autor y categorías.
