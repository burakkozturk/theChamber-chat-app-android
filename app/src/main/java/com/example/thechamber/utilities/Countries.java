package com.example.thechamber.utilities;

public class Countries {
    private String id;
    private String name;
    private String flag;
    private String code;
    private String dialCode;
    private String pattern;
    private int limit;

    public Countries(String id, String name, String flag, String code, String dialCode, String pattern, int limit) {
        this.id = id;
        this.name = name;
        this.flag = flag;
        this.code = code;
        this.dialCode = dialCode;
        this.pattern = pattern;
        this.limit = limit;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getFlag() {
        return flag;
    }

    public String getDialCode() {
        return dialCode;
    }

    public String getPattern() {
        return pattern;
    }

    public int getLimit() {
        return limit;
    }


    @Override
    public String toString() {
        return dialCode + " " + flag; // Spinner'da gösterilecek metni döndür
    }

    // Diğer getter'lar buraya eklenebilir
}
