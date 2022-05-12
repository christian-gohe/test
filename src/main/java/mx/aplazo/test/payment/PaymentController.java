package mx.aplazo.test.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
class PaymentController {

    private final PaymentService service;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Iterable<PaymentDTO> calculatePayment(@RequestBody @Valid CalculatePaymentRequest req) {
        return service.calculate(req);
    }
}
