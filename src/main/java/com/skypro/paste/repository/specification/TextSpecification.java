package com.skypro.paste.repository.specification;

import com.skypro.paste.model.PasteAccessType;
import com.skypro.paste.model.Text;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;

public class TextSpecification {
    public static Specification<Text> byText(String search) {
        return (root, query, cb) -> {
            if (search == null || search.isBlank()) {
                return cb.conjunction();
            }
            return cb.like(root.get("text"), "%" + search + "%");
        };
    }

    public static Specification<Text> byTitle(String search) {
        return (root, query, cb) -> {
            if (search == null || search.isBlank()) {
                return cb.conjunction();
            }
            return cb.like(root.get("title"), "%" + search + "%");
        };
    }

    public static Specification<Text> byId(String id) {
        return (root, query, cb) -> {
            if (id == null) {
                return cb.conjunction();
            }
            return cb.equal(root.get("id"), id);
        };
    }

    public static Specification<Text> publicOnly() {
        return (root, query, cb) -> cb.equal(root.get("accessType"), PasteAccessType.PUBLIC.getString());
    }

    public static Specification<Text> validOnly() {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("expiryDateTime"), Instant.now());
    }

}
