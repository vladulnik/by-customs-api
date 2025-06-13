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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Сущность декларации (ДТ-1, ДТ-2)
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "declarations")
public class DeclarationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(nullable = false)
    private LocalDate date;

    @OneToMany(mappedBy = "declaration", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ItemEntity> items = new ArrayList<>();

    @OneToMany(mappedBy = "declaration", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<ParticipantEntity> participants = new ArrayList<>();

    @OneToMany(mappedBy = "declaration", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PaymentEntity> payments = new ArrayList<>();

    /**
     * Добавить товар с установкой двусторонней связи
     */
    public void addItem(ItemEntity item) {
        items.add(item);
        item.setDeclaration(this);
    }

    /**
     * Добавить участника с установкой двусторонней связи
     */
    public void addParticipant(ParticipantEntity participant) {
        participants.add(participant);
        participant.setDeclaration(this);
    }

    /**
     * Добавить платеж с установкой двусторонней связи
     */
    public void addPayment(PaymentEntity payment) {
        payments.add(payment);
        payment.setDeclaration(this);
    }

    /**
     * Удалить товар с разрывом двусторонней связи
     */
    public void removeItem(ItemEntity item) {
        items.remove(item);
        item.setDeclaration(null);
    }

    /**
     * Удалить участника с разрывом двусторонней связи
     */
    public void removeParticipant(ParticipantEntity participant) {
        participants.remove(participant);
        participant.setDeclaration(null);
    }

    /**
     * Удалить платеж с разрывом двусторонней связи
     */
    public void removePayment(PaymentEntity payment) {
        payments.remove(payment);
        payment.setDeclaration(null);
    }
}
