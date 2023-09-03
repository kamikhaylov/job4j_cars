package ru.job4j.cars.common.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Мадель автомобиля
 */
@Entity
@Table(name = "car")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Car {

    /** Идентификатор автомобиля */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /** Наименнование автомобиля */
    @Column(name = "name")
    private String name;

    /** Идентификатор двигателя */
    @ManyToOne
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "engine_id_fk"))
    private Engine engine;

    /** Список владельцев */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "history_owners",
            joinColumns =
                    {@JoinColumn(name = "car_id", nullable = false, updatable = false)},
            inverseJoinColumns =
                    {@JoinColumn(name = "owners_id", nullable = false, updatable = false)}
    )
    private Set<Owner> owners = new HashSet<>();
}
