package br.com.roberto.ms_conversor.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public record ItemVenda(BigDecimal quantity,
                        BigDecimal unitAmount,
                        String description) {

    public ItemVenda {
        quantity = normalizeQuantity(validatePositive(quantity, "A quantidade do item deve ser informada e maior que zero"));
        unitAmount = normalizeAmount(validatePositive(unitAmount, "O valor unitário deve ser informado e maior que zero"));
        description = validateNotBlank(description, "A descrição do item é obrigatória");
    }


    public BigDecimal totalAmount() {
        return quantity
                .multiply(unitAmount)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public String formattedTotal() {
        return String.format("R$ %,.2f", totalAmount());
    }

    private static BigDecimal validatePositive(BigDecimal value, String message) {
        if (Objects.isNull(value) || value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(message);
        }
        return value.stripTrailingZeros();
    }

    private static String validateNotBlank(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
        return value.trim();
    }

    private static BigDecimal normalizeQuantity(BigDecimal quantity) {
        return quantity.setScale(3, RoundingMode.HALF_UP);
    }

    private static BigDecimal normalizeAmount(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String toString() {
        return String.format(
                "ItemVenda[descricao='%s', quantidade=%s, valorUnitario=%s, valorTotal=%s]",
                description,
                quantity,
                unitAmount,
                totalAmount()
        );
    }
}
