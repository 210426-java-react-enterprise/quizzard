package com.revature.quizzard.util;

public enum RegEx {

    /**
     * Documentation needed
     */
    VALID_FIRST_NAME("^[a-zA-Z]{1,25}$"),

    /**
     * Documentation needed
     */
    VALID_LAST_NAME("^[a-zA-Z]{1,25}$"),

    /**
     * Documentation needed
     */
    VALID_USERNAME("^[a-zA-Z0-9]{1,20}$"),

    /**
     * Documentation needed
     */
    VALID_PASSWORD("^(?=.*?[#?!@$%^&*-])[a-zA-Z0-9].{8,255}$"),

    /**
     * Documentation needed
     */
    VALID_EMAIL("^([0-9a-zA-Z.]+@[0-9a-zA-Z]+[.][a-zA-Z]+){1,255}$"),

    /**
     * Documentation needed
     */
    VALID_AGE("^[0-9]{1,3}$"),

    /**
     * A regular expression used for validating the user's input from the welcome screen.
     * Should match to only numbers in the range 1 through 3.
     */
    VALID_WELCOME_SCREEN_INPUT("[1-3]"),

    /**
     * A default regular expression that does not check strings against any criteria.
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
