package com.bank.service.interfaces;

import com.bank.dto.TransferDTO;
import com.bank.dto.TransactionDTO;

public interface TransferService {
    TransactionDTO transferBetweenOwnCards(TransferDTO transferDTO);
}
