package ru.job4j.cars.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.cars.common.model.post.Post;

/**
 * Контекст детальной информации объявления
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDetailsContext {

    /** Объявление */
    private Post post;

    /** Показать действия */
    private boolean showAction;

    public PostDetailsContext(Post post) {
        this.post = post;
    }
}
