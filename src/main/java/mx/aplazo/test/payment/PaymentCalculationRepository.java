package mx.aplazo.test.payment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentCalculationRepository extends CrudRepository<PaymentCalculation, Integer> {
}
