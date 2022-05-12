package mx.aplazo.test.payment;

import java.util.List;

public interface PaymentCalculator {

    List<Payment> calculate(PaymentCalculation calculation);
}
