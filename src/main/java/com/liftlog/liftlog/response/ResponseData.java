package com.liftlog.liftlog.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseData<T> {

    private boolean success;
    private String message;
    private T data;


}
