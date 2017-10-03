package com.soothsayer.core.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class SoothSayerError {
    private HttpStatus httpStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String messageDetails;

    private SoothSayerError() {
        this.timestamp = LocalDateTime.now();
    }

    public SoothSayerError(HttpStatus httpStatus) {
        this();
        this.httpStatus = httpStatus;
    }

    public SoothSayerError(HttpStatus httpStatus, Throwable ex, String message) {
        this(httpStatus);
        this.message = message;
        this.messageDetails = ex.getLocalizedMessage();
    }
    public SoothSayerError(HttpStatus httpStatus, Throwable ex) {
        this(httpStatus, ex, "Unexpected error");
    }
}
