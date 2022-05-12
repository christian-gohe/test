package mx.aplazo.test.payment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record PaymentDTO(@JsonProperty("payment_number") int number, double amount,
                         @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy") @JsonProperty("payment_date") LocalDate date) {
}
