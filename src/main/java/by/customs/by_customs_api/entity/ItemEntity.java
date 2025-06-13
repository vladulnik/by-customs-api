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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Сущность товарной позиции
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "hs_code", nullable = false)
    private String hsCode;

    @Column(name = "value", nullable = false)
    private Double value;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "origin_country", nullable = false)
    private String originCountry;

    @ManyToOne
    @JoinColumn(name = "declaration_id", nullable = false)
    private DeclarationEntity declaration;
}
