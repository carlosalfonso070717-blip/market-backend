package mx.edtecdesoftware.edu.mx.demo.persistence.crud;

import mx.edtecdesoftware.edu.mx.demo.persistence.entity.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoCrudRepository
        extends CrudRepository<Producto, Integer> {

}
