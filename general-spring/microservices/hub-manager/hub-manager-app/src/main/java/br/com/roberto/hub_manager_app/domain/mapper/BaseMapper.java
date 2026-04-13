package br.com.roberto.hub_manager_app.domain.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface BaseMapper<D,E> {
    D toDomain(E entity);
    E toEntity(D domain);

    default List<D> toDomain(List<E> entities) {
        if (entities == null) return List.of();

        return entities.stream()
                .filter(Objects::nonNull)
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    default List<E> toEntity(List<D> domains) {
        if (domains == null) return List.of();

        return domains.stream()
                .filter(Objects::nonNull)
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
