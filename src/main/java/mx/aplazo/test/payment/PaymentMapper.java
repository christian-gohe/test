package mx.aplazo.test.payment;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMapper {

    public PaymentDTO map(Payment payment) {
        return new PaymentDTO(payment.getNumber(), payment.getAmount(), payment.getDate());
    }

    public List<PaymentDTO> map(List<Payment> payments) {
        return payments.stream().map(this::map).collect(Collectors.toList());
    }
}
