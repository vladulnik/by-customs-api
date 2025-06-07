package by.customs.by_customs_api.entity;

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
 * Сущность таможенной декларации.
 * Пока только заглушка, без связей.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = DeclarationEntity.TABLE_NAME)
public class DeclarationEntity {

    public static final String TABLE_NAME = "declarations";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Здесь позже можно добавить связи: участник, товары, платежи и т.д.
}
