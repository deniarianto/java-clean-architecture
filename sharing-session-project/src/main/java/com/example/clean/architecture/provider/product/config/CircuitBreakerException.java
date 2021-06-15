package com.example.clean.architecture.provider.product.config;

public class CircuitBreakerException extends Exception {

    private static final long serialVersionUID = 1L;

    private String code;
    private String message;
    private String action;

    public CircuitBreakerException(String code, String message) {
        super();
        this.setCode(code);
        this.setMessage(message);
    }

    public CircuitBreakerException(String code, String message, String action) {
        super();
        this.setCode(code);
        this.setMessage(message);
        this.setAction(action);
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Circuit Breaker Exception{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", action='" + action + '\'' +
                '}' + super.toString();
    }
}
