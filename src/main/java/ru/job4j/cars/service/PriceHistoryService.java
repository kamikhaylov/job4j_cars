package ru.job4j.cars.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.cars.common.model.post.PriceHistory;
import ru.job4j.cars.repository.post.PriceHistoryRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Сервис истории цен
 */
@ThreadSafe
@Service
@AllArgsConstructor
public class PriceHistoryService {

    private final PriceHistoryRepository priceHistoryRepository;

    public PriceHistory create(BigDecimal price) {
        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setCreated(LocalDateTime.now());
        priceHistory.setBefore(price);
        priceHistory.setAfter(price);
        priceHistoryRepository.create(priceHistory);
        return priceHistory;
    }
}
