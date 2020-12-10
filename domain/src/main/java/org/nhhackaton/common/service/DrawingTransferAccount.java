package org.nhhackaton.common.service;

import org.nhhackaton.common.dto.FinAccountDto;

//고객계좌 -> 핀테크계좌로 입금
public interface DrawingTransferAccount {
    void transferAccount(FinAccountDto finAccountDto);
}
