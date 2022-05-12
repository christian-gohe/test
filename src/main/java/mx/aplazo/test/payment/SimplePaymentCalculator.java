package mx.aplazo.test.payment;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
class SimplePaymentCalculator implements PaymentCalculator {

    @Override
    public List<Payment> calculate(PaymentCalculation calculation) {
        double amount = calculation.amountByTerm();
        LocalDate startDate = LocalDate.now();
        return IntStream.rangeClosed(1, calculation.getTerms())
                .mapToObj(index -> {
                    Payment payment = new Payment();
                    payment.setNumber(index);
                    payment.setAmount(amount);
                    payment.setDate(startDate.plusWeeks(index));
                    return payment;
                }).collect(Collectors.toList());
    }
}
