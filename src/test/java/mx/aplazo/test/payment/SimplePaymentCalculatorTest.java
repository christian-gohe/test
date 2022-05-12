package mx.aplazo.test.payment;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class SimplePaymentCalculatorTest {

    @Test
    void testCalculate() {
        PaymentCalculation calc = new PaymentCalculation();
        calc.setAmount(100D);
        calc.setRate(15D);
        calc.setTerms(4);
        SimplePaymentCalculator calculator = new SimplePaymentCalculator();
        List<Payment> payments = calculator.calculate(calc);
        assertNotNull(payments);
        assertFalse(payments.isEmpty());
        assertEquals(calc.getTerms(), payments.size());
        payments.forEach(obj -> log.info("payment: {}", obj));
        assertEquals(calc.total(), payments.stream().mapToDouble(Payment::getAmount).sum());
        LocalDate last = null;
        for (Payment payment : payments) {
            if (last == null) {
                last = payment.getDate();
                continue;
            }
            log.info("Date1: {}, Date2 {}", last, payment.getDate());
            assertEquals(7, DAYS.between(last, payment.getDate()));
            last = payment.getDate();
        }
    }
}