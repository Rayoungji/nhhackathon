package org.nhhackaton.errors.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.nhhackaton.errors.ErrorCode;

@Getter
@RequiredArgsConstructor
public class ErrorCodeException extends RuntimeException{
    private final ErrorCode errorCode;
}
