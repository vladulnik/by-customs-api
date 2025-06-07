package by.customs.by_customs_api.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * Сущность, представляющая таможенную декларацию.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DeclarationEntity.TABLE_NAME)
public class DeclarationEntity {

    public static final String TABLE_NAME = "declarations";

    /**
     * Уникальный идентификатор декларации.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Номер декларации (например, "DEC123").
     */
    @Column(nullable = false)
    private String number;

    /**
     * Дата оформления декларации.
     */
    @Column(nullable = false)
    private LocalDate date;

    /**
     * Список товарных позиций, связанных с этой декларацией.
     */
    @OneToMany(mappedBy = "declaration", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemEntity> items;
}
