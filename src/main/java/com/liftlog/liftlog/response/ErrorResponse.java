package com.liftlog.liftlog.response;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

    private boolean success;
    private int status;
    private LocalDateTime createdAt;
    private String message;
}
