package mx.aplazo.test.payment;

import java.util.ArrayList;

public final class PaymentCalculationFactory {

    private PaymentCalculationFactory() {
    }

    public static PaymentCalculation create(CalculatePaymentRequest req) {
        PaymentCalculation calculation = new PaymentCalculation();
        calculation.setPayments(new ArrayList<>());
        calculation.setAmount(req.amount());
        calculation.setRate(req.rate());
        calculation.setTerms(req.terms());
        return calculation;
    }
}
