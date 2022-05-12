package mx.aplazo.test.payment;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public record CalculatePaymentRequest(@NotNull @Positive Double amount,
                                      @NotNull @Positive Integer terms,
                                      @NotNull @Positive Double rate) {
}
