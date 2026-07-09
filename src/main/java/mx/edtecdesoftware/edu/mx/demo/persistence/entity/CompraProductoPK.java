package mx.edtecdesoftware.edu.mx.demo.persistence.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class CompraProductoPK implements Serializable {

    @Column(name = "id_compra")
    private Integer idCompra;

    @Column(name = "id_producto")
    private Integer idProducto;

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        idCompra = idCompra;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        idProducto = idProducto;
    }
}
