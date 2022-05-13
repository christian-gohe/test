package mx.aplazo.test.payment;

import io.micrometer.core.lang.Nullable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@ToString
@Table(name = "payment_calculation")
public class PaymentCalculation {

    @Id
    @Nullable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "terms")
    private Integer terms;
    @Column(name = "rate")
    private Double rate;

    @ToString.Exclude
    @OneToMany(mappedBy = "calculation", cascade = CascadeType.PERSIST)
    private List<Payment> payments;

    void setPayments(List<Payment> payments) {
        this.payments = payments;
        if (payments.isEmpty()) return;
        payments.forEach(payment -> payment.setCalculation(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PaymentCalculation that = (PaymentCalculation) o;
        return id != null && Objects.equals(id, that.id);
    }

    public double total() {
        return amount * ((100D + rate) / 100D);
    }

    public double amountByTerm() {
        return total() / terms;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
