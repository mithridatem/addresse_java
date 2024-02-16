package com.addresse.model;

public class Regex {
    public static final String REGEX_MAIL = "^[\\w!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    public static final String REGEX_PASSWORD = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{12,}$";
}
