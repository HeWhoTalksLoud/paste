package com.skypro.paste.repository.specification;

import com.skypro.paste.model.PasteAccessType;
import com.skypro.paste.model.Text;
import org.springframework.data.jpa.domain.Specification;

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

    public static Specification<Text> publicOnly() {
        return (root, query, cb) -> cb.equal(root.get("accessType"), PasteAccessType.PUBLIC.getString());
    }
}