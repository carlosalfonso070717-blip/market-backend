package mx.edtecdesoftware.edu.mx.demo.persistence.mapper;

import mx.edtecdesoftware.edu.mx.demo.domain.dto.PurchaseItem;
import mx.edtecdesoftware.edu.mx.demo.persistence.entity.CompraProducto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PurchaseItemMapper {

    @Mappings({
            @Mapping(source = "id.idProducto", target = "productId"),
            @Mapping(source = "cantidad", target = "quantity"),
            @Mapping(source = "estado", target = "active")
    })
    PurchaseItem toPurchaseItem(CompraProducto producto);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(source = "productId", target = "id.idProducto"), // Esta ya la deberías tener (tu llave compuesta)

            @Mapping(source = "productId", target = "producto.idProducto"),

            @Mapping(source = "quantity", target = "cantidad"),
            @Mapping(source = "active", target = "estado")
    })
    CompraProducto toCompraProducto(PurchaseItem item);

}