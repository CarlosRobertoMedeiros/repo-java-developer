package br.com.roberto.br.samplehexagonal.ms_template.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ObjectSample extends BaseModel implements Serializable {
    private BigDecimal amount;
    private LocalDateTime date;
    private String text;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ObjectSample{" +
                "amount=" + amount +
                ", date=" + date +
                ", text='" + text + '\'' +
                '}';
    }
}
