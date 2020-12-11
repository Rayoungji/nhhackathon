package org.nhhackaton.errors.exception;

import org.nhhackaton.errors.ErrorCode;

public class DocumentEmptyException extends ErrorCodeException{

    public DocumentEmptyException(ErrorCode errorCode) {
        super(errorCode);
    }
    public DocumentEmptyException(){
        this(ErrorCode.DOCUMENT_EMPTY);
    }
}
