package ru.job4j.cars.common.model.post;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.job4j.cars.common.model.car.Car;
import ru.job4j.cars.common.model.user.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "auto_post")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Post {

    /** Идентификатор объявления */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    /** Текст объявления */
    private String text;

    /** Дата создания */
    private LocalDateTime created;

    /** Пользователь */
    @ManyToOne
    @JoinColumn(name = "auto_user_id")
    private User user;

    /** История стоимости */
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "auto_post_id")
    private List<PriceHistory> priceHistory = new ArrayList<>();

    /** Владельцы */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "auto_post_id")
    private List<User> participates = new ArrayList<>();

    /** Автомобиль */
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    /** Фото */
    @OneToOne
    @JoinColumn(name = "photo_id")
    private Photo photo;

    /** Идентификатор категории */
    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "category_id_fk"))
    private Category category;

    /** Признак продажи (true - продано, false - нет) */
    @Column(name = "is_sold")
    private Boolean isSold;

}
