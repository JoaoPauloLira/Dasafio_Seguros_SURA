package br.com.sura.error;

import org.springframework.stereotype.Component;

@Component
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = -7806029002430564887L;
    
    private String message;

    public CustomException() {
    }

    public CustomException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
