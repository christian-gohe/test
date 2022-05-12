package mx.aplazo.test.payment;

import java.util.List;

public interface PaymentService {

    List<PaymentDTO> calculate(CalculatePaymentRequest req);
}
