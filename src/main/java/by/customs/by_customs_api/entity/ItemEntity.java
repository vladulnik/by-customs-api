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
     * Описание товара (например, "Ноутбук").
     */
    @Column(nullable = false)
    private String description;

    /**
     * Вес товара в килограммах.
     */
    @Column(nullable = false)
    private double weight;

    /**
     * Ссылка на декларацию, к которой относится товар.
     */
    @ManyToOne
    @JoinColumn(name = "declaration_id", nullable = false)
    private DeclarationEntity declaration;
}
