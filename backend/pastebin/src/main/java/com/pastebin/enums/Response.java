package com.pastebin.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@JsonFormat(shape=JsonFormat.Shape.OBJECT)
public enum Response {
    SUCCESS(200, "Request was successfully executed"),
    DATA_UNAVAILABLE(404, "Requested data doesnt exist"),
    UNREACHABLE(503, "Unable to reach to the end point"),
    TIMEOUT(504, "Connection timeout"),
    UNKNOWN_ERROR(500, "Unknown exception occurred"),
    INVALID_REQUEST(500, "Some params are invalid");

    @Getter
    private int code;

    @Getter
    private String reason;
}
