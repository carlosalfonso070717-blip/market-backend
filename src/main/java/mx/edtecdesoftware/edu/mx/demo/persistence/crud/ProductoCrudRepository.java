package mx.edtecdesoftware.edu.mx.demo.persistence.crud;

import org.springframework.data.repository.CrudRepository;
import mx.edtecdesoftware.edu.mx.demo.persistence.entity.Producto;
import java.util.List;
import java.util.Optional;

public interface ProductoCrudRepository extends CrudRepository<Producto, Integer> {

    // Query Method
    /*
        SELECT *
        FROM categorias
        WHERE id_categoria = ?
        ORDER BY nombre ASC
     */
    // Obtener una lista de productos filtrados por id de categoria
    // y ordenarlos ascendentemente por nombre

    List<Producto> findByIdCategoriaOrderByNombreAsc(int idCategoria);

    // Obtener los productos escasos
    Optional<List<Producto>> findByCantidadStockLessThanAndEstado(int cantidad,boolean estado);



}
