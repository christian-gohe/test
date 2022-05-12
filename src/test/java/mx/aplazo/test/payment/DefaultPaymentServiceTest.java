package mx.aplazo.test.payment;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class DefaultPaymentServiceTest {

    @Autowired
    private PaymentCalculationRepository repository;

    @Autowired
    private PaymentService service;

    @Test
    @Transactional
    void testSavedCalculationOnDB() {
        assertTrue(((Collection<PaymentCalculation>) repository.findAll()).isEmpty());
        CalculatePaymentRequest req = new CalculatePaymentRequest(100D, 4, 15D);
        List<PaymentDTO> payments = service.calculate(req);
        List<PaymentCalculation> calculations = (List<PaymentCalculation>) repository.findAll();
        assertFalse(calculations.isEmpty());
        assertEquals(1, calculations.size());
        PaymentCalculation calc = calculations.get(0);
        assertEquals(req.amount(), calc.getAmount());
        assertEquals(req.terms(), calc.getTerms());
        assertEquals(req.rate(), calc.getRate());
        assertEquals(payments.size(), calc.getPayments().size());
        for (PaymentDTO dto : payments) {
            for (Payment payment : calc.getPayments()) {
                if (dto.number() == payment.getNumber()) {
                    assertEquals(dto.amount(), payment.getAmount());
                    assertEquals(dto.date(), payment.getDate());
                    assertEquals(dto.number(), payment.getNumber());
                }
            }
        }
    }
}