package com.example.apicontrolecombustivel.utils;

import com.example.apicontrolecombustivel.dto.MessageDto;
import lombok.experimental.UtilityClass;
import org.springframework.http.HttpStatus;

@UtilityClass
public class MsgStandard {
    public static MessageDto msgStandardOk(String msg){
        HttpStatus httpStatus = HttpStatus.OK;
        return MessageDto.builder()
                .httpStatus(httpStatus)
                .statusCode(httpStatus.value())
                .message(msg)
                .build();
    }
}