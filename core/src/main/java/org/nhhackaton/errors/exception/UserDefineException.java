package org.nhhackaton.errors.exception;

import lombok.Getter;

@Getter
public class UserDefineException extends RuntimeException {

    public UserDefineException(String message){
        super(message);
    }

}