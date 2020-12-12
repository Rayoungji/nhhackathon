package org.nhhackaton.errors.exception;

import org.nhhackaton.errors.ErrorCode;

public class DocumentUploadFailException extends ErrorCodeException {
    public DocumentUploadFailException(ErrorCode errorCode) {
        super(errorCode);
    }

    public DocumentUploadFailException(){
        this(ErrorCode.DOCUMENT_UPLOAD_FAIL);
    }
}
