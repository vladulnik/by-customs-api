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
 * Сущность, представляющая участника декларации.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = ParticipantEntity.TABLE_NAME)
public class ParticipantEntity {

    public static final String TABLE_NAME = "participants";

    /**
     * Уникальный идентификатор участника.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название или ФИО.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Адрес участника.
     */
    @Column(nullable = false)
    private String address;

    /**
     * Связь с декларацией.
     */
    @ManyToOne
    @JoinColumn(name = "declaration_id", nullable = false)
    private DeclarationEntity declaration;
}
