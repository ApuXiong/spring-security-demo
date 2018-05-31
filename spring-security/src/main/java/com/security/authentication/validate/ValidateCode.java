package com.security.authentication.validate;

public class ValidateCode {
    private String key;
    private String code;

    public ValidateCode(String key, String code) {
        this.key = key;
        this.code = code;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
