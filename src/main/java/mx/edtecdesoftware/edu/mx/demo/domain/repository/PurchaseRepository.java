package mx.edtecdesoftware.edu.mx.demo.domain.repository;

import mx.edtecdesoftware.edu.mx.demo.domain.dto.Purchase;
import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(String clientId);
    Purchase save(Purchase purchase);
}