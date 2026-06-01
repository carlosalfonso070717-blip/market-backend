package mx.edtecdesoftware.edu.mx.demo.persistence;

import mx.edtecdesoftware.edu.mx.demo.persistence.crud.ProductoCrudRepository;
import mx.edtecdesoftware.edu.mx.demo.persistence.entity.Producto;

import java.util.List;
import java.util.Optional;

public class ProductoRepository {

    private ProductoCrudRepository productoCrudRepository;

    // SELECT * FROM productos
    public List<Producto> getAll(){
        // Se castea de Iterable a Lista
        return (List<Producto>) productoCrudRepository.findAll();
    }

    public List<Producto> getByCategoria(int idCategoria){
        return productoCrudRepository.findByIdCategoriaOrderByNombreAsc(idCategoria);
    }

    /*
      SELECT * FROM producto
      WHERE cantidad_stock < ?
      AND estado = true;
     */
    public Optional<List<Producto>> getEscasos(int cantidad){
        return productoCrudRepository.findByCantidadStockLessThanAndEstado(cantidad, true);
    }

    // Obtener un producto dado el id
    public Optional<Producto> getProductoById(int idProducto){
        return productoCrudRepository.findById(idProducto);
    }

    /*
      INSERT INTO producto (nombre, cantidad_stock, estado, id_categoria)
      VALUES (?, ?, ?, ?);
    */
    // Guardar un producto
    public Producto save(Producto producto){
        return productoCrudRepository.save(producto);
    }

    //Eliminar el id
    public void delete(int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }

}
