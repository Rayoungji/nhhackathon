package org.nhhackaton.errors.exception;

import org.nhhackaton.errors.ErrorCode;

public class NotExistIdentityException extends ErrorCodeException{
    public NotExistIdentityException(ErrorCode errorCode) {
        super(errorCode);

    }
    public NotExistIdentityException(){
        this(ErrorCode.NOT_EXIST_IDENTITY);
    }
}
