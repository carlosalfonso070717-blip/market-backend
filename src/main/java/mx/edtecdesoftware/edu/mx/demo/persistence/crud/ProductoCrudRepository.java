package mx.edtecdesoftware.edu.mx.demo.persistence.crud;

import mx.edtecdesoftware.edu.mx.demo.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository
        extends CrudRepository<Producto, Integer> {

    // Query method
     /*
         SELECT * FROM categorias
         WHERE id_categoria = ?
         ORDER BY nombre ASC
     */
    // Obtener una lista de productos filtrados por id de categoría
    // y ordenados ascendentemente por nombre

    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    // Obtener los productos escasos
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(
            int cantidad,
            boolean estado);

}
