package br.com.roberto.hub_manager_app.infrastructure.adapter.out.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationBuilder<T> {

    private final List<Specification<T>> specs = new ArrayList<>();

    public static <T> SpecificationBuilder<T> builder() {
        return new SpecificationBuilder<>();
    }

    public SpecificationBuilder<T> equal(String field, Object value) {
        if (value != null) {
            specs.add((root, query, cb) -> cb.equal(root.get(field), value));
        }
        return this;
    }

    public SpecificationBuilder<T> likeIgnoreCase(String field, String value) {
        if (value != null && !value.isBlank()) {
            specs.add((root, query, cb) ->
                    cb.like(cb.lower(root.get(field)), "%" + value.toLowerCase() + "%"));
        }
        return this;
    }

    public SpecificationBuilder<T> between(String field, Comparable from, Comparable to) {
        if (from != null && to != null) {
            specs.add((root, query, cb) -> cb.between(root.get(field), from, to));
        }
        return this;
    }

    public Specification<T> build() {
        return specs.stream()
                .reduce(Specification::and)
                .orElse((root, query, cb) -> cb.conjunction());
    }
}
