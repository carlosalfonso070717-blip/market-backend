package mx.edtecdesoftware.edu.mx.demo.persistence;

import mx.edtecdesoftware.edu.mx.demo.domain.dto.Purchase;
import mx.edtecdesoftware.edu.mx.demo.domain.repository.PurchaseRepository;
import mx.edtecdesoftware.edu.mx.demo.persistence.crud.CompraCrudRepository;
import mx.edtecdesoftware.edu.mx.demo.persistence.entity.Compra;
import mx.edtecdesoftware.edu.mx.demo.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    private final CompraCrudRepository compraCrudRepository;
    private final PurchaseMapper mapper;

    @Autowired
    public CompraRepository(CompraCrudRepository compraCrudRepository, PurchaseMapper mapper) {
        this.compraCrudRepository = compraCrudRepository;
        this.mapper = mapper;
    }

    @Override
    public List<Purchase> getAll() {
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                .map(mapper::toPurchases);
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);

        if (compra.getProductos() != null) {
            compra.getProductos().forEach(producto -> producto.setCompra(compra));
        }

        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}