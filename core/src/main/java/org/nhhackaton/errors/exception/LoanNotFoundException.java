package org.nhhackaton.errors.exception;

import org.nhhackaton.errors.ErrorCode;

public class LoanNotFoundException extends ErrorCodeException{
    public LoanNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public LoanNotFoundException(){
        this(ErrorCode.LOAN_NOT_FOUND);
    }
}
