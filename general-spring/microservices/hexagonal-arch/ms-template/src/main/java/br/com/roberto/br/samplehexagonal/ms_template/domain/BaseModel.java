package br.com.roberto.br.samplehexagonal.ms_template.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class BaseModel {
    private UUID id;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;


    public BaseModel() {

    }

    public BaseModel(UUID id, LocalDateTime createdDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
