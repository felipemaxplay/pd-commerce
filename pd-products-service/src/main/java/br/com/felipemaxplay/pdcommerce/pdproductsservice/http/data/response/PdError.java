package br.com.felipemaxplay.pdcommerce.pdproductsservice.http.data.response;

import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.Objects;

public class PdError {
    private final Date timestamp;
    private final String code;
    private final String message;

    public PdError(@NonNull Date timestamp, @NonNull String code, @NonNull String message) {
        this.timestamp = Objects.requireNonNull(timestamp);
        this.code = Objects.requireNonNull(code);
        this.message = Objects.requireNonNull(message);
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "PdError{" +
                "timestamp=" + timestamp +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
