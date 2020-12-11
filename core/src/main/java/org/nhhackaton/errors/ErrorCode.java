package org.nhhackaton.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //Common
    INVALID_INPUT_VALUE(400, "Invalid Input Value"),
    ENTITY_NOT_FOUND(400, "Entity Not Found"),
    INVALID_TYPE_VALUE(400, "Invalid Type Value"),
    METHOD_NOT_ALLOWED(405, "Invalid Input Value"),
    INTERNAL_SERVER_ERROR(500, "Server Error"),


    //Member
    DOCUMENT_EMPTY(400, "Check The Document")
    ;


    private final int code;
    private final String message;
}
