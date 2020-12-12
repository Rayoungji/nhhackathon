package org.nhhackaton.errors.exception;

import org.nhhackaton.errors.ErrorCode;

public class MemberAlreadyExsistException extends ErrorCodeException{
    public MemberAlreadyExsistException(ErrorCode errorCode) {
        super(errorCode);

    }
    public MemberAlreadyExsistException(){
        this(ErrorCode.MEMBER_ALREADY_EXISIST);
    }
}
