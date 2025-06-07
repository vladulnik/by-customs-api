package by.customs.by_customs_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность для таможенных платежей.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = PaymentEntity.TABLE_NAME)
public class PaymentEntity {

    public static final String TABLE_NAME = "payments";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Сумма пошлины.
     */
    @Column(nullable = false)
    private double duty;

    /**
     * Сумма НДС.
     */
    @Column(nullable = false)
    private double vat;

    /**
     * Сумма акциза.
     */
    @Column(nullable = false)
    private double excise;
}
