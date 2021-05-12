package com.revature.quizzard.util;

public enum RegEx {

    /**
     * Must contain only English alphabetic characters and be between 1 and 25 characters in length.
     */
    ALPHABETIC_25("^[a-zA-Z]{1,25}$"),

    /**
     * Minimum one character, maximum twenty characters, alphanumeric characters allowed
     */
    ALPHANUMERIC_20("^[a-zA-Z0-9]{1,20}$"),

    /**
     * Minimum eight characters, at least one letter, one number and one special character.
     */
    PASSWORD("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$"),

    /**
     * Avert ye eyes, lest ye be blinded by the glory of RFC 5322.
     */
    EMAIL("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\" +
            "x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9]" +
            ")?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(" +
            "2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21" +
            "-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"),

    /**
     * Minimum one character, maximum three characters, only numeric values allowed.
     */
    AGE_RANGE("^[0-9]{1,3}$"),

    /**
     * Only numeric values 1, 2, and 3 allowed.
     */
    VALID_WELCOME_SCREEN_INPUT("[1-3]"),

    /**
     * A non-value used as a default when no pattern validation is necessary.
     */
    NONE("");

    private String value;

    RegEx(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
