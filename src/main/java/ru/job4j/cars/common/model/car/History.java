package ru.job4j.cars.common.model.car;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.job4j.cars.common.model.car.Owner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Мадель история владения автомобиля
 */
@Entity
@Table(name = "history")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class History {

    /** Идентификатор истории */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Дата начала */
    @Column(name = "start_at")
    private LocalDateTime startAt;

    /** Дата окончания */
    @Column(name = "end_at")
    private LocalDateTime endAt;

    /** Владелец */
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
}
