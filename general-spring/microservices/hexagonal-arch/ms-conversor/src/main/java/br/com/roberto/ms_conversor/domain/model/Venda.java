package br.com.roberto.ms_conversor.domain.model;

import java.math.BigDecimal;

public record Venda(BigDecimal quantity,
                    BigDecimal unitAmount,
                    String description) {
}
