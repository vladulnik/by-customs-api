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
 * Сущность, представляющая товарную позицию в декларации.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = ItemEntity.TABLE_NAME)
public class ItemEntity {

    public static final String TABLE_NAME = "items";

    /**
     * Уникальный идентификатор товарной позиции.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Код ТН ВЭД.
     */
    @Column(name = "hs_code", nullable = false)
    private String hsCode;

    /**
     * Стоимость товара.
     */
    @Column(nullable = false)
    private double value;

    /**
     * Вес товара в килограммах.
     */
    @Column(nullable = false)
    private double weight;

    /**
     * Страна происхождения товара.
     */
    @Column(name = "origin_country", nullable = false)
    private String originCountry;

    /**
     * Ссылка на декларацию, к которой относится товар.
     */
    @ManyToOne
    @JoinColumn(name = "declaration_id", nullable = false)
    private DeclarationEntity declaration;
}
