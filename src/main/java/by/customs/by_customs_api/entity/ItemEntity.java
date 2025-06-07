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
 * Сущность товарной позиции.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = ItemEntity.TABLE_NAME)
public class ItemEntity {

    public static final String TABLE_NAME = "items";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Код ТН ВЭД.
     */
    @Column(nullable = false)
    private String commodityCode;

    /**
     * Стоимость товара.
     */
    @Column(nullable = false)
    private double value;

    /**
     * Вес товара.
     */
    @Column(nullable = false)
    private double weight;

    /**
     * Страна происхождения.
     */
    @Column(nullable = false)
    private String originCountry;
}
