package com.bank.service.impl;

import com.bank.dto.TransferDTO;
import com.bank.dto.TransactionDTO;
import com.bank.entity.Card;
import com.bank.entity.Transaction;
import com.bank.repository.CardRepository;
import com.bank.service.interfaces.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class TransferServiceImpl implements TransferService {
    
    @Autowired
    private CardRepository cardRepository;
    
    @Override
    public TransactionDTO transferBetweenOwnCards(TransferDTO transferDTO) {
        Card fromCard = cardRepository.findById(transferDTO.getFromCardId()).orElse(null);
        Card toCard = cardRepository.findById(transferDTO.getToCardId()).orElse(null);
        
        if (fromCard == null || toCard == null) return null;
        if (fromCard.getBalance() < transferDTO.getAmount()) return null;
        
        fromCard.setBalance(fromCard.getBalance() - transferDTO.getAmount());
        toCard.setBalance(toCard.getBalance() + transferDTO.getAmount());
        
        cardRepository.save(fromCard);
        cardRepository.save(toCard);
        
        TransactionDTO dto = new TransactionDTO();
        dto.setId(1L);
        dto.setAmount(transferDTO.getAmount());
        dto.setStatus("COMPLETED");
        dto.setTransactionDate(LocalDateTime.now());
        return dto;
    }
}
