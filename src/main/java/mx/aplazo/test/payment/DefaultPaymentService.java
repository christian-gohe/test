package mx.aplazo.test.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
class DefaultPaymentService implements PaymentService {

    private final PaymentCalculationRepository repository;
    private final PaymentMapper mapper;
    private final PaymentCalculator calculator;

    @Override
    @Transactional
    public List<PaymentDTO> calculate(CalculatePaymentRequest req) {
        PaymentCalculation calculation = PaymentCalculationFactory.create(req);
        List<Payment> payments = calculator.calculate(calculation);
        calculation.setPayments(payments);
        repository.save(calculation);
        return mapper.map(calculation.getPayments());
    }
}
