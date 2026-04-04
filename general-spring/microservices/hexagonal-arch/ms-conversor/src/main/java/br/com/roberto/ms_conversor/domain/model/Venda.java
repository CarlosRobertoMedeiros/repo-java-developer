package br.com.roberto.ms_conversor.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public record Venda(LocalDateTime date,
                    String type,
                    String origin,
                    String status,
                    List<ItemVenda> items) {
    public Venda {

        Objects.requireNonNull(date, "A data da venda é obrigatória");

        type = validateNotBlank(type, "O tipo da venda é obrigatório");
        origin = validateNotBlank(origin, "A origem da venda é obrigatória");
        status = validateNotBlank(status, "O status da venda é obrigatório");

        items = (items == null) ? List.of() : Collections.unmodifiableList(items);

    }

    public BigDecimal totalAmount() {
        return items.stream()
                .map(ItemVenda::totalAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        return String.format(
                "Venda[data=%s, type='%s', origin='%s', status='%s', totalItems=%d, totalAmount=%s]",
                date, type, origin, status, items.size(), totalAmount()
        );
    }

    private static String validateNotBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
        return value.trim();
    }
}
