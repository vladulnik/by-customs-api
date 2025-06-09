package by.customs.by_customs_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность, представляющая информацию о таможенных платежах.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = PaymentEntity.TABLE_NAME)
public class PaymentEntity {

    public static final String TABLE_NAME = "payments";

    /**
     * Уникальный идентификатор платежа.
     */
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

    /**
     * Связь с декларацией.
     */
    @ManyToOne
    @JoinColumn(name = "declaration_id", nullable = false)
    private DeclarationEntity declaration;
}
