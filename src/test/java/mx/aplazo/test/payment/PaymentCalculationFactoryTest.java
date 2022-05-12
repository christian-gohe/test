package mx.aplazo.test.payment;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaymentCalculationFactoryTest {

    @Test
    void testCreate() {
        CalculatePaymentRequest req = new CalculatePaymentRequest(100D, 4, 15D);
        PaymentCalculation calc = PaymentCalculationFactory.create(req);
        assertEquals(req.amount(), calc.getAmount());
        assertEquals(req.rate(), calc.getRate());
        assertEquals(req.terms(), calc.getTerms());
        assertNotNull(calc.getPayments());
        assertTrue(calc.getPayments().isEmpty());
    }
}