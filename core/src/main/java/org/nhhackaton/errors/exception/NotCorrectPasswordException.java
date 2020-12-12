package org.nhhackaton.errors.exception;

import org.nhhackaton.errors.ErrorCode;

public class NotCorrectPasswordException extends ErrorCodeException{
    public NotCorrectPasswordException(ErrorCode errorCode) {
        super(errorCode);

    }
    public NotCorrectPasswordException(){
        this(ErrorCode.NOT_CORRECT_PASSWORD);
    }
}
