package mx.edtecdesoftware.edu.mx.demo.persistence;

import mx.edtecdesoftware.edu.mx.demo.persistence.crud.ProductoCrudRepository;
import mx.edtecdesoftware.edu.mx.demo.persistence.entity.Producto;

import java.util.List;

public class ProductoRepository {

    private ProductoCrudRepository productoCrudRepository;

    // SELECT * FROM productos
    public List<Producto> getAll(){
        // Se castea de Iterable a Lista
        return (List<Producto>) productoCrudRepository.findAll();
    }
}
