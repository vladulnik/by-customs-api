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
 * Сущность участника декларации.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = ParticipantEntity.TABLE_NAME)
public class ParticipantEntity {

    public static final String TABLE_NAME = "participants";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Имя или название участника.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Адрес участника.
     */
    @Column(nullable = false)
    private String address;
}
