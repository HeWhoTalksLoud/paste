package com.skypro.paste.model;

public enum PasteAccessType {
    PUBLIC("PUBLIC"),
    UNLISTED("UNLISTED");

    final String string;
    PasteAccessType(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    public static PasteAccessType fromString(String s) {
        switch (s) {
            case "PUBLIC":
                return PasteAccessType.PUBLIC;
            case "UNLISTED":
                return PasteAccessType.UNLISTED;
            default:
                return null;
        }
    }
}
