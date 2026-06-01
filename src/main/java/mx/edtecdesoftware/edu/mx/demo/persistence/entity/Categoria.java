package mx.edtecdesoftware.edu.mx.demo.persistence.entity;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "descripcion", length = 45)
    private String descripcion;

    @Column(name = "estado")
    private Boolean estado;

    @OneToMany(
            mappedBy = "categoria"
    )
    private List<Producto> productos;

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public Boolean getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
