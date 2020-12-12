package org.nhhackaton.errors.exception;

import org.nhhackaton.errors.ErrorCode;

public class MemberNotFoundException extends ErrorCodeException{

    public MemberNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
    public MemberNotFoundException(){
        this(ErrorCode.MEMBER_NOT_FOUND);
    }
}
